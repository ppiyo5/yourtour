package net.nigne.yourtour.book.application;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.book.domain.Book;
import net.nigne.yourtour.book.domain.BookRepository;
import net.nigne.yourtour.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 책이 존재하지 않습니다.", id)));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
