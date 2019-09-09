package net.nigne.yourtour.book.infra;

import net.nigne.yourtour.book.domain.RegisteredBook;
import net.nigne.yourtour.book.infra.dto.RegisteredBookResponse;

public class RegisteredBookTranslator {

    public static RegisteredBookResponse translate(RegisteredBook registeredBook, String book) {

        return RegisteredBookResponse.createBuilder()
                .id(registeredBook.getId())
                .amount(registeredBook.getAmount().getAmount())
                .book(book)
                .sellType(registeredBook.getSellType())
                .reserved(registeredBook.isReserved())
                .enrollmentId(registeredBook.getEnrollmentId())
                .build();
    }
}
