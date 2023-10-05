package com.example.demo.serviceImp;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.QuestionsDto;
import com.example.demo.dto.QuestionsUpdateDto;

import com.example.demo.entity.Questions;
import com.example.demo.entity.Quiz;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;

import com.example.demo.repository.QuestionsRepo;
import com.example.demo.repository.QuizRepo;

class QuestionServiceImpTest {
    @InjectMocks
    private QuestionServiceImp questionsService;
    @Mock
    private QuestionsRepo questionsRepo;
    @Mock
    private QuizRepo quizRepo;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testAddQuestion() {
        QuestionsDto q=new QuestionsDto("java is","oops","popl","none","both","oops",7,9);
       Quiz quiz=new Quiz(9,"variables","java variables",60);
      when(quizRepo.findById(q.getQuizId())).thenReturn(Optional.of(quiz));
        when(questionsRepo.findByQuestion(q.getQuestion())).thenReturn(Optional.empty());
        assertEquals(q.getCorrectOption(),q.getOption1());
       Questions question=new Questions();
       
       question.setQuiz(quiz);
       question.setQuestion(q.getQuestion());
       question.setOption1(q.getOption1());
       question.setOption2(q.getOption2());
       question.setOption3(q.getOption3());
       question.setOption4(q.getOption4());
       question.setCorrectOption(q.getCorrectOption());
       questionsService.addQuestion(q);
    }
    
