package net.nigne.yourtour.book.infra;

import net.nigne.yourtour.book.domain.Book;
import net.nigne.yourtour.book.infra.dto.BookApiResponseDto;
import net.nigne.yourtour.book.ui.dto.BookResponseDto;

public class BookTranslator {

    public static Book translate(BookApiResponseDto.Item item, String category) {

        return Book.createBuilder()
                .name(item.getOriginTitle())
                .author(item.getAuthor())
                .category(category)
                .publisher(item.getPublisher())
                .price(item.getPrice())
                .build();
    }

    public static BookResponseDto translate(Book book) {

        return BookResponseDto.responseBuilder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuther())
                .category(book.getCategory())
                .publisher(book.getPublisher())
                .price(book.getPrice())
                .build();
    }
}
