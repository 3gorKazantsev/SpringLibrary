package org.egorkazantsev.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityAlreadyExistsException extends LibraryException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String type, Object id) {
        this(formatMessage(type, id));
    }

    private static String formatMessage(String type, Object id) {
        checkTypeAndId(type, id);
        return String.format("Entity '%s' with ID '%s' already exists", type, id);
    }
}
