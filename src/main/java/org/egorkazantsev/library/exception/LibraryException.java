package org.egorkazantsev.library.exception;

import org.springframework.util.Assert;

public class LibraryException extends RuntimeException {

    public LibraryException(String message) {
        super(message);
    }

    protected static void checkTypeAndId(String type, Object id) {
        Assert.hasText(type, "Type cannot be empty");
        Assert.notNull(id, "ID cannot be null");
        Assert.hasText(id.toString(), "ID cannot be empty");
    }
}
