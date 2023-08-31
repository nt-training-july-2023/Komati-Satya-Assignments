package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Duplicate email exception.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmailException extends RuntimeException {
    /**
     * serial version id variable.
     */
    private static final long serialVersionUID = 1L;
    /**
     * constructor.
     * @param message error message
     */
    public DuplicateEmailException(final String message) {
        super(message);
    }
}
