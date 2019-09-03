package net.nigne.yourtour.book.infra;

import net.nigne.yourtour.book.domain.Book;
import net.nigne.yourtour.book.infra.dto.BookApiResponseDto;

public class BookTranslator {

    public static Book translate(BookApiResponseDto.Item item, String category) {

        return Book.createBuilder()
                .name(item.getOriginTitle())
                .auther(item.getAuthor())
                .category(category)
                .publisher(item.getPublisher())
                .price(item.getPrice())
                .build();
    }

    public static BookRes
}
