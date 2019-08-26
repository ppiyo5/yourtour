package net.nigne.yourtour.exception;

import net.nigne.yourtour.error.ErrorCode;

public class AlreadyReservationException extends BusinessException {

    public AlreadyReservationException(String message) {

        super(ErrorCode.ALREADY_RESERVATION, message);
    }

    public AlreadyReservationException() {

        super(ErrorCode.ALREADY_RESERVATION, ErrorCode.ALREADY_RESERVATION.getMessage());
    }
}
