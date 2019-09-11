package net.nigne.yourtour.reservation.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class ReservationBookDto {

    private static final Double MANY_BUY_DISCOUNT_PERCENT = 5.0;

    private Long registeredBookId;
    private Long bookId;
    private String bookName;

    private long amount;
    private long usedDiscountAmount;
    private double eventDiscountPercent;
    @JsonIgnore
    private boolean manyBuy;

    @Builder
    public ReservationBookDto(long registeredBookId, long bookId, String bookName, long amount, long usedDiscountAmount, double eventDiscountPercent) {
        this.registeredBookId = registeredBookId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.amount = amount;
        this.usedDiscountAmount = usedDiscountAmount;
        this.eventDiscountPercent = eventDiscountPercent;
    }

    public long computeBaseAmount() {
        return amount + usedDiscountAmount;
    }

    public long getEventDiscountAmount() {
        return -calculateRateAmount(computeBaseAmount(), eventDiscountPercent);
    }

    public long getManyBuyDiscountAmount() {
        return -calculateRateAmount(computeBaseAmount(), getManyBuyDiscountPercent());
    }

    public double getManyBuyDiscountPercent() {
        if(manyBuy) {
            return MANY_BUY_DISCOUNT_PERCENT;
        }
        return 0.0;
    }

    private long calculateRateAmount(Long amount, Double rate) {

        return BigDecimal.valueOf(amount)
                .multiply(
                        BigDecimal.valueOf(0.01).multiply(BigDecimal.valueOf(rate))
                )
                .setScale(0, RoundingMode.DOWN)
                .longValue();
    }

    public ReservationBookDto changeSmallBuy() {
        manyBuy = false;
        return this;
    }

    public ReservationBookDto changeManyBuy() {
        manyBuy = true;
        return this;
    }
}
