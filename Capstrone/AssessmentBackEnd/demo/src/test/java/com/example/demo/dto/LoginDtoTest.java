package com.example.demo.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginDtoTest {

    @Test
    void testAllArgsConstructor() {
       LoginDto loginDto=new LoginDto("Satya@1919","satya1919@nucleusteq.com");
       assertEquals("Satya@1919",loginDto.getPassword());
       assertEquals("satya1919@nucleusteq.com",loginDto.getEmail());
    }

}
