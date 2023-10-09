package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * email does not exist exception.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class EmailDoesNotExistException extends RuntimeException {
    /**
     * constructor.
     * @param message error message
     */
    public EmailDoesNotExistException(final String message) {
        super(message);
    }
}
