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
import com.example.demo.dto.QuestionsDto;
import com.example.demo.dto.QuestionsUpdateDto;
import com.example.demo.entity.Questions;
import com.example.demo.response.Response;
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
        QuestionsDto question=new QuestionsDto();
        question.setQuestion("java is?");
        QuestionsDto questionsDto=new QuestionsDto();
        questionsDto.setQuestion("java is?");
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("question added successfully");
        
        when(questionService.addQuestion(question)).thenReturn(questionsDto);
        Response response=questionController.addQuestion(question);
        assertEquals(response,expectedResponse);
    }
    @Test
    void testGetAllQuestionsSuccess() {
        List<QuestionsDto> questionsDto=new ArrayList<>();
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("successfully retrieve all questions");
        expectedResponse.setData(questionsDto);
        
        when(questionService.getQuestions()).thenReturn(questionsDto);
        Response response=questionController.getQuestions();
        assertEquals(response,expectedResponse);
    }
    @Test
    void testDeleteSuccess() {
        Questions question=new Questions();
        question.setQuestion("java is?");
        int i=1;
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("question deleted successfully");
        Response response=questionController.delete(i);
        assertEquals(response,expectedResponse);
    }
    @Test
    void testUpdateSuccess() {
        QuestionsUpdateDto q=new QuestionsUpdateDto();
        q.setQuestion("java is");
        q.setQuestion("java is??");
        int i=1;
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("question updated successfully");
        when(questionService.updateQuestion(q, i)).thenReturn(q);
        Response response=questionController.updateQue(q, i);
        assertEquals(response,expectedResponse);
    }
    @Test
    void testGetQuestionByQuizIdSuccess() {
        List<QuestionsDto> q=new ArrayList<>();
        int i=1;
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("successfully retrieve question by quiz id");
        expectedResponse.setData(q);
        when(questionService.findQuestionById(i)).thenReturn(q);
        Response response=questionController.findQueById(i);
        assertEquals(response,expectedResponse);
    }
    @Test
    void testFindQuestionByQuestionSuccess() {
        Questions question=new Questions();
        question.setQuestion("java is?");
        QuestionsDto questionsDto=new QuestionsDto();
        questionsDto.setQuestion("java is?");
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("successfully retrieve the data");
        expectedResponse.setData(questionsDto);
        
        when(questionService.findByQuestion("java is?")).thenReturn(questionsDto);
        Response response=questionController.findByQuestion("java is?");
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals(response,expectedResponse);
    }
}
