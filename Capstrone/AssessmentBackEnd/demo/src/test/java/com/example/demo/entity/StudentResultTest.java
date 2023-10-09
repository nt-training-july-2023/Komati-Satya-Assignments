package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentResultTest {

    @Test
    void testGetterSetter() {
        StudentResult studentResult=new StudentResult();
        studentResult.setResultId(1);
        Quiz q=new Quiz();
        q.setTopicName("variables");
        studentResult.setQuiz(q);
        Student student=new Student();
        student.setUserId(18);
        studentResult.setStudentResult(student);
        Category c=new Category();
        c.setCategoryName("java");
        c.setCategoryId(13);
        studentResult.setCategoryId(13);
        assertEquals(1,studentResult.getResultId());
        assertEquals(13,studentResult.getCategoryId());
    }
    @Test
    void testNoArgsConstructor() {
        StudentResult studentResult=new StudentResult();
        assertEquals(0,studentResult.getResultId());
        assertEquals(0,studentResult.getCategoryId());
        assertEquals(null,studentResult.getDateAndTime());
        }

}
