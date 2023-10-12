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
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.demo.controlleradvice.GlobalExceptionHandler;
import com.example.demo.response.Response;

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
        ResponseEntity<Response> responseEntity = globalhandler.handleAlreadyExistException(exception);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        Response errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.FOUND.value(), errorResponse.getCode());
        assertEquals("Resource already exists", errorResponse.getMessage());
    }
    
    @Test
    public void testHandleAllNotFoundException() {
        AllNotFoundException exception = new AllNotFoundException("Resource not found");
        ResponseEntity<Response> responseEntity = globalhandler.handleAllNotFoundException(exception);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        Response errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.CONFLICT.value(), errorResponse.getCode());
        assertEquals("Resource not found", errorResponse.getMessage());
    }
    @Test
    public void testHandleEmailDoesNotExistException() {
        EmailDoesNotExistException exception = new EmailDoesNotExistException("Email not exist");
        ResponseEntity<Response> responseEntity = globalhandler.handleEmailDoesNotExistException(exception);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        Response errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.UNAUTHORIZED.value(), errorResponse.getCode());
        assertEquals("Email not exist", errorResponse.getMessage());
    }
    @Test
    public void testHandleNotFoundException() {
        NotFoundException exception = new NotFoundException("not found");
        ResponseEntity<Response> responseEntity = globalhandler.handleNotFoundExceptionException(exception);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        Response errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.CONFLICT.value(), errorResponse.getCode());
        assertEquals("not found", errorResponse.getMessage());
    }
    @Test
    public void testHandlePasswordMissMatchException() {
        PasswordMissMatchException exception = new PasswordMissMatchException("password miss match");
        ResponseEntity<Response> responseEntity = globalhandler.handlePasswordMissMatchException(exception);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        Response errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.UNAUTHORIZED.value(), errorResponse.getCode());
        assertEquals("password miss match", errorResponse.getMessage());
    }
    @Test
    public void testHandleDuplicateEmailException() {
        DuplicateEmailException exception = new DuplicateEmailException("duplicate email");
        ResponseEntity<Response> responseEntity = globalhandler.handleDuplicateEmailException(exception);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        Response errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.FOUND.value(), errorResponse.getCode());
        assertEquals("duplicate email", errorResponse.getMessage());
    }
    
    @Test
    public void  testHttpMessageNotReadableException() {
        @SuppressWarnings("deprecation")
        HttpMessageNotReadableException exception=new HttpMessageNotReadableException("gender should be male,female or other");
        ResponseEntity<Response<HttpMessageNotReadableException>> responseEntity = globalhandler.handleHttpMessageNotReadableException(exception);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        Response errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.CONFLICT.value(), errorResponse.getCode());
        assertEquals("gender should be male,female or other", errorResponse.getMessage());
        
    }

}
