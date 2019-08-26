package net.nigne.yourtour.exception;

import net.nigne.yourtour.error.ErrorCode;

public class MaxOverReservationException extends BusinessException {

    public MaxOverReservationException(String message) {

        super(ErrorCode.MAX_OVER_RESERVATION, message);
    }
}
