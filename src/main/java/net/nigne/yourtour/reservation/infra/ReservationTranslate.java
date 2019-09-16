package net.nigne.yourtour.reservation.infra;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.book.domain.Book;
import net.nigne.yourtour.book.domain.RegisteredBook;
import net.nigne.yourtour.enrollment.domain.Enrollment;
import net.nigne.yourtour.enrollment.domain.Event;
import net.nigne.yourtour.exception.NotFoundException;
import net.nigne.yourtour.reservation.application.dto.ReservationBookDto;
import net.nigne.yourtour.reservation.domain.Reservation;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationTranslate {

    public static ReservationBookDto translate(Reservation reservation, List<RegisteredBook> registeredBooks, List<Book> books, List<Enrollment> enrollments) {
        RegisteredBook registeredBook = findByRegisterBook(registeredBooks, reservation.getRegisteredBookId());
        Book book = findByBook(books, registeredBook.getBookId());
        Enrollment enrollment = findByEnrollment(enrollments, registeredBook.getEnrollmentId());

        return ReservationBookDto.builder()
                .registeredBookId(registeredBook.getId())
                .bookId(book.getId())
                .bookName(book.getName())
                .amount(book.getPrice())
                .usedDiscountAmount(registeredBook.getAmount().getAmount() - book.getPrice())
                .eventDiscountPercent(enrollment.getEvent(reservation.getReservationDate())
                .map(Event::getPeriodPercent)
                .orElse(0.0))
                .build();
    }

    private static RegisteredBook findByRegisterBook(List<RegisteredBook> registeredBookList, Long registeredBookId) {

        return registeredBookList.stream()
                .filter(registeredBook -> registeredBook.getId().equals(registeredBookId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 책이 존재하지 않습니다.", registeredBookId)));
    }

    private static Book findByBook(List<Book> bookList, Long bookId) {

        return bookList.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 책이 존재하지 않습니다.", bookId)));
    }

    private static Enrollment findByEnrollment(List<Enrollment> enrollmentList, Long enrollmentId) {

        return enrollmentList.stream()
                .filter(enrollment -> enrollment.getId().equals(enrollmentId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 등록이 존재하지 않습니다.", enrollmentId)));
    }
}
