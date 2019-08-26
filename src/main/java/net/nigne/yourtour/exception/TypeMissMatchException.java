package net.nigne.yourtour.exception;

import net.nigne.yourtour.error.ErrorCode;

public class TypeMissMatchException extends BusinessException {

    public TypeMissMatchException(String message) {

        super(ErrorCode.INVALID_TYPE_VALUE, message);
    }
}
