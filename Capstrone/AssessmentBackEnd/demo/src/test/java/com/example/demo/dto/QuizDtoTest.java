package com.example.demo.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuizDtoTest {

    @Test
    void testSetterGetter() {
        QuizDto quizDto=new QuizDto();
        quizDto.setQuizId(1);
        quizDto.setTopicName("arrays");
        quizDto.setTopicDescription("java");
        quizDto.setPassMarks(18);
        quizDto.setMaxMarks(30);
        quizDto.setCategoryId(10);
        assertEquals(1,quizDto.getQuizId());
        assertEquals("arrays",quizDto.getTopicName());
        assertEquals("java",quizDto.getTopicDescription());
        assertEquals(18,quizDto.getPassMarks());
        assertEquals(30,quizDto.getMaxMarks());
        assertEquals(10,quizDto.getCategoryId());
    }
    @Test
    void testNoargsConstructor() {
        QuizDto quizDto=new QuizDto();
        assertEquals(0,quizDto.getQuizId());
        assertEquals(null,quizDto.getTopicName());
        assertEquals(null,quizDto.getTopicDescription());
        assertEquals(0,quizDto.getPassMarks());
        assertEquals(0,quizDto.getMaxMarks());
        assertEquals(0,quizDto.getCategoryId());
    }
    @Test
    void testAllArgsConstructor() {
        QuizDto quizDto=new QuizDto(1,"arrays","java",18,30,1); 
        assertEquals(1,quizDto.getQuizId());
        assertEquals("arrays",quizDto.getTopicName());
        assertEquals("java",quizDto.getTopicDescription());
        assertEquals(30,quizDto.getPassMarks());
        assertEquals(18,quizDto.getMaxMarks());
        assertEquals(1,quizDto.getCategoryId());
    }
    
    
}