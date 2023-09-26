package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(AlreadyExistException.class)
    public final ResponseEntity<ErrorResponse> handleAlreadyExistException(final
            AlreadyExistException exception) {
        String message = exception.getMessage();
        HttpStatus errorCode = HttpStatus.FOUND;
        ErrorResponse errorResponse=new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.FOUND);
   }
    
    @ExceptionHandler(AllNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleAllNotFoundException(final
            AllNotFoundException exception) {
        String message = exception.getMessage();
        HttpStatus errorCode = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse=new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.NOT_FOUND);
   }
    
    @ExceptionHandler(EmailDoesNotExistException.class)
    public final ResponseEntity<ErrorResponse> handleEmailDoesNotExistException(final
            EmailDoesNotExistException exception) {
        String message = exception.getMessage();
        HttpStatus errorCode = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse=new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.NOT_FOUND);
   }
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleNotFoundExceptionException(final
            NotFoundException exception) {
        String message = exception.getMessage();
        HttpStatus errorCode = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse=new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.NOT_FOUND);
   }
    
    @ExceptionHandler(PasswordMissMatchException.class)
    public final ResponseEntity<ErrorResponse> handlePasswordMissMatchException(final
            PasswordMissMatchException exception) {
        String message = exception.getMessage();
        HttpStatus errorCode = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse=new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.NOT_FOUND);
   }
    @ExceptionHandler(DuplicateEmailException.class)
    public final ResponseEntity<ErrorResponse> handleDuplicateEmailException(final
            DuplicateEmailException exception) {
        String message = exception.getMessage();
        HttpStatus errorCode = HttpStatus.FOUND;
        ErrorResponse errorResponse=new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.FOUND);
   }
    
    
}
