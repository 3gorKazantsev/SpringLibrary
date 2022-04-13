package org.egorkazantsev.library.exception.handler;

import org.egorkazantsev.library.exception.ErrorEntity;
import org.egorkazantsev.library.exception.LibraryException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class LibraryExceptionHandler {

    // ошибки Spring
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleSpringException(Exception ex) {
        ErrorEntity error = new ErrorEntity();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setTimestamp(ZonedDateTime.now());

        String defaultMessage = ex.getMessage();

        if (ex instanceof MethodArgumentTypeMismatchException) {
            defaultMessage = "Argument type mismatch";
            error.setStatus(HttpStatus.BAD_REQUEST.value());
            error.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }

        // TODO возможно стоит переделать
        // обработка PSQL ошибок
        if (ex instanceof DataIntegrityViolationException) {
            defaultMessage = ex.getClass().getName();
            error.setStatus(HttpStatus.BAD_REQUEST.value());
            error.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }

        error.setMessage(defaultMessage);

        return new ResponseEntity<>(error, HttpStatus.valueOf(error.getStatus()));
    }

    // внутренние ошибки Library
    @ExceptionHandler(LibraryException.class)
    public ResponseEntity<Object> handleLibraryException(LibraryException ex) {
        ErrorEntity errorEntity = new ErrorEntity(
                ex.getMessage(),
                getHttpStatus(ex).value(),
                getHttpStatus(ex).getReasonPhrase(),
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(errorEntity, getHttpStatus(ex));
    }

    private static HttpStatus getHttpStatus(LibraryException ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            httpStatus = ex.getClass().getAnnotation(ResponseStatus.class).value();
        } catch (Exception exception) {
        }
        return httpStatus;
    }
}
