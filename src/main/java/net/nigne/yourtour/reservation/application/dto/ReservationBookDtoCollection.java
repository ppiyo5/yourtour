package net.nigne.yourtour.reservation.application.dto;

import lombok.Getter;
import net.nigne.yourtour.book.domain.Book;
import net.nigne.yourtour.book.domain.RegisteredBook;
import net.nigne.yourtour.enrollment.domain.Enrollment;
import net.nigne.yourtour.reservation.domain.Reservations;

import java.util.List;

@Getter
public class ReservationBookDtoCollection {

    private static final Integer DELIVERY_FEE_MIN_COUNT = 3;
    private static final Integer MANY_BUY_DISCOUNT_COUNT = 5;
    private static final Long DELIVERY_FEE = 2500L;

    private List<ReservationBookDto> reservationBooks;

    ReservationBookDtoCollection(Reservations reservations, List<RegisteredBook> registeredBookList, List<Book> bookList, List<Enrollment> enrollmentList) {
        reservationBooks =
    }
}
