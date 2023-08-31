package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * already exist exception.
 */
@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class AlreadyExistException extends RuntimeException {
    /**
     * serial version id variable.
     */
    private static final long serialVersionUID = 1L;
    /**
     * constructor.
     * @param message error message
     */
    public AlreadyExistException(final String message) {
        super(message);
    }
}
