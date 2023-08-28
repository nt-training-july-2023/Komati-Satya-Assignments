package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public class AllNotFoundException extends RuntimeException {

		private static final long serialVersionID=1L;
		public AllNotFoundException(String message) {
			super(message);
		}
	}

