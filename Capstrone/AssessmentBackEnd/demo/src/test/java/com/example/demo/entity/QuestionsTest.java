package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionsTest {

    @Test
    void testGetterSetter() {
        Questions questions=new Questions();
        questions.setQuestion("what is array");
        questions.setOption1("a");
        questions.setOption2("b");
        questions.setOption3("c");
        questions.setOption4("d");
        questions.setCorrectOption("a");
        assertEquals("what is array",questions.getQuestion());
        assertEquals("a",questions.getOption1());
        assertEquals("b",questions.getOption2());
        assertEquals("c",questions.getOption3());
        assertEquals("d",questions.getOption4());
        assertEquals("a",questions.getCorrectOption());
    }
    @Test
    void testAllArgsConstructor() {
        Questions questions=new Questions("what is array","a","b","c","d","a");
        assertEquals("what is array",questions.getQuestion());
        assertEquals("a",questions.getOption1());
        assertEquals("b",questions.getOption2());
        assertEquals("c",questions.getOption3());
        assertEquals("d",questions.getOption4());
        assertEquals("a",questions.getCorrectOption());
    }

}
