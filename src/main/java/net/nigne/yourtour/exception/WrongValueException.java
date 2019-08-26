package net.nigne.yourtour.exception;

import net.nigne.yourtour.error.ErrorCode;

public class WrongValueException extends BusinessException {

    public WrongValueException(String message) {
        super(ErrorCode.INVALID_INPUT_VALUE, message);
    }
}
