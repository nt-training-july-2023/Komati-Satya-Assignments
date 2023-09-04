package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
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
import com.example.demo.dto.QuizDto;
import com.example.demo.dto.QuizUpdateDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Quiz;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.serviceImp.QuizSirviceImp;

class QuizControllerTest {
    @Mock
    private QuizSirviceImp quizService;
    @InjectMocks
    private QuizController quizController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddQuizSuccess() {
        Quiz quiz=new Quiz();
        quiz.setTopicName("arrays");
        QuizDto quizDto=new QuizDto();
        quizDto.setTopicName("arrays");
        when(quizService.addQuiz(quiz)).thenReturn(quizDto);
        ResponseEntity<Object> response=quizController.saveQuiz(quiz);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testAddQuizError() {
        Quiz quiz=new Quiz();
        quiz.setTopicName("arrays");
        QuizDto quizDto=new QuizDto();
        quizDto.setTopicName("arrays");
        when(quizService.addQuiz(quiz)).thenThrow(new NotFoundException("Category is not present"));
        ResponseEntity<Object> response=quizController.saveQuiz(quiz);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testGetQuizByIdSuccess() {
        Quiz quiz=new Quiz();
        quiz.setTopicName("arrays");
        quiz.setQuizId(1);
        QuizDto quizDto=new QuizDto();
        quizDto.setTopicName("arrays");
        when(quizService.getQuiz(1)).thenReturn(Optional.of(quizDto));
        ResponseEntity<Object> response=quizController.getQuiz(1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testFetQuizByIdError() {
        Quiz quiz=new Quiz();
        quiz.setTopicName("arrays");
        quiz.setQuizId(1);
        QuizDto quizDto=new QuizDto();
        quizDto.setTopicName("arrays");
        when(quizService.getQuiz(1)).thenThrow(new NotFoundException("wrong quiz id"));
        ResponseEntity<Object> response=quizController.getQuiz(1);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testFindAllQuizSuccess() {
        List<QuizDto> quizDto=new ArrayList<>();
        when(quizService.findAll()).thenReturn(quizDto);
        ResponseEntity<Object> response=quizController.findAll();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testFindAllQuizError() {
        when(quizService.findAll()).thenThrow(new AllNotFoundException("no quiz is present"));
        ResponseEntity<Object> response=quizController.findAll();
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testDeleteQuizSuccess() {
        Quiz quiz=new Quiz();
        quiz.setQuizId(1);
        ResponseEntity<Object> response=quizController.deleteQuiz(1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testDeleteQuizError() {
        Quiz quiz=new Quiz();
        quiz.setQuizId(1);
        doThrow(new AllNotFoundException("no quiz is present")).when(quizService).deleteQuiz(1);
        ResponseEntity<Object> response=quizController.deleteQuiz(1);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testUpdateQuizSuccess() {
        QuizUpdateDto quizDto=new QuizUpdateDto();
        quizDto.setTopicName("arrays");
        quizDto.setTopicName("Arrays");
        int i=1;
        when(quizService.updateQuiz(quizDto, i)).thenReturn(quizDto);
        ResponseEntity<Object> response=quizController.updateQuiz(quizDto, i);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testUpdateQuizError() {
        QuizUpdateDto quizDto=new QuizUpdateDto();
        quizDto.setTopicName("arrays");
        quizDto.setTopicName("Arrays");
        int i=1;
        when(quizService.updateQuiz(quizDto, i)).thenThrow(new AllNotFoundException("no quiz is present"));
        ResponseEntity<Object> response=quizController.updateQuiz(quizDto, i);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testQuizFindById() {
        Quiz quiz=new Quiz();
        quiz.setTopicName("arrays");
        quiz.setQuizId(1);
        Category c=new Category();
        c.setCategoryId(2);
        QuizDto quizDto=new QuizDto();
        quizDto.setTopicName("arrays");
        List<QuizDto> q=new ArrayList<>();
        when(quizService.findQuizById(2)).thenReturn(q);
        ResponseEntity<Object> response=quizController.findQuizById(2);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testQuizFindByIdError() {
        when(quizService.findQuizById(2)).thenThrow(new NotFoundException("wrong category id"));
        ResponseEntity<Object> response=quizController.findQuizById(2);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testQuizByNameSuccess() {
        Quiz quiz=new Quiz();
        quiz.setTopicName("arrays");
        quiz.setQuizId(1);
        QuizDto quizDto=new QuizDto();
        quizDto.setTopicName("arrays");
        when(quizService.findQuizByName("arrays")).thenReturn(Optional.of(quizDto));
        ResponseEntity<Object> response=quizController.findQuizByName("arrays");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());   
    }
    @Test
    void testQuizByNameError() {
        Quiz quiz=new Quiz();
        quiz.setTopicName("arrays");
        quiz.setQuizId(1);
        QuizDto quizDto=new QuizDto();
        quizDto.setTopicName("arrays");
        when(quizService.findQuizByName("arrays")).thenThrow(new NotFoundException("topic is not found"));
        ResponseEntity<Object> response=quizController.findQuizByName("arrays");
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());   
    }


}
