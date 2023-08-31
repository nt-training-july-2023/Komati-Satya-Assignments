package com.example.demo.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * PasswordMissmatch exception.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class PasswordMissMatchException extends RuntimeException {
  /**
   * serialVersionUID variable.
   */
  private static final long serialVersionUID = 1L;
  /**
   * constructor.
   * @param message error message
   */
  public PasswordMissMatchException(final String message) {
    super(message);
  }
}
