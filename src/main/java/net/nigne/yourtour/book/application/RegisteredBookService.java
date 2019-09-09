package net.nigne.yourtour.book.application;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.book.application.dto.RegisteredBookDto;
import net.nigne.yourtour.book.application.dto.RegisteredBookRequestDto;
import net.nigne.yourtour.book.domain.Book;
import net.nigne.yourtour.book.domain.RegisteredBook;
import net.nigne.yourtour.book.domain.RegisteredBookRepository;
import net.nigne.yourtour.book.infra.RegisteredBookTranslator;
import net.nigne.yourtour.book.infra.dto.RegisteredBookResponse;
import net.nigne.yourtour.enrollment.domain.SellType;
import net.nigne.yourtour.exception.NotFoundException;
import net.nigne.yourtour.exception.WrongValueException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisteredBookService {

    private final BookService bookService;
    private final RegisteredBookRepository registeredBookRepository;

    private final RegisteredBookSessionManager sessionManager;

    public RegisteredBook findById(Long registeredBookId) {

        return registeredBookRepository.findById(registeredBookId)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 책이 존재하지 않습니다.", registeredBookId)));
    }

    public void save(HttpSession session, RegisteredBookRequestDto registeredBookRequestDto) {
        Book book = bookService.findById(registeredBookRequestDto.getBookId());

        if(registeredBookRequestDto.getSellType() == SellType.NEW) {
            registeredBookRequestDto.setAmount(book.getPrice());
            sessionManager.addNewRegisteredBook(session, registeredBookRequestDto);
            return;
        }

        validateAmount(book.getPrice(), registeredBookRequestDto.getAmount());
        sessionManager.addUsedRegisteredBook(session, registeredBookRequestDto);
    }

    private void validateAmount(Long price, Long amoutOfUsedBook) {
        if(price < amoutOfUsedBook) {
            throw new WrongValueException("원래 책값보다 비쌉니다.");
        }
    }

    public void save(Long enrollmentId, List<RegisteredBookDto> registeredBookDtos) {
        List<RegisteredBook> registeredBooks = registeredBookDtos.stream()
                .map(dto -> convert(enrollmentId, dto))
                .collect(Collectors.toList());

        registeredBookRepository.saveAll(registeredBooks);
    }

    private RegisteredBook convert(Long enrollmentId, RegisteredBookDto dto) {
        return RegisteredBook.builder()
                .bookId(dto.getBookId())
                .amount(dto.getAmount())
                .sellType(dto.getSellType())
                .enrollmentId(enrollmentId)
                .build();
    }

    public List<RegisteredBookResponse> findBy(Pageable convert) {
        List<RegisteredBook> registeredBooks = registeredBookRepository.findAll(convert)
                .getContent();

        return registeredBooks.stream()
                .map(registeredBook -> {
                    String bookName = bookService.findById(registeredBook.getId()).getName();
                    return RegisteredBookTranslator.translate(registeredBook, bookName);
                }).collect(Collectors.toList());
    }




}
