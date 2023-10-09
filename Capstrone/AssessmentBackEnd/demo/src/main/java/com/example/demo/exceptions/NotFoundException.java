package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    /**
     * constructor.
     * @param message error message
     */
    public NotFoundException(final String message) {
        super(message);
    }
}
