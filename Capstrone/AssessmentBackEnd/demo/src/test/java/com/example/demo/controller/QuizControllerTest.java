package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
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
import com.example.demo.dto.QuizDto;
import com.example.demo.dto.QuizUpdateDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Quiz;
import com.example.demo.response.Response;
import com.example.demo.serviceImp.QuizServiceImp;

class QuizControllerTest {
    @Mock
    private QuizServiceImp quizService;
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
        Response response=quizController.saveQuiz(quiz);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("succcessfully add the data",response.getMessage());
        
    }
    @Test
    void testGetQuizByIdSuccess() {
        Quiz quiz=new Quiz();
        quiz.setTopicName("arrays");
        quiz.setQuizId(1);
        QuizDto quizDto=new QuizDto();
        quizDto.setTopicName("arrays");
        
        when(quizService.getQuiz(1)).thenReturn(Optional.of(quizDto));
        Response response=quizController.getQuiz(1);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("successfully retrieve the quiz detaills",response.getMessage());
        assertEquals(Optional.of(quizDto),response.getData());
        
    }
    @Test
    void testFindAllQuizSuccess() {
        List<QuizDto> quizDto=new ArrayList<>();
        when(quizService.findAll()).thenReturn(quizDto);
        Response response=quizController.findAll();
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals(quizDto,response.getData());
        assertEquals("successfully find all quizes",response.getMessage());
    }
    @Test
    void testDeleteQuizSuccess() {
        Quiz quiz=new Quiz();
        quiz.setQuizId(1);
        
        Response response=quizController.deleteQuiz(1);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("quiz deleted successfully",response.getMessage());
    }
    @Test
    void testUpdateQuizSuccess() {
        QuizUpdateDto quizDto=new QuizUpdateDto();
        quizDto.setTopicName("arrays");
        quizDto.setTopicName("Arrays");
        int i=1;
        
        when(quizService.updateQuiz(quizDto, i)).thenReturn(quizDto);
        Response errorResponse = new Response(HttpStatus.CREATED.value(), "quiz updated successfully");
        Response response=quizController.updateQuiz(quizDto, i);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("quiz updated successfully",response.getMessage());
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
        Response response=quizController.findQuizById(2);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals(q,response.getData());
        assertEquals("successfully retrieve the quiz by category id",response.getMessage());
    }
    @Test
    void testQuizByNameSuccess() {
        Quiz quiz=new Quiz();
        quiz.setTopicName("arrays");
        quiz.setQuizId(1);
        QuizDto quizDto=new QuizDto();
        quizDto.setTopicName("arrays");
        
        when(quizService.findQuizByName("arrays")).thenReturn(Optional.of(quizDto));
        Response response=quizController.findQuizByName("arrays");
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals(Optional.of(quizDto),response.getData());
        assertEquals("successfully retrieve the quiz detaills",response.getMessage());
    }
}
