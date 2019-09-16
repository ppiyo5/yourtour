package net.nigne.yourtour.reservation.application;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.book.application.BookService;
import net.nigne.yourtour.book.application.RegisteredBookService;
import net.nigne.yourtour.book.domain.Book;
import net.nigne.yourtour.book.domain.RegisteredBook;
import net.nigne.yourtour.enrollment.application.EnrollmentService;
import net.nigne.yourtour.enrollment.domain.Enrollment;
import net.nigne.yourtour.reservation.application.dto.ShoppingBasketResponseDto;
import net.nigne.yourtour.reservation.domain.Reservation;
import net.nigne.yourtour.reservation.domain.ShoppingBasket;
import net.nigne.yourtour.reservation.domain.ShoppingBasketRepository;
import net.nigne.yourtour.reservation.infra.ShoppingBasketTranslate;
import net.nigne.yourtour.user.application.BuyerService;
import net.nigne.yourtour.user.domain.Buyer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingBasketService {

    private final BuyerService buyerService;
    private final ShoppingBasketRepository shoppingBasketRepository;
    private final RegisteredBookService registeredBookService;
    private final BookService bookService;
    private EnrollmentService enrollmentService;

    public ShoppingBasket findByBuyerId(String buyerId) {

        Buyer buyer = buyerService.findById(buyerId);

        return shoppingBasketRepository.findById(buyer.getId())
                .orElseGet(() -> shoppingBasketRepository.save(new ShoppingBasket(buyer.getId())));
    }

    public ShoppingBasketResponseDto findShoppingBasket(String buyerId) {

        ShoppingBasket shoppingBasket = findByBuyerId(buyerId);

        List<RegisteredBook> registeredBookList = shoppingBasket.getReservations()
                .getReservations().stream()
                .map(Reservation::getRegisteredBookId)
                .map(registeredBookService::findById)
                .collect(Collectors.toList());

        List<Book> bookList = registeredBookList.stream()
                .map(RegisteredBook::getBookId)
                .map(bookService::findById)
                .collect(Collectors.toList());

        List<Enrollment> enrollmentList = registeredBookList.stream()
                .map(RegisteredBook::getEnrollmentId)
                .map(enrollmentService::findById)
                .collect(Collectors.toList());

        return ShoppingBasketTranslate.translate(shoppingBasket, registeredBookList, bookList, enrollmentList);

    }
}
