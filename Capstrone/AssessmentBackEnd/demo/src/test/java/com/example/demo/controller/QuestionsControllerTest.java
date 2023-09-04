package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.QuestionsDto;
import com.example.demo.dto.QuestionsUpdateDto;

import com.example.demo.entity.Questions;

import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.serviceImp.QuestionServiceImp;


class QuestionsControllerTest {

    @Mock
    private QuestionServiceImp questionService;
    @InjectMocks
    private QuestionsController questionController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddQuestionSuccess() {
        Questions question=new Questions();
        question.setQuestion("java is?");
        QuestionsDto questionsDto=new QuestionsDto();
        questionsDto.setQuestion("java is?");
        when(questionService.addQuestion(question)).thenReturn(questionsDto);
        ResponseEntity<Object> response=questionController.addQuestion(question);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testAddQuestionError() {
        Questions question=new Questions();
        question.setQuestion("java is?");
        QuestionsDto questionsDto=new QuestionsDto();
        questionsDto.setQuestion("java is?");
        when(questionService.addQuestion(question)).thenThrow(new AlreadyExistException("question already exist"));
        ResponseEntity<Object> response=questionController.addQuestion(question);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testGetAllQuestionsSuccess() {
        List<QuestionsDto> questionsDto=new ArrayList<>();
        when(questionService.getQuestions()).thenReturn(questionsDto);
        ResponseEntity<Object> response=questionController.getQuestions();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testGetAllQuestionsError() {
        when(questionService.getQuestions()).thenThrow(new AllNotFoundException("no question is present"));
        ResponseEntity<Object> response=questionController.getQuestions();
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testDeleteSuccess() {
        Questions question=new Questions();
        question.setQuestion("java is?");
        int i=1;
        ResponseEntity<Object> response=questionController.delete(i);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testDeleteError() {
        Questions question=new Questions();
        question.setQuestion("java is?");
        int i=1;
        doThrow(new AllNotFoundException("no question is present")).when(questionService).delete(i);;
        ResponseEntity<Object> response=questionController.delete(i);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testUpdateSuccess() {
        QuestionsUpdateDto q=new QuestionsUpdateDto();
        q.setQuestion("java is");
        q.setQuestion("java is??");
        int i=1;
        when(questionService.updateQue(q, i)).thenReturn(q);
        ResponseEntity<Object> response=questionController.updateQue(q, i);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());  
    }
    @Test
    void testUpdateError() {
        QuestionsUpdateDto q=new QuestionsUpdateDto();
        q.setQuestion("java is");
        q.setQuestion("java is??");
        int i=1;
        when(questionService.updateQue(q, i)).thenThrow(new AllNotFoundException("no question is present"));
        ResponseEntity<Object> response=questionController.updateQue(q, i);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());  
    }
    @Test
    void testGetQuestionByQuizIdSuccess() {
        List<QuestionsDto> q=new ArrayList<>();
        int i=1;
        when(questionService.findQueById(i)).thenReturn(q);
        ResponseEntity<Object> response=questionController.findQueById(i);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());  
    }
    @Test
    void testGetQuestionByQuizIdError() {
        List<QuestionsDto> q=new ArrayList<>();
        int i=1;
        when(questionService.findQueById(i)).thenThrow(new AllNotFoundException("no question is present"));
        ResponseEntity<Object> response=questionController.findQueById(i);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());  
    }
    @Test
    void testFindQuestionByQuestionSuccess() {
        Questions question=new Questions();
        question.setQuestion("java is?");
        QuestionsDto questionsDto=new QuestionsDto();
        questionsDto.setQuestion("java is?");
        when(questionService.findByQuestion("java is?")).thenReturn(Optional.of(questionsDto));
        ResponseEntity<Object> response=questionController.findByQuestion("java is?");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());     
    }
    @Test
    void testFindQuestionByQuestionError() {
        Questions question=new Questions();
        question.setQuestion("java is?");
        QuestionsDto questionsDto=new QuestionsDto();
        questionsDto.setQuestion("java is?");
        when(questionService.findByQuestion("java is?")).thenThrow(new AllNotFoundException("no question is present"));
        ResponseEntity<Object> response=questionController.findByQuestion("java is?");
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());  
        
    }

}
