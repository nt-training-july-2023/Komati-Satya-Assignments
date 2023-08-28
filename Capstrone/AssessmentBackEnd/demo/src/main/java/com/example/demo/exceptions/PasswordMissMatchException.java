package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PasswordMissMatchException extends RuntimeException{

	
	private static final long serialVersionID=1L;
	public PasswordMissMatchException(String message) {
		super(message);
	}
}
