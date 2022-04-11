package org.egorkazantsev.library.exception.handler;

import org.egorkazantsev.library.exception.ErrorEntity;
import org.egorkazantsev.library.exception.LibraryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class LibraryExceptionHandler {

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
