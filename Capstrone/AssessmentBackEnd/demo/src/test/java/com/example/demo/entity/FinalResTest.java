package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FinalResTest {

    @Test
    void testGetterSetter() {
        FinalRes finalRess=new FinalRes();
        finalRess.setCategoryName("java");
        finalRess.setDateAndTime("23-10-23");
        finalRess.setEmail("satya@nucleusteq.com");
        finalRess.setMarks(97);
        finalRess.setFinalId(12);
        finalRess.setQuizTopic("Array");
        finalRess.setUserId(1);
        finalRess.setFirstName("satya");
        finalRess.setMaxMarks(101);
        
        assertEquals("java",finalRess.getCategoryName());
        assertEquals("23-10-23",finalRess.getDateAndTime());
        assertEquals("satya@nucleusteq.com",finalRess.getEmail());
        assertEquals(101,finalRess.getMaxMarks());
        assertEquals("satya",finalRess.getFirstName());
        assertEquals("Array",finalRess.getQuizTopic());
    }

}
