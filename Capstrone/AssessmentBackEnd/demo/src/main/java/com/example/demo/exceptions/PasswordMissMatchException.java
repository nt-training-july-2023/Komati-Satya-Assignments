package com.example.demo.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * PasswordMissmatch exception.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class PasswordMissMatchException extends RuntimeException {
  /**
   * constructor.
   * @param message error message
   */
  public PasswordMissMatchException(final String message) {
    super(message);
  }
}
