package com.example.demo.controlleradvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.exceptions.EmailDoesNotExistException;
import com.example.demo.exceptions.ErrorResponse;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.PasswordMissMatchException;
/**
 * Global exception handler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * handle empty inputs.
     * @param exception exception
     * @return response
     */
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
    /**
     * AlreadyExistException handler.
     * @param exception argument
     * @return response
     */
    @ExceptionHandler(AlreadyExistException.class)
    public final ResponseEntity<ErrorResponse> handleAlreadyExistException(final
            AlreadyExistException exception) {
        String message = exception.getMessage();
        Integer errorCode = HttpStatus.FOUND.value();
        ErrorResponse errorResponse = new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.FOUND);
   }
    /**
     * AllNotFoundException handler.
     * @param exception argument
     * @return reaponse
     */
    @ExceptionHandler(AllNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleAllNotFoundException(final
            AllNotFoundException exception) {
        String message = exception.getMessage();
        Integer errorCode = HttpStatus.NO_CONTENT.value();
        ErrorResponse errorResponse = new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.NO_CONTENT);
   }
    /**
     * EmailDoesNotExistException handler.
     * @param exception argument
     * @return response
     */
    @ExceptionHandler(EmailDoesNotExistException.class)
    public final ResponseEntity<ErrorResponse>
    handleEmailDoesNotExistException(final
            EmailDoesNotExistException exception) {
        String message = exception.getMessage();
        Integer errorCode = HttpStatus.UNAUTHORIZED.value();
        ErrorResponse errorResponse = new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.UNAUTHORIZED);
   }
    /**
     * NotFoundException handler.
     * @param exception argument
     * @return response
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorResponse>
    handleNotFoundExceptionException(final
            NotFoundException exception) {
        String message = exception.getMessage();
        Integer errorCode = HttpStatus.CONFLICT.value();
        ErrorResponse errorResponse = new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.CONFLICT);
   }
   /**
    * PasswordMissMatchException handler.
    * @param exception argument
    * @return response
    */
    @ExceptionHandler(PasswordMissMatchException.class)
    public final ResponseEntity<ErrorResponse>
    handlePasswordMissMatchException(final
            PasswordMissMatchException exception) {
        String message = exception.getMessage();
        Integer errorCode = HttpStatus.UNAUTHORIZED.value();
        ErrorResponse errorResponse = new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.UNAUTHORIZED);
   }
    /**
     * DuplicateEmailException handler.
     * @param exception argument
     * @return response
     */
    @ExceptionHandler(DuplicateEmailException.class)
    public final ResponseEntity<ErrorResponse>
    handleDuplicateEmailException(final
            DuplicateEmailException exception) {
        String message = exception.getMessage();
        Integer errorCode = HttpStatus.FOUND.value();
        ErrorResponse errorResponse = new ErrorResponse(errorCode, message);
   return new ResponseEntity<ErrorResponse>(errorResponse,
          HttpStatus.FOUND);
   }
}
