package com.example.demo.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionsDtoTest {

    @Test
    void testAllArgsConstructor() {
        QuestionsDto questionsDto = new QuestionsDto("java is?","oops","popl","none","both","oopl",1);
        assertEquals("java is?",questionsDto.getQuestion());
        assertEquals("oops",questionsDto.getOption1());
        assertEquals("popl",questionsDto.getOption2());
        assertEquals("none",questionsDto.getOption3());
        assertEquals("both",questionsDto.getOption4());
        assertEquals("oopl",questionsDto.getCorrectOption());
        assertEquals(1,questionsDto.getQuizId());
    }

}
