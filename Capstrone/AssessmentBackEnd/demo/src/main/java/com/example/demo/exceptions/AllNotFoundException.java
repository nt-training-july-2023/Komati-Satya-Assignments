package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Not found exception class.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AllNotFoundException extends RuntimeException {
    /**
     * serialVersionUId variable.
     */
    private static final long serialVersionUID = 1L;
    /**
     * constructor.
     * @param message error message
     */
    public AllNotFoundException(final String message) {
        super(message);
    }
}
