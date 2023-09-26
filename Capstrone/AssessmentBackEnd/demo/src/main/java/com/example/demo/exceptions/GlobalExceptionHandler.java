package com.example.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final Map<String, String> handleEmptyInput(
        final MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
          errorMap.put(error.getField(), error.getDefaultMessage());
        });
        errorMap.put("StatusCode", "400");
        return errorMap;
    }
    @ExceptionHandler(AlreadyExistException.class)
    public final ResponseEntity<ErrorResponse> handleAlreadyExistException(final
            AlreadyExistException exception) {
        String message = exception.getMessage();
        Integer errorCode = HttpStatus.FOUND.value();
        ErrorResponse errorResponse=new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.FOUND);
   }
    
    @ExceptionHandler(AllNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleAllNotFoundException(final
            AllNotFoundException exception) {
        String message = exception.getMessage();
        Integer errorCode = HttpStatus.NO_CONTENT.value();
        ErrorResponse errorResponse=new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.NO_CONTENT);
   }
    
    @ExceptionHandler(EmailDoesNotExistException.class)
    public final ResponseEntity<ErrorResponse> handleEmailDoesNotExistException(final
            EmailDoesNotExistException exception) {
        String message = exception.getMessage();
        Integer errorCode = HttpStatus.NOT_FOUND.value();
        ErrorResponse errorResponse=new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.NOT_FOUND);
   }
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleNotFoundExceptionException(final
            NotFoundException exception) {
        String message = exception.getMessage();
        Integer errorCode = HttpStatus.CONFLICT.value();
        ErrorResponse errorResponse=new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.CONFLICT);
   }
    
    @ExceptionHandler(PasswordMissMatchException.class)
    public final ResponseEntity<ErrorResponse> handlePasswordMissMatchException(final
            PasswordMissMatchException exception) {
        String message = exception.getMessage();
        Integer errorCode = HttpStatus.NOT_FOUND.value();
        ErrorResponse errorResponse=new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.NOT_FOUND);
   }
    @ExceptionHandler(DuplicateEmailException.class)
    public final ResponseEntity<ErrorResponse> handleDuplicateEmailException(final
            DuplicateEmailException exception) {
        String message = exception.getMessage();
        Integer errorCode = HttpStatus.FOUND.value();
        ErrorResponse errorResponse=new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.FOUND);
   }
    
    
}
