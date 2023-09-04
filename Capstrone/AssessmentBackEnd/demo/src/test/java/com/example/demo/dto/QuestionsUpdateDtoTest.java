package com.example.demo.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionsUpdateDtoTest {

    @Test
    void testSetterGetter() {
        QuestionsUpdateDto questionsDto = new QuestionsUpdateDto();
        questionsDto.setQuestion("java is");
        questionsDto.setOption1("oops");
        questionsDto.setOption2("popl");
        questionsDto.setOption3("none");
        questionsDto.setOption4("both");
        questionsDto.setCorrectOption("oops");
        assertEquals("java is",questionsDto.getQuestion());
        assertEquals("oops",questionsDto.getOption1());
        assertEquals("popl",questionsDto.getOption2());
        assertEquals("none",questionsDto.getOption3());
        assertEquals("both",questionsDto.getOption4());
        assertEquals("oops",questionsDto.getCorrectOption());
    }
    @Test
    void testNoArgsConstructor() {
    QuestionsUpdateDto questionsDto = new QuestionsUpdateDto();
    assertEquals(null,questionsDto.getQuestion());
    assertEquals(null,questionsDto.getOption1());
    assertEquals(null,questionsDto.getOption2());
    assertEquals(null,questionsDto.getOption3());
    assertEquals(null,questionsDto.getOption4());
    assertEquals(null,questionsDto.getCorrectOption());
    }
}
