package com.example.demo.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DuplicateEmailExceptionTest {

    @Test
    void testDuplicateEmailException() {
        String message="Email already exist";
        DuplicateEmailException exception=new DuplicateEmailException(message);
        assertEquals(message,exception.getMessage());
    }

}
