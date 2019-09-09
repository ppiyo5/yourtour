package net.nigne.yourtour.book.ui;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.book.application.BookService;
import net.nigne.yourtour.book.infra.BookTranslator;
import net.nigne.yourtour.book.ui.dto.BookResponseDto;
import net.nigne.yourtour.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping(value = "/books/{id}")
    public ApiResponse<BookResponseDto> getBook(@PathVariable Long id) {
        return ApiResponse.createOK(BookTranslator.translate(bookService.findById(id)));
    }

    @GetMapping("/books")
    public ApiResponse<List<BookResponseDto>> getAll() {
        List<BookResponseDto> responseDtoList = bookService.findAll().stream()
                .map(BookTranslator::translate)
                .collect(Collectors.toList());

        return ApiResponse.createOK(responseDtoList);
    }
}
