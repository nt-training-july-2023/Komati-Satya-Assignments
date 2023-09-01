package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class QuizTest {

    @Test
    public void testGetterSetter() {
        Quiz q=new Quiz();
        q.setMaxMarks(10);
        assertEquals(10,q.getMaxMarks());
        q.setPassMarks(8);
        assertEquals(8,q.getPassMarks());
        q.setQuizId(1);
        assertEquals(1,q.getQuizId());
        q.setTopicName("variables");
        assertEquals("variables",q.getTopicName());
        q.setTopicDescription("java variables");
        assertEquals("java variables",q.getTopicDescription());
        Questions q1=new Questions();
        Questions q2=new Questions();
        List<Questions> questions=new ArrayList<>();
        questions.add(q1);
        questions.add(q2);
        q.setQue(questions);
        assertEquals(2,q.getQue().size());
        Category c=new Category();
        c.setCategoryId(1);
        c.setCategoryName("java");
        c.setCategoryDescription("java basics");
        q.setCate(c);
        assertNotEquals(c,q.getCate());
        StudentResult sr=new StudentResult();
        List<StudentResult> result=new ArrayList<StudentResult>();
        q.setSe(result);
        assertEquals(0,q.getSe().size());
    }
    @Test
    public void testNoargsConstructor() {
        Quiz q=new Quiz();
        assertEquals(0,q.getQuizId());
        assertEquals(0,q.getMaxMarks());
        assertEquals(0,q.getPassMarks());
        assertNull(q.getTopicName());
        assertNull(q.getTopicDescription());
    }
    
    @Test
    public void testAllArgsConstructor() {
        int id=1;
        String topic="java";
        String topicDescription="java basics";
        int pass=10;
        int marks=15;
        Quiz q=new Quiz(id,topic,topicDescription,marks,pass);
        assertEquals(id,q.getQuizId());
        assertEquals(marks,q.getMaxMarks());
        assertEquals(pass,q.getPassMarks());
        assertEquals(topic,q.getTopicName());
        assertEquals(topicDescription,q.getTopicDescription());
    }
}
