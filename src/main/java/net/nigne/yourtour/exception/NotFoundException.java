package net.nigne.yourtour.exception;

import net.nigne.yourtour.error.ErrorCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(String message) {
        super(ErrorCode.ENTITY_NOT_FOUND, message);
    }
}
