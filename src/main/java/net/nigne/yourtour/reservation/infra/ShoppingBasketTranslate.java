package net.nigne.yourtour.reservation.infra;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.book.domain.Book;
import net.nigne.yourtour.book.domain.RegisteredBook;
import net.nigne.yourtour.enrollment.domain.Enrollment;
import net.nigne.yourtour.reservation.application.dto.ShoppingBasketResponseDto;
import net.nigne.yourtour.reservation.domain.ShoppingBasket;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShoppingBasketTranslate {

    public static ShoppingBasketResponseDto translate(ShoppingBasket shoppingBasket, List<RegisteredBook> registeredBookList, List<Book> bookList, List<Enrollment> enrollmentList) {

        return ShoppingBasketResponseDto.responseBuilder()
                .shoppingBasket(shoppingBasket)
                .registeredBookList(registeredBookList)
                .bookList(bookList)
                .enrollmentList(enrollmentList)
                .build();
    }
}
