package com.example.demo.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordMissMatchExceptionTest {

    @Test
    void testPasswordMissMatchException() {
        String message="password must be same";
        PasswordMissMatchException exception=new PasswordMissMatchException(message);
        assertEquals(message,exception.getMessage());
    }

}
