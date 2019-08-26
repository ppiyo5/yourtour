package net.nigne.yourtour.error;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import net.nigne.yourtour.exception.BusinessException;
import net.nigne.yourtour.response.ApiResponse;
import net.nigne.yourtour.response.ApiResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiResponse<ErrorMessageCollection> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        ApiResponse<ErrorMessageCollection> exception = ApiResponse.createException(
                ErrorCode.INVALID_INPUT_VALUE,
                new ErrorMessageCollection(e.getBindingResult().getFieldErrors(), e.getBindingResult().getGlobalErrors()));

        log.error("[{}] {}", ApiResponseCode.BAD_PARAMETER.getId(), exception);
        return exception;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MismatchedInputException.class)
    protected ApiResponse<String> handleMismatchedInputException(MismatchedInputException e) {

        ApiResponse<String> exception = ApiResponse.createException(ErrorCode.INVALID_TYPE_VALUE);

        log.error("[{}] {}", ApiResponseCode.BAD_PARAMETER.getId(), exception);
        return exception;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ApiResponse<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        ApiResponse<String> exception = ApiResponse.createException(ErrorCode.NOT_REQUEST_PARAMETER);

        log.error("[{}] {}", ApiResponseCode.BAD_PARAMETER.getId(), exception);
        return exception;
    }

    @ExceptionHandler(BindException.class)
    protected String handleBindException(BindException e) {
        return "NO";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    protected ApiResponse<String> handleBusinessException(BusinessException e) {

        ApiResponse<String> exception = ApiResponse.createException(e.getErrorCode(), e.getMessage());

        log.error("[{}] {}", ApiResponseCode.BAD_PARAMETER.getId(), exception.getMessage());
        return exception;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return "NO";
    }


    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return "NO";
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected String handleAccessDeniedException(AccessDeniedException e) {
        return "NO";
    }
}
