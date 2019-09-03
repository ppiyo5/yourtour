package net.nigne.yourtour.book.infra.dto;

import lombok.Builder;
import lombok.Getter;
import net.nigne.yourtour.enrollment.domain.SellType;

@Getter
public class RegisteredBookResponse {

    private Long id;

    private String book;

    private Long amount;

    private SellType sellType;

    private boolean reserved;

    private Long enrollmentId;

    @Builder(builderMethodName = "createBuilder")
    public RegisteredBookResponse(Long id, String book, Long amount, SellType sellType, boolean reserved, Long enrollmentId) {
        this.id = id;
        this.book = book;
        this.amount = amount;
        this.sellType = sellType;
        this.reserved = reserved;
        this.enrollmentId = enrollmentId;
    }
}
