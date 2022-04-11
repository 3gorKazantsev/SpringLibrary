package org.egorkazantsev.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorEntity {
    private String message;
    private int status;
    private String error;
    private ZonedDateTime timestamp;
}
