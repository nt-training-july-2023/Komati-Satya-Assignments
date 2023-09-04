package com.example.demo.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ResponseeTest {

    @Test
    void testGenerateResponce() {
       String message="added the data";
       HttpStatus status=HttpStatus.OK;
       String Obj="data";
       Object res="test data";
       ResponseEntity<Object> response= Responsee.generateResponce(message, status, Obj, res);
       assertNotNull(response);
       assertEquals(status,response.getStatusCode());
    }

}
