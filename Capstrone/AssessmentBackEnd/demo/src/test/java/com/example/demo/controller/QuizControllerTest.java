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
import com.example.demo.response.Response;
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
        QuizDto quiz=new QuizDto();
        quiz.setTopicName("arrays");
        QuizDto quizDto=new QuizDto();
        quizDto.setTopicName("arrays");
        when(quizService.addQuiz(quiz)).thenReturn(quizDto);
        Response errorResponse = new Response(HttpStatus.CREATED.value(), "quiz added successfully");
        ResponseEntity<Response> response=quizController.saveQuiz(quiz);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals("quiz added successfully",errorResponse.getMessage());
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
        ResponseEntity<Optional<QuizDto>> response=quizController.getQuiz(1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testFindAllQuizSuccess() {
        List<QuizDto> quizDto=new ArrayList<>();
        when(quizService.findAll()).thenReturn(quizDto);
        ResponseEntity<List<QuizDto>> response=quizController.findAll();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testDeleteQuizSuccess() {
        Quiz quiz=new Quiz();
        quiz.setQuizId(1);
        Response errorResponse = new Response(HttpStatus.CREATED.value(), "quiz deleted successfully");
        ResponseEntity<Response> response=quizController.deleteQuiz(1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("quiz deleted successfully",errorResponse.getMessage());
    }
    @Test
    void testUpdateQuizSuccess() {
        QuizUpdateDto quizDto=new QuizUpdateDto();
        quizDto.setTopicName("arrays");
        quizDto.setTopicName("Arrays");
        int i=1;
        when(quizService.updateQuiz(quizDto, i)).thenReturn(quizDto);
        Response errorResponse = new Response(HttpStatus.CREATED.value(), "quiz updated successfully");
        ResponseEntity<Response> response=quizController.updateQuiz(quizDto, i);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("quiz updated successfully",errorResponse.getMessage());
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
        ResponseEntity<List<QuizDto>> response=quizController.findQuizById(2);
        assertEquals(HttpStatus.OK,response.getStatusCode());
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
        ResponseEntity<Optional<QuizDto>> response=quizController.findQuizByName("arrays");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());   
    }
}
