package net.nigne.yourtour.reservation.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import net.nigne.yourtour.book.domain.Book;
import net.nigne.yourtour.book.domain.RegisteredBook;
import net.nigne.yourtour.enrollment.domain.Enrollment;
import net.nigne.yourtour.reservation.domain.ShoppingBasket;

import java.util.List;

@Getter
public class ShoppingBasketResponseDto {

    @JsonIgnore
    private final ReservationBookDtoCollection reservationBooks;

    @Builder(builderMethodName = "responseBuilder")
    public ShoppingBasketResponseDto(ShoppingBasket shoppingBasket, List<RegisteredBook> registeredBookList, List<Book> bookList, List<Enrollment> enrollmentList) {
        this.reservationBooks = new ReservationBookDtoCollection(shoppingBasket.getReservations(), registeredBookList, bookList, enrollmentList);
    }

    public List<ReservationBookDto> getBooks() {
        return reservationBooks.getReservationBooks();
    }

    public Long getTotalAmount() {
        return reservationBooks.computeTotalAmount();
    }

    public Long getDeliveryFee() {
        return reservationBooks.computeDeliveryFee();
    }

    public Long getManyBuyDiscountAmount() {
        return reservationBooks.computeManyBuyDiscountAmount();
    }

    public Long getUsedDiscountAmount() {
        return reservationBooks.computeUsedDiscountAmount();
    }

    public Long getEventDiscountAmount() {
        return reservationBooks.computeEventDiscountAmount();
    }

    public Integer getCount() {
        return reservationBooks.computeCount();
    }
}
