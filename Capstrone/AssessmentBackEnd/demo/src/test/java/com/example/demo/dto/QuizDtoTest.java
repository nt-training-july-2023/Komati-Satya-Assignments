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
        quizDto.setCategoryId(10);
        assertEquals(1,quizDto.getQuizId());
        assertEquals("arrays",quizDto.getTopicName());
        assertEquals("java",quizDto.getTopicDescription());
        assertEquals(10,quizDto.getCategoryId());
    }
    @Test
    void testNoargsConstructor() {
        QuizDto quizDto=new QuizDto();
        assertEquals(0,quizDto.getQuizId());
        assertEquals(null,quizDto.getTopicName());
        assertEquals(null,quizDto.getTopicDescription());
        assertEquals(0,quizDto.getCategoryId());
    }
    @Test
    void testAllArgsConstructor() {
        QuizDto quizDto=new QuizDto(1,"arrays","java",18,6); 
        assertEquals(1,quizDto.getQuizId());
        assertEquals("arrays",quizDto.getTopicName());
        assertEquals("java",quizDto.getTopicDescription());
        assertEquals(18,quizDto.getCategoryId());
    }
    
    
}