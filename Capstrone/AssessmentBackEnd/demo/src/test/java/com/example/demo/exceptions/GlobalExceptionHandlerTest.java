package com.example.demo.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

class GlobalExceptionHandlerTest {

    @InjectMocks
    GlobalExceptionHandler globalhandler;
    @Mock
    MethodArgumentNotValidException methodArgumentNotValidException;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testHandleEmptyInput() {
        BindingResult bindingResult = mockBindingResult();
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        Map<String, String> errorMap = globalhandler.handleEmptyInput(methodArgumentNotValidException);
        assertEquals("400", errorMap.get("StatusCode"));
    }
    private BindingResult mockBindingResult() {
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("objectName", "field1", "Field1Error"));
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
        return bindingResult;
    }
    
    @Test
    public void testHandleAlreadyExistException() {
        AlreadyExistException exception = new AlreadyExistException("Resource already exists");
        ResponseEntity<ErrorResponse> responseEntity = globalhandler.handleAlreadyExistException(exception);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.FOUND.value(), errorResponse.getErrorCode());
        assertEquals("Resource already exists", errorResponse.getErrorMessage());
    }
    
    @Test
    public void testHandleAllNotFoundException() {
        AllNotFoundException exception = new AllNotFoundException("Resource not found");
        ResponseEntity<ErrorResponse> responseEntity = globalhandler.handleAllNotFoundException(exception);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.NO_CONTENT.value(), errorResponse.getErrorCode());
        assertEquals("Resource not found", errorResponse.getErrorMessage());
    }
    @Test
    public void testHandleEmailDoesNotExistException() {
        EmailDoesNotExistException exception = new EmailDoesNotExistException("Email not exist");
        ResponseEntity<ErrorResponse> responseEntity = globalhandler.handleEmailDoesNotExistException(exception);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.UNAUTHORIZED.value(), errorResponse.getErrorCode());
        assertEquals("Email not exist", errorResponse.getErrorMessage());
    }
    @Test
    public void testHandleNotFoundException() {
        NotFoundException exception = new NotFoundException("not found");
        ResponseEntity<ErrorResponse> responseEntity = globalhandler.handleNotFoundExceptionException(exception);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.CONFLICT.value(), errorResponse.getErrorCode());
        assertEquals("not found", errorResponse.getErrorMessage());
    }
    @Test
    public void testHandlePasswordMissMatchException() {
        PasswordMissMatchException exception = new PasswordMissMatchException("password miss match");
        ResponseEntity<ErrorResponse> responseEntity = globalhandler.handlePasswordMissMatchException(exception);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.UNAUTHORIZED.value(), errorResponse.getErrorCode());
        assertEquals("password miss match", errorResponse.getErrorMessage());
    }
    @Test
    public void testHandleDuplicateEmailException() {
        DuplicateEmailException exception = new DuplicateEmailException("duplicate email");
        ResponseEntity<ErrorResponse> responseEntity = globalhandler.handleDuplicateEmailException(exception);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.FOUND.value(), errorResponse.getErrorCode());
        assertEquals("duplicate email", errorResponse.getErrorMessage());
    }
}
