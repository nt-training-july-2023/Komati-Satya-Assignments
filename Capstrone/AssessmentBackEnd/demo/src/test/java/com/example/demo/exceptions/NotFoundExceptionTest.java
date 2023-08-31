package com.example.demo.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {

    @Test
    void testNotFoundException() {
        String message="User not found";
        NotFoundException exception=new NotFoundException(message);
        assertEquals(message,exception.getMessage());
    }

}
