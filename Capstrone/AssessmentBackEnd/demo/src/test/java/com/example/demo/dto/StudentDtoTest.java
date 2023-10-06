package com.example.demo.dto;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentDtoTest {

    @Test
    void testGetterSetter() {
        StudentDto studentDto=new StudentDto();
        studentDto.setUserName("Satya");
        studentDto.setEmail("satya1919@nucleusteq.com");
        studentDto.setDateOfBirth("24-02-2001");
        studentDto.setGender("female");
        studentDto.setPhoneNumber("8639924113");
        studentDto.setRole("student");
        assertEquals("Satya",studentDto.getUserName());
        assertEquals("satya1919@nucleusteq.com",studentDto.getEmail());
        assertEquals("24-02-2001",studentDto.getDateOfBirth());
        assertEquals("female",studentDto.getGender());
    }

}
