package com.example.demo.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuizUpdateDtoTest {

    @Test
    void testNoargsConstructor() {
        QuizUpdateDto quizDto=new QuizUpdateDto();
        assertEquals(0,quizDto.getQuizId());
        assertEquals(null,quizDto.getTopicName());
        assertEquals(null,quizDto.getTopicDescription());
        assertEquals(0,quizDto.getPassMarks());
        assertEquals(0,quizDto.getMaxMarks());
        
    }
    @Test
    void testSetterGetter() {
        QuizUpdateDto quizDto=new QuizUpdateDto();
        quizDto.setQuizId(1);
        quizDto.setTopicName("arrays");
        quizDto.setTopicDescription("java");
        quizDto.setPassMarks(18);
        quizDto.setMaxMarks(30);
        assertEquals(1,quizDto.getQuizId());
        assertEquals("arrays",quizDto.getTopicName());
        assertEquals("java",quizDto.getTopicDescription());
        assertEquals(18,quizDto.getPassMarks());
        assertEquals(30,quizDto.getMaxMarks());
       }

}
