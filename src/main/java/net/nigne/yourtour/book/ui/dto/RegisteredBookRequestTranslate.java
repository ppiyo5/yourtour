package net.nigne.yourtour.book.ui.dto;

import net.nigne.yourtour.book.application.dto.RegisteredBookRequestDto;
import net.nigne.yourtour.enrollment.domain.SellType;

public class RegisteredBookRequestTranslate {

    public static RegisteredBookRequestDto translate(String sellerId, NewBookEnrollmentRequest request) {
        return RegisteredBookRequestDto.builder()
                .sellerId(sellerId)
                .bookId(request.getBookId())
                .count(request.getCount())
                .sellType(SellType.NEW)
                .build();
    }

    public static RegisteredBookRequestDto translate(String sellerId, UsedBookEnrollmentRequest request) {
        return RegisteredBookRequestDto.builder()
                .sellerId(sellerId)
                .bookId(request.getBookId())
                .amount(request.getAmount())
                .sellType(SellType.USED)
                .build();
    }
}
