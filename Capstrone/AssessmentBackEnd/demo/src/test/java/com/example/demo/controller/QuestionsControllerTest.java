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
        
        when(questionService.addQuestion(question)).thenReturn(questionsDto);
        Response errorResponse = new Response(HttpStatus.OK.value(), "question added successfully");
        Response response=questionController.addQuestion(question);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("question added successfully",errorResponse.getMessage());
    }
    @Test
    void testGetAllQuestionsSuccess() {
        List<QuestionsDto> questionsDto=new ArrayList<>();
        when(questionService.getQuestions()).thenReturn(questionsDto);
        Response response=questionController.getQuestions();
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals(questionsDto,response.getData());
        assertEquals("successfully retrieve all questions",response.getMessage());
    }
    @Test
    void testDeleteSuccess() {
        Questions question=new Questions();
        question.setQuestion("java is?");
        int i=1;
        
        Response response=questionController.delete(i);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("question deleted successfully",response.getMessage());
      

    }
    @Test
    void testUpdateSuccess() {
        QuestionsUpdateDto q=new QuestionsUpdateDto();
        q.setQuestion("java is");
        q.setQuestion("java is??");
        int i=1;
        
        when(questionService.updateQuestion(q, i)).thenReturn(q);
        Response response=questionController.updateQue(q, i);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("question updated successfully",response.getMessage()); 
    }
    @Test
    void testGetQuestionByQuizIdSuccess() {
        List<QuestionsDto> q=new ArrayList<>();
        int i=1;
        when(questionService.findQuestionById(i)).thenReturn(q);
        Response response=questionController.findQueById(i);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("successfully retrieve question by quiz id",response.getMessage());  
        
    }
    @Test
    void testFindQuestionByQuestionSuccess() {
        Questions question=new Questions();
        question.setQuestion("java is?");
        QuestionsDto questionsDto=new QuestionsDto();
        questionsDto.setQuestion("java is?");
        
        when(questionService.findByQuestion("java is?")).thenReturn(Optional.of(questionsDto));
        Response response=questionController.findByQuestion("java is?");
        assertEquals(HttpStatus.OK.value(),response.getCode());
           
    }
}
