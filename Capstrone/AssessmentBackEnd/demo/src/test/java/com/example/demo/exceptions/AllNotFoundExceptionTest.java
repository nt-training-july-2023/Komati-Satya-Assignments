package com.example.demo.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AllNotFoundExceptionTest {

    @Test
    void testAllNotFoundException() {
        String message="No user is present";
        AllNotFoundException exception=new AllNotFoundException(message);
        assertEquals(message,exception.getMessage());
    }

}
