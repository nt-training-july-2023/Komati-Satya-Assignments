package com.example.demo.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionsDtoForAssertionsTest {

    @Test
    void testGetterSetter() {
        QuestionsDtoForAssertions questionsDto = new QuestionsDtoForAssertions();
        questionsDto.setQuestion("what is java");
        questionsDto.setOption1("oops");
        questionsDto.setOption2("popl");
        questionsDto.setCorrectOption("oopl");
        questionsDto.setQuestionId(1);
        questionsDto.setQuizId(1);
        assertEquals("what is java",questionsDto.getQuestion());
        assertEquals("oops",questionsDto.getOption1());
        assertEquals("popl",questionsDto.getOption2());
        assertEquals("oopl",questionsDto.getCorrectOption());
        assertEquals(1,questionsDto.getQuizId());
    }
    @Test
    void testNoArgsConstructor() {
    QuestionsDtoForAssertions questionsDto = new  QuestionsDtoForAssertions();
    assertEquals(null,questionsDto.getQuestion());
    assertEquals(null,questionsDto.getOption1());
    assertEquals(null,questionsDto.getOption2());
    assertEquals(null,questionsDto.getCorrectOption());
    }
    
    @Test
    void testAllArgsConstructor() {
        QuestionsDtoForAssertions questionsDto = new QuestionsDtoForAssertions("java is?","oops","popl","oopl",1,7);
        assertEquals("java is?",questionsDto.getQuestion());
        assertEquals("oops",questionsDto.getOption1());
        assertEquals("popl",questionsDto.getOption2());
        assertEquals("oopl",questionsDto.getCorrectOption());
        assertEquals(1,questionsDto.getQuizId());
        assertEquals(7,questionsDto.getQuestionId());
    }
}
