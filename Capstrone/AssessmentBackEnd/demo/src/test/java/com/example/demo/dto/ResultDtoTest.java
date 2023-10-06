package com.example.demo.dto;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResultDtoTest {

    @Test
    void testGetterSetter() {
       ResultDto resultDto=new ResultDto();
       resultDto.setAttemptedQuestions(10);
       resultDto.setCategoryId(1);
       resultDto.setCategoryName("java");
       resultDto.setDateAndTime("12-08-2023");
       resultDto.setEmail("satya@nucleusteq.com");
       resultDto.setMaxMarks(10);
       resultDto.setObtainMarks(6);
       resultDto.setQuizName("arrays");
       resultDto.setTotalQuestions(10);
       assertEquals(10,resultDto.getAttemptedQuestions());
       assertEquals(1,resultDto.getCategoryId());
       assertEquals("java",resultDto.getCategoryName());
       assertEquals("12-08-2023",resultDto.getDateAndTime());
       assertEquals("satya@nucleusteq.com",resultDto.getEmail());
    }
    @Test
    void testAllArgsConstructor() {
        ResultDto resultDto = new ResultDto(101,"23-10-23","pass",19, "Madhuri","satya@nucleusteq.com","Array",
                "Java",97,9,10,1,12);
        assertEquals(19,resultDto.getAttemptedQuestions());
        assertEquals(9,resultDto.getCategoryId());
        assertEquals("Java",resultDto.getCategoryName());
        assertEquals("23-10-23",resultDto.getDateAndTime());
        assertEquals("satya@nucleusteq.com",resultDto.getEmail());
    }

}
