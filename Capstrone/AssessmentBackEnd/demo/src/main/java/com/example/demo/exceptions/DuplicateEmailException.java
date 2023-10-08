package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Duplicate email exception.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmailException extends RuntimeException {
    /**
     * constructor.
     * @param message error message
     */
    public DuplicateEmailException(final String message) {
        super(message);
    }
}
