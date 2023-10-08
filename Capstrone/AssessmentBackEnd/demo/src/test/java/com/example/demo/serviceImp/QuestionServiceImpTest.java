package com.example.demo.serviceImp;


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
        QuestionsDto questionDto=new QuestionsDto("java is","oops","popl","none","both","oops",7,9);
       Quiz quiz=new Quiz(9,"variables","java variables",60);
      when(quizRepo.findById(questionDto.getQuizId())).thenReturn(Optional.of(quiz));
        when(questionsRepo.findByQuestion(questionDto.getQuestion())).thenReturn(Optional.empty());
        assertEquals(questionDto.getCorrectOption(),questionDto.getOption1());
       Questions question=new Questions();
       
       question.setQuiz(quiz);
       question.setQuestion(questionDto.getQuestion());
       question.setOption1(questionDto.getOption1());
       question.setOption2(questionDto.getOption2());
       question.setOption3(questionDto.getOption3());
       question.setOption4(questionDto.getOption4());
       question.setCorrectOption(questionDto.getCorrectOption());
       questionsService.addQuestion(questionDto);
    }
    
    @Test
    void testQuestionAlreadyExistException() {
        QuestionsDto questionDto=new QuestionsDto("java is","oops","popl","none","both","oopl",1,3);
        Quiz quiz=new Quiz(1,"variables","java variables",60);
        Questions question=new Questions("java is","oops","popl","none","both","oopl");
        
        when(quizRepo.findById(questionDto.getQuizId())).thenReturn(Optional.of(quiz));
        when(questionsRepo.findByQuestion(questionDto.getQuestion())).thenReturn(Optional.of(question));
        assertThrows(AlreadyExistException.class,()->{  questionsService.addQuestion(questionDto);
        });
   }
   @Test
    void testQuizTopicNotPresent() {
        QuestionsDto questionDto=new QuestionsDto("java is","oops","popl","none","both","oopl",7,9);
        Quiz quiz=new Quiz(1,"variables","java variables",60);
        
        when(quizRepo.findById(questionDto.getQuizId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,()->{  questionsService.addQuestion(questionDto);
       });
    }
    @Test
   void testGetAll() {
        Questions questions=new Questions("java is","oops","popl","none","both","oopl");
        Quiz quiz=new Quiz(1,"variables","java variables",60);
         questions.setQuiz(quiz);
         
         List<Questions> q1=new ArrayList<>();
         q1.add(questions);
       when(questionsRepo.findAll()).thenReturn(q1);
       List<QuestionsDto> questionsDto=questionsService.getQuestions();
       assertEquals(questions.getQuestion(),questionsDto.get(0).getQuestion());
       assertEquals(questions.getOption1(),questionsDto.get(0).getOption1());
       assertEquals(questions.getOption2(),questionsDto.get(0).getOption2());
       assertEquals(questions.getOption3(),questionsDto.get(0).getOption3());
       assertEquals(questions.getOption4(),questionsDto.get(0).getOption4());
       assertEquals(questions.getCorrectOption(),questionsDto.get(0).getCorrectOption());
       assertEquals(questions.getQuiz().getQuizId(),questionsDto.get(0).getQuizId());      
    }
    @Test
    void testNoQuestionIsPresent() {
        when(questionsRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, ()->{  questionsService.getQuestions();
        });
    }
    @Test
    void testDeleteById() {
        Questions questions=new Questions("java is","oops","popl","none","both","oopl");
        questions.setQid(10);
        Quiz quiz=new Quiz(1,"variables","java variables",60);
        questions.setQuiz(quiz);
        
        when(questionsRepo.findAll()).thenReturn(Collections.singletonList(new Questions()));
        when(questionsRepo.findById(questions.getQid())).thenReturn(Optional.of(questions));
        questionsService.delete(10);
    }
    
    @Test
    public void testDeleteIdNotFound() {
        int questionsId=1;
        when(questionsRepo.findAll()).thenReturn(Collections.singletonList(new Questions()));
        when(quizRepo.findById(questionsId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{ questionsService.delete(questionsId);
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
        Questions questions=new Questions("java is","oops","popl","none","both","oopl");
        questions.setQid(1);
        Quiz quiz=new Quiz(1,"variables","java variables",60);
         questions.setQuiz(quiz);
        questionsRepo.save(questions);
        QuestionsDto questionsDto=new QuestionsDto();
        questionsDto.setQuizId(quiz.getQuizId());
        questionsDto.setQuestion(questions.getQuestion());
        questionsDto.setOption1(questions.getOption1());
        questionsDto.setOption2(questions.getOption2());
        questionsDto.setOption3(questions.getOption3());
        questionsDto.setOption4(questions.getOption4());
        questionsDto.setCorrectOption(questions.getCorrectOption());
        
        when(questionsRepo.findById(questions.getQid())).thenReturn(Optional.of(questions));
        assertTrue(Optional.of(questions).isPresent());
        QuestionsUpdateDto question=new QuestionsUpdateDto("java is?","oops","popl","none","both","oops");
        List<Questions> questionList=new ArrayList<>();
        questionList.add(questions);
        when(questionsRepo.findAll()).thenReturn(questionList);
        QuestionsUpdateDto qd=questionsService.updateQuestion(question, 1);
        assertEquals(questions.getQuestion(),qd.getQuestion());
        assertEquals(questions.getOption1(),qd.getOption1());
        assertEquals(questions.getOption2(),qd.getOption2());
        assertEquals(questions.getOption3(),qd.getOption3());
        assertEquals(questions.getOption4(),qd.getOption4());
        assertEquals(questions.getCorrectOption(),qd.getCorrectOption());
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
        Questions questions1=new Questions("java is","oops","popl","none","both","oopl");
        questions1.setQid(1);
        Quiz quiz=new Quiz(1,"variables","java variables",60);
         questions1.setQuiz(quiz);
         Questions questions2=new Questions("variable is","oops","popl","none","both","oopl");
         questions2.setQuiz(quiz);
        List<Questions> questions=new ArrayList<>();
       questions.add(questions1);
       questions.add(questions2);
       
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
         Questions questions=new Questions("java is","oops","popl","none","both","oopl");
         questions.setQid(1);
         Quiz quiz=new Quiz(1,"variables","java variables",60);
         questions.setQuiz(quiz);
         List<Questions> questionList=new ArrayList<>();
         questionList.add(questions);
         when(questionsRepo.findAll()).thenReturn(questionList);
        
         Optional<QuestionsDto> questionsDto=questionsService.findByQuestion(questions.getQuestion());
         assertEquals(questions.getQuestion(),questionsDto.get().getQuestion());
         assertEquals(questions.getOption1(),questionsDto.get().getOption1());
         assertEquals(questions.getOption2(),questionsDto.get().getOption2());
         assertEquals(questions.getOption3(),questionsDto.get().getOption3());
         assertEquals(questions.getOption4(),questionsDto.get().getOption4());
         assertEquals(questions.getCorrectOption(),questionsDto.get().getCorrectOption());
         assertEquals(questions.getQuiz().getQuizId(),questionsDto.get().getQuizId());      
    }

       @Test
       void testFindQuizNoQuizIsPresent() {
           String questionsName="arrays is?";
           when(questionsRepo.findAll()).thenReturn(new ArrayList<>());
           assertThrows(NotFoundException.class , () ->{ questionsService.findByQuestion(questionsName);
           });
       }
  
}
