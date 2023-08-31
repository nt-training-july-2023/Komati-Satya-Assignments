package com.example.demo.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlreadyExistExceptionTest {

    @Test
    void testAlreadyExistException() {
        String message="Category already present";
        AlreadyExistException exception=new AlreadyExistException(message);
        assertEquals(message,exception.getMessage());
    }

}
