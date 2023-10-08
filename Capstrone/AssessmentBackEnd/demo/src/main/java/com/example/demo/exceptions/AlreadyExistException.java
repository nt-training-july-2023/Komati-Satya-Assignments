package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * already exist exception.
 */
@ResponseStatus(HttpStatus.FOUND)
public class AlreadyExistException extends RuntimeException {
    /**
     * constructor.
     * @param message error message
     */
    public AlreadyExistException(final String message) {
        super(message);
    }
}