    @Test
    void testQuestionAlreadyExistException() {
        QuestionsDto q=new QuestionsDto("java is","oops","popl","none","both","oopl",1,3);
        Quiz quiz=new Quiz(1,"variables","java variables",60);
        Questions question=new Questions("java is","oops","popl","none","both","oopl");
        
        when(quizRepo.findById(q.getQuizId())).thenReturn(Optional.of(quiz));
        when(questionsRepo.findByQuestion(q.getQuestion())).thenReturn(Optional.of(question));
        assertThrows(AlreadyExistException.class,()->{
            questionsService.addQuestion(q);
        });
   }
   @Test
    void testQuizTopicNotPresent() {
        QuestionsDto q=new QuestionsDto("java is","oops","popl","none","both","oopl",7,9);
        Quiz quiz=new Quiz(1,"variables","java variables",60);
        
        when(quizRepo.findById(q.getQuizId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,()->{
            questionsService.addQuestion(q);
       });
    }
    @Test
   void testGetAll() {
        Questions q=new Questions("java is","oops","popl","none","both","oopl");
        Quiz quiz=new Quiz(1,"variables","java variables",60);
         q.setQuiz(quiz);
         
         List<Questions> q1=new ArrayList<>();
         q1.add(q);
       when(questionsRepo.findAll()).thenReturn(q1);
       List<QuestionsDto> questionsDto=questionsService.getQuestions();
       assertEquals(q.getQuestion(),questionsDto.get(0).getQuestion());
       assertEquals(q.getOption1(),questionsDto.get(0).getOption1());
       assertEquals(q.getOption2(),questionsDto.get(0).getOption2());
       assertEquals(q.getOption3(),questionsDto.get(0).getOption3());
       assertEquals(q.getOption4(),questionsDto.get(0).getOption4());
       assertEquals(q.getCorrectOption(),questionsDto.get(0).getCorrectOption());
       assertEquals(q.getQuiz().getQuizId(),questionsDto.get(0).getQuizId());      
    }
    @Test
    void testNoQuestionIsPresent() {
        when(questionsRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, ()->{
            questionsService.getQuestions();
        });
    }
    @Test
    void testDeleteById() {
        Questions q=new Questions("java is","oops","popl","none","both","oopl");
        q.setQid(10);
        Quiz quiz=new Quiz(1,"variables","java variables",60);
        q.setQuiz(quiz);
        
        when(questionsRepo.findAll()).thenReturn(Collections.singletonList(new Questions()));
        when(questionsRepo.findById(q.getQid())).thenReturn(Optional.of(q));
        questionsService.delete(10);
    }
    
    @Test
    public void testDeleteIdNotFound() {
        int questionsId=1;
        when(questionsRepo.findAll()).thenReturn(Collections.singletonList(new Questions()));
        when(quizRepo.findById(questionsId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            questionsService.delete(questionsId);
        });  
    }
    
    @Test
    public void testDeleteNoQuizNotFind() {
        int questionsId=1;
        when(questionsRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class , () ->{ questionsService.delete(questionsId);
        });
    }
    @Test
    public void testUpdateQuestion() {
        Questions q=new Questions("java is","oops","popl","none","both","oopl");
        q.setQid(1);
        Quiz quiz=new Quiz(1,"variables","java variables",60);
         q.setQuiz(quiz);
        questionsRepo.save(q);
        QuestionsDto questionsDto=new QuestionsDto();
        questionsDto.setQuizId(quiz.getQuizId());
        questionsDto.setQuestion(q.getQuestion());
        questionsDto.setOption1(q.getOption1());
        questionsDto.setOption2(q.getOption2());
        questionsDto.setOption3(q.getOption3());
        questionsDto.setOption4(q.getOption4());
        questionsDto.setCorrectOption(q.getCorrectOption());
        
        when(questionsRepo.findById(q.getQid())).thenReturn(Optional.of(q));
        assertTrue(Optional.of(q).isPresent());
        QuestionsUpdateDto question=new QuestionsUpdateDto("java is?","oops","popl","none","both","oops");
        List<Questions> questionList=new ArrayList<>();
        questionList.add(q);
        when(questionsRepo.findAll()).thenReturn(questionList);
        QuestionsUpdateDto qd=questionsService.updateQuestion(question, 1);
        assertEquals(q.getQuestion(),qd.getQuestion());
        assertEquals(q.getOption1(),qd.getOption1());
        assertEquals(q.getOption2(),qd.getOption2());
        assertEquals(q.getOption3(),qd.getOption3());
        assertEquals(q.getOption4(),qd.getOption4());
        assertEquals(q.getCorrectOption(),qd.getCorrectOption());
    }
    @Test
    public void testNoQuizisPresent() {
        QuestionsUpdateDto question=new QuestionsUpdateDto("java is?","oops","popl","none","both","oopl");
        when(questionsRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class , () ->{questionsService.updateQuestion(question, 10);
        });
    }
    @Test
    public void testUpdateQuizNotPresent() {
        QuestionsUpdateDto question=new QuestionsUpdateDto("java is?","oops","popl","none","both","oopl");
        when(questionsRepo.findAll()).thenReturn(Collections.singletonList(new Questions()));
        when(questionsRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{questionsService.updateQuestion(question,1);
    });
    }
    @Test
    public void testGetQuestionByQuizId() {
        Questions q1=new Questions("java is","oops","popl","none","both","oopl");
        q1.setQid(1);
        Quiz quiz=new Quiz(1,"variables","java variables",60);
         q1.setQuiz(quiz);
         Questions q2=new Questions("variable is","oops","popl","none","both","oopl");
         q2.setQuiz(quiz);
        List<Questions> questions=new ArrayList<>();
       questions.add(q1);
       questions.add(q2);
       
        when(questionsRepo.findQueById(1)).thenReturn(questions);
        List<QuestionsDto> questionsDto=questionsService.findQuestionById(1);
      assertEquals("java is",questionsDto.get(0).getQuestion()); 
      assertEquals("variable is",questionsDto.get(1).getQuestion()); 
      assertEquals("oops",questionsDto.get(1).getOption1());
      assertEquals("oops",questionsDto.get(0).getOption1());
      assertEquals("popl",questionsDto.get(0).getOption2());
      assertEquals("popl",questionsDto.get(1).getOption2());
      assertEquals("none",questionsDto.get(0).getOption3());
      assertEquals("none",questionsDto.get(1).getOption3());
      assertEquals("both",questionsDto.get(0).getOption4());
      assertEquals("both",questionsDto.get(1).getOption4());
      assertEquals("oopl",questionsDto.get(0).getCorrectOption());
      assertEquals("oopl",questionsDto.get(1).getCorrectOption());
      
    }

     @Test
     void testCategoryIdNotPresent() {
         int quizId=10;
         when(questionsRepo.findQueById(quizId)).thenReturn(new ArrayList<>());
         assertThrows(AllNotFoundException.class, () ->{questionsService.findQuestionById(quizId);
         }); 
     }
     
     @Test   
     void testFindQuestionByQuestionName() {
         Questions q1=new Questions("java is","oops","popl","none","both","oopl");
         q1.setQid(1);
         Quiz quiz=new Quiz(1,"variables","java variables",60);
         q1.setQuiz(quiz);
         List<Questions> questionList=new ArrayList<>();
         questionList.add(q1);
         when(questionsRepo.findAll()).thenReturn(questionList);
        
         Optional<QuestionsDto> questionsDto=questionsService.findByQuestion(q1.getQuestion());
         assertEquals(q1.getQuestion(),questionsDto.get().getQuestion());
         assertEquals(q1.getOption1(),questionsDto.get().getOption1());
         assertEquals(q1.getOption2(),questionsDto.get().getOption2());
         assertEquals(q1.getOption3(),questionsDto.get().getOption3());
         assertEquals(q1.getOption4(),questionsDto.get().getOption4());
         assertEquals(q1.getCorrectOption(),questionsDto.get().getCorrectOption());
         assertEquals(q1.getQuiz().getQuizId(),questionsDto.get().getQuizId());      
    }

       @Test
       void testFindQuizNoQuizIsPresent() {
           String questionsName="arrays is?";
           when(questionsRepo.findAll()).thenReturn(new ArrayList<>());
           assertThrows(NotFoundException.class , () ->{ questionsService.findByQuestion(questionsName);
           });
       }
  
}
