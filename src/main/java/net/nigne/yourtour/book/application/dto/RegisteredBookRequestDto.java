package net.nigne.yourtour.book.application.dto;

import lombok.*;
import net.nigne.yourtour.common.domain.UnitAmount;
import net.nigne.yourtour.enrollment.domain.SellType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBookRequestDto {

    private String sellerId;

    private Long bookId;

    private Long count;

    @Setter
    private Long amount;

    @Enumerated(EnumType.STRING)
    private SellType sellType;

    @Builder
    public RegisteredBookRequestDto(String sellerId, Long bookId, Long count, Long amount, SellType sellType) {
        this.sellerId = sellerId;
        this.bookId = bookId;
        this.count = count;
        this.amount = amount;
        this.sellType = sellType;
    }

    public RegisteredBookDto toSession() {
        return RegisteredBookDto.builder()
                .bookId(bookId)
                .amount(new UnitAmount(amount))
                .sellType(sellType)
                .sellerId(sellerId)
                .build();
    }
}
