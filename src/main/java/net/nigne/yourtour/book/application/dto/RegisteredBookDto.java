package net.nigne.yourtour.book.application.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.common.domain.UnitAmount;
import net.nigne.yourtour.enrollment.domain.SellType;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBookDto {

    private String sellerId;

    private Long bookId;

    private UnitAmount amount;

    private SellType sellType;

    @Builder
    public RegisteredBookDto(String sellerId, Long bookId, UnitAmount amount, SellType sellType) {
        this.sellerId = sellerId;
        this.bookId = bookId;
        this.amount = amount;
        this.sellType = sellType;
    }
}
