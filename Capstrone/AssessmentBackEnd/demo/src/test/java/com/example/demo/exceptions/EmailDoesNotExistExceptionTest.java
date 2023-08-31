package com.example.demo.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmailDoesNotExistExceptionTest {

    @Test
    void testEmailDoesNotExistException() {
        String message="Email not exist";
        EmailDoesNotExistException exception=new EmailDoesNotExistException(message);
        assertEquals(message,exception.getMessage());
    }

}
