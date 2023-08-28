package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailDoesNotExistException extends RuntimeException{
	private static final long serialVersionID=1L;
	public EmailDoesNotExistException(String message) {
		super(message);
	}

}
