package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
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
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("succcessfully add the data");
        
        when(quizService.addQuiz(quiz)).thenReturn(quizDto);
        Response response=quizController.saveQuiz(quiz);
        assertEquals(response,expectedResponse);
    }
    @Test
    void testGetQuizByIdSuccess() {
        Quiz quiz=new Quiz();
        quiz.setTopicName("arrays");
        quiz.setQuizId(1);
        QuizDto quizDto=new QuizDto();
        quizDto.setTopicName("arrays");
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("successfully retrieve the quiz detaills");
        expectedResponse.setData(quizDto);
        
        when(quizService.getQuiz(1)).thenReturn(quizDto);
        Response response=quizController.getQuiz(1);
        assertEquals(response,expectedResponse);
    }
    @Test
    void testFindAllQuizSuccess() {
        List<QuizDto> quizDto=new ArrayList<>();
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("successfully find all quizes");
        expectedResponse.setData(quizDto);
        
        when(quizService.findAll()).thenReturn(quizDto);
        Response response=quizController.findAll();
        assertEquals(response,expectedResponse);
    }
    @Test
    void testDeleteQuizSuccess() {
        Quiz quiz=new Quiz();
        quiz.setQuizId(1);
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("quiz deleted successfully");
        Response response=quizController.deleteQuiz(1);
        assertEquals(response,expectedResponse);
    }
    @Test
    void testUpdateQuizSuccess() {
        QuizUpdateDto quizDto=new QuizUpdateDto();
        quizDto.setTopicName("arrays");
        quizDto.setTopicName("Arrays");
        int i=1;
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("quiz updated successfully");
        
        when(quizService.updateQuiz(quizDto, i)).thenReturn(quizDto);
        Response response=quizController.updateQuiz(quizDto, i);
        assertEquals(response,expectedResponse);
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
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("successfully retrieve the quiz by category id");
        expectedResponse.setData(q);
        
        when(quizService.findQuizById(2)).thenReturn(q);
        Response response=quizController.findQuizById(2);
        assertEquals(response,expectedResponse);
    }
    @Test
    void testQuizByNameSuccess() {
        Quiz quiz=new Quiz();
        quiz.setTopicName("arrays");
        quiz.setQuizId(1);
        QuizDto quizDto=new QuizDto();
        quizDto.setTopicName("arrays");
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("successfully retrieve the quiz detaills");
        expectedResponse.setData(quizDto);
        
        when(quizService.findQuizByName("arrays")).thenReturn(quizDto);
        Response response=quizController.findQuizByName("arrays");
        assertEquals(response,expectedResponse);
    }
}
