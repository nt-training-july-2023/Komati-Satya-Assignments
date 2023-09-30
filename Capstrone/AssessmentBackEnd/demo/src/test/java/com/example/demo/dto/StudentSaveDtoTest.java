package com.example.demo.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentSaveDtoTest {

    @Test
    void testGetterSetter() {
        StudentSaveDto studentSaveDto=new StudentSaveDto();
        studentSaveDto.setUserName("Satya");
        studentSaveDto.setEmail("satya1919@nucleusteq.com");
        studentSaveDto.setDateOfBirth("24-02-2001");
        studentSaveDto.setGender("female");
        studentSaveDto.setPhoneNumber("8639924113");
        studentSaveDto.setPassword("Satya@1919");
        studentSaveDto.setRole("student");
        assertEquals("Satya",studentSaveDto.getUserName());
        assertEquals("satya1919@nucleusteq.com",studentSaveDto.getEmail());
        assertEquals("24-02-2001",studentSaveDto.getDateOfBirth());
        assertEquals("female",studentSaveDto.getGender());
        assertEquals("8639924113",studentSaveDto.getPhoneNumber());
    }

}
