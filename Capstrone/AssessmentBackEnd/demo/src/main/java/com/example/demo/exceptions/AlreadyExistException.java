package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class AlreadyExistException extends RuntimeException{

	private static final long serialVersionID=1L;
	public AlreadyExistException(String message) {
		super(message);
	}
}
