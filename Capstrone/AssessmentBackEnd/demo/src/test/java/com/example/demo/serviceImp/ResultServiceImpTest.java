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

import com.example.demo.dto.ResultDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.FinalRes;
import com.example.demo.entity.Quiz;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentResult;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.FinalResultRepo;
import com.example.demo.repository.QuizRepo;
import com.example.demo.repository.StudentRepo;
import com.example.demo.repository.StudentResultRepo;

class ResultServiceImpTest {

    @InjectMocks
    private ResultServiceImp resultService;
    @Mock
    private FinalResultRepo finalRepo;
    @Mock
    private StudentResultRepo resultRepo;
    @Mock
    private CategoryRepo categoryRepo;
    @Mock
    private QuizRepo quizRepo;
    @Mock
    private StudentRepo studentRepo;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddResult() {
        ResultDto resultDto = new ResultDto(101,"23-10-23",19, "Madhuri","satya@nucleusteq.com","Array",
                "Java",97,9,10,1,12);
        Student student=new Student();
        student.setEmail("satya@nucleusteq.com");
        when(studentRepo.findByEmail("satya@nucleusteq.com")).thenReturn(Optional.of(student));
        Quiz quiz = new Quiz();
        Category category = new Category();
        FinalRes finalResult=new FinalRes();
        
        Optional<Category> optionalCategory=categoryRepo.findById(resultDto.getCategoryId());
        Optional<Student> optionalStudent=studentRepo.findByEmail(resultDto.getEmail());
    
        finalResult.setCategoryName(resultDto.getCategoryName()); 
        finalResult.setUserId(resultDto.getUserId());
        finalResult.setUserName(resultDto.getUserName());
        finalResult.setQuizTopic(resultDto.getQuizName());
        finalResult.setDateAndTime(resultDto.getDateAndTime());
        finalResult.setMarks(resultDto.getObtainMarks());
        finalResult.setMaxMarks(resultDto.getMaxMarks());
        finalResult.setAttemptedQuestions(resultDto.getAttemptedQuestions());
        finalResult.setCategoryId(resultDto.getCategoryId());
        finalResult.setCategoryName(resultDto.getCategoryName());
        finalResult.setEmail(resultDto.getEmail());
         
        when(quizRepo.findQuizByName("Array")).thenReturn(Optional.of(quiz));
        when(categoryRepo.findByCategoryName("Java")).thenReturn(Optional.of(category));
         ResultDto result= resultService.addResult(resultDto);
        assertEquals("satya@nucleusteq.com",result.getEmail());
    }
    @Test
    void testAddNoEmailFound() {
        ResultDto resultDto = new ResultDto();
        when(studentRepo.findByEmail("satya@nucleusteq.com")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,   ()->{  resultService.addResult(resultDto);
        });
    }
    @Test
    void testAddQuizNotFoundExcetion() {
        ResultDto resultDto = new ResultDto(101,"23-10-23",19, "Madhuri","satya@nucleusteq.com","Array",
                "Java",97,9,10,1,12);
        Student student=new Student();
        student.setEmail("satya@nucleusteq.com");
        when(studentRepo.findByEmail("satya@nucleusteq.com")).thenReturn(Optional.of(student));
        FinalRes finalResult=new FinalRes();
        Optional<Category> optionalCategory=categoryRepo.findById(resultDto.getCategoryId());
        
        finalResult.setCategoryName(resultDto.getCategoryName()); 
        finalResult.setUserId(resultDto.getUserId());
        finalResult.setUserName(resultDto.getUserName());
        finalResult.setQuizTopic(resultDto.getQuizName());
        finalResult.setDateAndTime(resultDto.getDateAndTime());
        finalResult.setMarks(resultDto.getObtainMarks());
        finalResult.setMaxMarks(resultDto.getMaxMarks());
        finalResult.setAttemptedQuestions(resultDto.getAttemptedQuestions());
        finalResult.setCategoryId(resultDto.getCategoryId());
        finalResult.setCategoryName(resultDto.getCategoryName());
        finalResult.setEmail(resultDto.getEmail());;
         
        when(quizRepo.findQuizByName("Array")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,   ()->{ resultService.addResult(resultDto); });
    }
    @Test
    void testAddCategoryNotPresent() {
        ResultDto resultDto = new ResultDto(101,"23-10-23",19, "Madhuri","satya@nucleusteq.com","Array",
                "Java",97,9,10,1,12);
        Student student=new Student();
        student.setEmail("satya@nucleusteq.com");
        when(studentRepo.findByEmail("satya@nucleusteq.com")).thenReturn(Optional.of(student));
        Quiz quiz = new Quiz();
        FinalRes finalResult=new FinalRes();
        Optional<Category> category=categoryRepo.findById(resultDto.getCategoryId());
        
        finalResult.setCategoryName(resultDto.getCategoryName()); 
        finalResult.setUserId(resultDto.getUserId());
        finalResult.setUserName(resultDto.getUserName());
        finalResult.setQuizTopic(resultDto.getQuizName());
        finalResult.setDateAndTime(resultDto.getDateAndTime());
        finalResult.setMarks(resultDto.getObtainMarks());
        finalResult.setMaxMarks(resultDto.getMaxMarks());
        finalResult.setAttemptedQuestions(resultDto.getAttemptedQuestions());
        finalResult.setCategoryId(resultDto.getCategoryId());
        finalResult.setCategoryName(resultDto.getCategoryName());
        finalResult.setEmail(resultDto.getEmail());
         
        when(quizRepo.findQuizByName("Array")).thenReturn(Optional.of(quiz));
        when(categoryRepo.findByCategoryName("Java")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,   ()->{ resultService.addResult(resultDto);    });
    }
   @Test
    void testGetResult() {
        StudentResult studentResult=new StudentResult();
        studentResult.setResultId(1);
        Quiz quiz=new Quiz();
        quiz.setTopicName("variables");
        studentResult.setQuiz(quiz);
        Student student=new Student();
        student.setUserId(18);
        studentResult.setStudentResult(student);
        Category category=new Category();
        category.setCategoryId(13);
        
        when(resultRepo.findAll()).thenReturn(Collections.singletonList(new StudentResult()));
        when(resultRepo.findById(1)).thenReturn(Optional.of(studentResult));
        when(categoryRepo.findById(13)).thenReturn(Optional.of(category));
        studentResult.setCategoryId(13);
        Optional<ResultDto> resultDto=resultService.getResult(studentResult.getResultId());
       assertEquals(resultDto.get().getQuizName(),studentResult.getQuiz().getTopicName());    
    }
    @Test
   void testGetAllResultNoUserFound() {
        when(resultRepo.findAll()).thenReturn(Collections.emptyList());
        assertThrows(AllNotFoundException.class,   ()->{ resultService.getResult(1);    });
   }
   @Test 
   void testGetAllStudentNotFound() {
       when(resultRepo.findAll()).thenReturn(Collections.singletonList(new StudentResult()));
       when(resultRepo.findById(1)).thenReturn(Optional.empty());
       assertThrows(NotFoundException.class,   ()->{ resultService.getResult(1);    });
   }
    @Test
   void testGetAllResult() {
        StudentResult studentResult=new StudentResult();
        studentResult.setResultId(1);
        Quiz quiz=new Quiz();
        quiz.setTopicName("variables");
        studentResult.setQuiz(quiz);
        Student student=new Student();
        student.setUserId(18);
        studentResult.setStudentResult(student);
        Category category=new Category();
        category.setCategoryName("java");
        category.setCategoryId(13);
        studentResult.setCategoryId(13);
        List<StudentResult> studentResultList=new ArrayList<>();
        studentResultList.add(studentResult);
        
        when(resultRepo.findAll()).thenReturn(studentResultList);
       when(resultRepo.findById(1)).thenReturn(Optional.of(studentResult));
        when(categoryRepo.findById(13)).thenReturn(Optional.of(category));
        assertTrue(Optional.of(category).isPresent());
        List<ResultDto> resultDto=resultService.getResults();
        assertEquals(studentResult.getCategoryId(),resultDto.get(0).getCategoryId());       
   }
    @Test
   void testNoStudentResultIsPresent() {
        when(resultRepo.findAll()).thenReturn(Collections.emptyList());
        assertThrows(AllNotFoundException.class,   ()->{ resultService.getResults();    });
   }
}
