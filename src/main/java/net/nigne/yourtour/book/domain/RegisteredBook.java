package net.nigne.yourtour.book.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.common.domain.UnitAmount;
import net.nigne.yourtour.enrollment.domain.SellType;
import net.nigne.yourtour.exception.AlreadyReservationException;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;

    @Embedded
    private UnitAmount amount;

    @Enumerated(EnumType.STRING)
    private SellType sellType;

    private boolean reserved;

    private Long enrollmentId;

    @Builder
    public RegisteredBook(Long bookId, UnitAmount amount, SellType sellType, Long enrollmentId) {
        this.bookId = bookId;
        this.amount = amount;
        this.sellType = sellType;
        this.enrollmentId = enrollmentId;
    }

    public void reserve() {
        if(isReserved()) {
            throw new AlreadyReservationException("이미 예약되었습니다.");
        }

        reserved = true;
    }

    public void cancel() {
        if(isCanceled()) {
            throw new AlreadyReservationException("이미 예약 취소되었습니다.");
        }

        reserved = false;
    }

    private boolean isReserved() {
        return reserved;
    }

    private boolean isCanceled() {
        return !reserved;
    }
}
