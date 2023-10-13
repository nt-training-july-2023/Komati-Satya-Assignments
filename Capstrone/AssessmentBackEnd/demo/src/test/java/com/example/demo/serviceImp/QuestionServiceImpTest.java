package com.example.demo.serviceImp;


import static org.junit.jupiter.api.Assertions.*;
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
       QuestionsDto questionDto2=questionsService.addQuestion(questionDto);
      assertEquals(questionDto2,questionDto);
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
        
        when(quizRepo.findById(questionDto.getQuizId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,()->{  questionsService.addQuestion(questionDto);
       });
    }
    @Test
   void testGetAll() {
        QuestionsDto questionDto=new QuestionsDto("java is","oops","popl","none","both","oopl",1,9);
        Quiz quiz=new Quiz(1,"variables","java variables",60);
        Questions questions=new Questions();
        questions.setCorrectOption(questionDto.getCorrectOption());
        questions.setOption1(questionDto.getOption1());
        questions.setOption2(questionDto.getOption2());
        questions.setOption3(questionDto.getOption3());
        questions.setOption4(questionDto.getOption4());
        questions.setQuestion(questionDto.getQuestion());
        questions.setQid(questionDto.getQuestionId());
        questions.setQuiz(quiz);
         
        List<Questions> question=new ArrayList<>();
        question.add(questions);
       when(questionsRepo.findAll()).thenReturn(question);
       List<QuestionsDto> questionsDto=questionsService.getQuestions();
       assertEquals(questionsDto,Collections.singletonList(questionDto));
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
        assertFalse(questionsRepo.existsById(10));
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
        assertEquals(qd,question);
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
        QuestionsDto questionDto=new QuestionsDto("java is","oops","popl","none","both","oopl",1,9);
        Quiz quiz=new Quiz(1,"variables","java variables",60);
        Questions questions=new Questions();
        questions.setCorrectOption(questionDto.getCorrectOption());
        questions.setOption1(questionDto.getOption1());
        questions.setOption2(questionDto.getOption2());
        questions.setOption3(questionDto.getOption3());
        questions.setOption4(questionDto.getOption4());
        questions.setQuestion(questionDto.getQuestion());
        questions.setQid(questionDto.getQuestionId());
        questions.setQuiz(quiz);
        List<Questions> questionsList = new ArrayList<>();
        questionsList.add(questions);
        when(quizRepo.findById(1)).thenReturn(Optional.of(quiz));
        when(questionsRepo.findQueById(1)).thenReturn(questionsList);
        List<QuestionsDto> questionsDto=questionsService.findQuestionById(1);
        assertEquals(questionsDto,Collections.singletonList(questionDto));
      
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
         QuestionsDto questionDto=new QuestionsDto("java is","oops","popl","none","both","oopl",1,9);
         Quiz quiz=new Quiz(1,"variables","java variables",60);
         Questions questions=new Questions();
         questions.setCorrectOption(questionDto.getCorrectOption());
         questions.setOption1(questionDto.getOption1());
         questions.setOption2(questionDto.getOption2());
         questions.setOption3(questionDto.getOption3());
         questions.setOption4(questionDto.getOption4());
         questions.setQuestion(questionDto.getQuestion());
         questions.setQid(questionDto.getQuestionId());
         questions.setQuiz(quiz);
         List<Questions> questionsList = new ArrayList<>();
         questionsList.add(questions);
         
         when(questionsRepo.findAll()).thenReturn(questionsList);
         QuestionsDto questionsDto=questionsService.findByQuestion(questions.getQuestion());
         assertEquals(questionsDto,questionsDto);   
    }

       @Test
       void testFindQuizNoQuizIsPresent() {
           String questionsName="arrays is?";
           when(questionsRepo.findAll()).thenReturn(new ArrayList<>());
           assertThrows(NotFoundException.class , () ->{ questionsService.findByQuestion(questionsName);
           });
       }
  
}
