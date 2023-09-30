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
        ResultDto sr = new ResultDto(101,"23-10-23","pass",19, "Madhuri","satya@nucleusteq.com","Array",
                "Java",97,9,10,1,12);
        Student student=new Student();
        student.setEmail("satya@nucleusteq.com");
        when(studentRepo.findByEmail("satya@nucleusteq.com")).thenReturn(Optional.of(student));
        Quiz quiz = new Quiz();
        Category category = new Category();
        FinalRes fr=new FinalRes();
        
        Optional<Category> c=categoryRepo.findById(sr.getCategoryId());
        Optional<Student> ss=studentRepo.findByEmail(sr.getEmail());
        System.out.println(c);
         fr.setCategoryName(sr.getCategoryName()); 
          fr.setUserId(sr.getUserId());
         fr.setUserName(sr.getUserName());
         fr.setQuizTopic(sr.getQuizName());
         fr.setDateAndTime(sr.getDateAndTime());
         fr.setMarks(sr.getObtainMarks());
         fr.setMaxMarks(sr.getMaxMarks());
         fr.setResultId(sr.getResultId());
         fr.setAttemptedQuestions(sr.getAttemptedQuestions());
         fr.setCategoryId(sr.getCategoryId());
         fr.setCategoryName(sr.getCategoryName());
         fr.setEmail(sr.getEmail());
         
        when(quizRepo.findQuizByName("Array")).thenReturn(Optional.of(quiz));
        when(categoryRepo.findByCategoryName("Java")).thenReturn(Optional.of(category));
         ResultDto result= resultService.addResult(sr);
        assertEquals("satya@nucleusteq.com",result.getEmail());
    }
    @Test
    void testAddNoEmailFound() {
        ResultDto sr = new ResultDto();
        when(studentRepo.findByEmail("satya@nucleusteq.com")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,   ()->{
            resultService.addResult(sr);
        });
    }
    @Test
    void testAddQuizNotFoundExcetion() {
        ResultDto sr = new ResultDto(101,"23-10-23","pass",19, "Madhuri","satya@nucleusteq.com","Array",
                "Java",97,9,10,1,12);
        Student student=new Student();
        student.setEmail("satya@nucleusteq.com");
        when(studentRepo.findByEmail("satya@nucleusteq.com")).thenReturn(Optional.of(student));
        FinalRes fr=new FinalRes();
        Optional<Category> c=categoryRepo.findById(sr.getCategoryId());
        System.out.println(c);
         fr.setCategoryName(sr.getCategoryName()); 
          fr.setUserId(sr.getUserId());
         fr.setUserName(sr.getUserName());
         fr.setQuizTopic(sr.getQuizName());
         fr.setDateAndTime(sr.getDateAndTime());
         fr.setMarks(sr.getObtainMarks());
         fr.setMaxMarks(sr.getMaxMarks());
         fr.setResultId(sr.getResultId());
         fr.setAttemptedQuestions(sr.getAttemptedQuestions());
         fr.setCategoryId(sr.getCategoryId());
         fr.setCategoryName(sr.getCategoryName());
         fr.setEmail(sr.getEmail());
         
        when(quizRepo.findQuizByName("Array")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,   ()->{ resultService.addResult(sr); });
    }
    @Test
    void testAddCategoryNotPresent() {
        ResultDto sr = new ResultDto(101,"23-10-23","pass",19, "Madhuri","satya@nucleusteq.com","Array",
                "Java",97,9,10,1,12);
        Student student=new Student();
        student.setEmail("satya@nucleusteq.com");
        when(studentRepo.findByEmail("satya@nucleusteq.com")).thenReturn(Optional.of(student));
        Quiz quiz = new Quiz();
        FinalRes fr=new FinalRes();
        Optional<Category> c=categoryRepo.findById(sr.getCategoryId());
        System.out.println(c);
         fr.setCategoryName(sr.getCategoryName()); 
          fr.setUserId(sr.getUserId());
         fr.setUserName(sr.getUserName());
         fr.setQuizTopic(sr.getQuizName());
         fr.setDateAndTime(sr.getDateAndTime());
         fr.setMarks(sr.getObtainMarks());
         fr.setMaxMarks(sr.getMaxMarks());
         fr.setResultId(sr.getResultId());
         fr.setAttemptedQuestions(sr.getAttemptedQuestions());
         fr.setCategoryId(sr.getCategoryId());
         fr.setCategoryName(sr.getCategoryName());
         fr.setEmail(sr.getEmail());
         
        when(quizRepo.findQuizByName("Array")).thenReturn(Optional.of(quiz));
        when(categoryRepo.findByCategoryName("Java")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,   ()->{ resultService.addResult(sr);    });
    }
   @Test
    void testGetResult() {
        StudentResult s=new StudentResult();
        s.setResultId(1);
        Quiz q=new Quiz();
        q.setTopicName("variables");
        s.setQe(q);
        Student ss=new Student();
        ss.setUserId(18);
        s.setSs(ss);
        Category c=new Category();
        c.setCategoryId(13);
        
        when(resultRepo.findAll()).thenReturn(Collections.singletonList(new StudentResult()));
        when(resultRepo.findById(1)).thenReturn(Optional.of(s));
        when(categoryRepo.findById(13)).thenReturn(Optional.of(c));
        s.setCategoryId(13);
        Optional<ResultDto> resultDto=resultService.getResult(s.getResultId());
       assertEquals(resultDto.get().getQuizName(),s.getQe().getTopicName());    
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
        StudentResult s=new StudentResult();
        s.setResultId(1);
        Quiz q=new Quiz();
        q.setTopicName("variables");
        s.setQe(q);
        Student ss=new Student();
        ss.setUserId(18);
        s.setSs(ss);
        Category c=new Category();
        c.setCategoryName("java");
        c.setCategoryId(13);
        s.setCategoryId(13);
        List<StudentResult> studentResult=new ArrayList<>();
        studentResult.add(s);
        
        when(resultRepo.findAll()).thenReturn(studentResult);
       when(resultRepo.findById(1)).thenReturn(Optional.of(s));
        when(categoryRepo.findById(13)).thenReturn(Optional.of(c));
        assertTrue(Optional.of(c).isPresent());
        List<ResultDto> resultDto=resultService.getResults();
        assertEquals(s.getCategoryId(),resultDto.get(0).getCategoryId());       
   }
    @Test
   void testNoStudentResultIsPresent() {
        when(resultRepo.findAll()).thenReturn(Collections.emptyList());
        assertThrows(AllNotFoundException.class,   ()->{ resultService.getResults();    });
   }
}
