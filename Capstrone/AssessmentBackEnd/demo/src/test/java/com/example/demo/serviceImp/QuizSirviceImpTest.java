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

import com.example.demo.dto.QuizDto;
import com.example.demo.dto.QuizUpdateDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Quiz;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.QuizRepo;

class QuizSirviceImpTest {

    @InjectMocks
    private QuizSirviceImp quizService;
    @Mock
    private QuizRepo quizRepo;
    @Mock
    private CategoryRepo categoryRepo;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    
    @Test
    void testAddQuiz() {
        QuizDto quizDto=new QuizDto(1,"variables","java variables",60,4);
        Category c=new Category(4,"java","java basics");
        when(categoryRepo.findById(quizDto.getCategoryId())).thenReturn(Optional.of(c));
        when(quizRepo.findQuizByName(quizDto.getTopicName())).thenReturn(Optional.empty());
        Quiz quiz=new Quiz();
        quiz.setCategory(c);
        quiz.setQuizId(quizDto.getQuizId());
        quiz.setTopicDescription(quizDto.getTopicDescription());
        quiz.setTopicName(quizDto.getTopicName());
        quizService.addQuiz(quizDto);
    }
    @Test
    public void testCategoryNotPresent() {
        QuizDto q=new QuizDto(1,"variables","java variables",60,3);
        Category c=new Category(3,"java","java basics");
        when(categoryRepo.findById(q.getCategoryId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            quizService.addQuiz(q);
        });
    }
    @Test 
    public void testQuizAlreadyPresent() {
     QuizDto quizDto=new QuizDto(1,"variables","java variables",60,2);   
     Category c=new Category(2,"java","java basics");
     Quiz quiz=new Quiz(1,"variables","java variables",60);
     
     when(categoryRepo.findById(quizDto.getCategoryId())).thenReturn(Optional.of(c));
     when(quizRepo.findQuizByName(quizDto.getTopicName())).thenReturn(Optional.of(quiz));
     assertThrows(AlreadyExistException.class, () ->{
         quizService.addQuiz(quizDto);
     });
    }
    @Test
    public void testGetQuizById() {
        Quiz q=new Quiz(1,"variables","java variables",60);   
        Category c=new Category(10,"java","java basics");
        q.setCategory(c);
        
        when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
        when(quizRepo.findById(q.getQuizId())).thenReturn(Optional.of(q));
        Optional<QuizDto> quizDto=quizService.getQuiz(q.getQuizId());
        assertEquals(q.getCategory().getCategoryId(),quizDto.get().getCategoryId());
        assertEquals(q.getTopicName(),quizDto.get().getTopicName());
        assertEquals(q.getTopicDescription(),quizDto.get().getTopicDescription());
 
        assertEquals(q.getQuizId(),quizDto.get().getQuizId());
    }
    @Test
    public void testQuizNotPresent() {
        int quizId=1;
        when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
        when(quizRepo.findById(quizId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            quizService.getQuiz(quizId);
    });
    }
    @Test
    public void testNoQuizIsPresent() {
        int quizId=1;
        when(quizRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, () ->{
            quizService.getQuiz(quizId);
    });
    }
    
    @Test
    public void testFindAll() {
        List<Quiz> quiz=new ArrayList<>();
        Quiz q=new Quiz(1,"variables","java variables",60);   
        Category c=new Category(10,"java","java basics");
        q.setCategory(c);
        quiz.add(q);
        
        when(quizRepo.findAll()).thenReturn(quiz);
        List<QuizDto> quizDto=quizService.findAll();
        assertEquals(1,quizDto.size());
        assertEquals(q.getQuizId(),quizDto.get(0).getQuizId());
        assertEquals(q.getTopicName(),quizDto.get(0).getTopicName());
        assertEquals(q.getTopicDescription(),quizDto.get(0).getTopicDescription());

        assertEquals(q.getQuizId(),quizDto.get(0).getQuizId());
    }
    @Test
    public void testAllquizNotFound() {
        when(quizRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, ()->{
            quizService.findAll();
        });
    }
    @Test
    public void testUpdateQuiz() {
        Quiz q=new Quiz(1,"variables","java variables",60);   
        Category c=new Category(10,"java","java basics");
        q.setCategory(c);
        quizRepo.save(q);
        QuizDto quizDto=new QuizDto();
        quizDto.setCategoryId(q.getCategory().getCategoryId());
        quizDto.setQuizId(q.getQuizId());
        quizDto.setTopicName(q.getTopicName());
        quizDto.setTopicDescription(q.getTopicDescription());
        
        when(quizRepo.findById(q.getQuizId())).thenReturn(Optional.of(q));
        assertTrue(Optional.of(q).isPresent());
        QuizUpdateDto quiz=new QuizUpdateDto(1,"variables","java basics",60);
        List<Quiz> quizList=new ArrayList<>();
        quizList.add(q);
        when(quizRepo.findAll()).thenReturn(quizList);
        QuizUpdateDto qd=quizService.updateQuiz(quiz, 1);
        assertEquals(q.getTopicName(),qd.getTopicName());
        assertEquals(q.getTopicDescription(),qd.getTopicDescription());
        assertEquals(q.getQuizId(),qd.getQuizId());
        
    }
    @Test
    public void testNoQuizisPresent() {
        QuizUpdateDto d=new QuizUpdateDto(1,"variables","java basics",20);
        when(quizRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class , () ->{
            quizService.updateQuiz(d,1);
        });
    }
    @Test
    public void testUpdateQuizNotPresent() {
        QuizUpdateDto d=new QuizUpdateDto(1,"variables","java basics",20);
        when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
        when(quizRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            quizService.updateQuiz(d,1);
    });
    }
    
    @Test
    public void testDeleteQuiz() {
        Quiz q=new Quiz(1,"variables","java variables",60);   
        Category c=new Category(10,"java","java basics");
        q.setCategory(c);
        quizRepo.save(q);
        
        when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
        when(quizRepo.findById(1)).thenReturn(Optional.of(q));
        quizService.deleteQuiz(1);              
    }
    @Test
    public void testDeleteIdNotFound() {
        int quizId=1;
        when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
        when(quizRepo.findById(quizId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            quizService.deleteQuiz(quizId);
        });  
    }
    @Test
    public void testDeleteNoQuizNotFind() {
        int quizId=1;
        when(quizRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class , () ->{
            quizService.deleteQuiz(quizId);
        });
    }
    @Test
    public void testQuizByCategoryId() {
        Quiz q1=new Quiz(1,"variables","java variables",60);   
        Category c=new Category(10,"java","java basics");
        q1.setCategory(c);
        Quiz q2=new Quiz(2,"arrays","java arrays",60);
        q2.setCategory(c);
        List<Quiz> quiz=new ArrayList<>();
        quiz.add(q1);
        quiz.add(q2);
        
        when(quizRepo.findQuizById(10)).thenReturn(quiz);
        List<QuizDto> quizDto=quizService.findQuizById(10);
        System.out.println(quizDto);
      assertEquals("variables",quizDto.get(0).getTopicName());  
      assertEquals("java variables",quizDto.get(0).getTopicDescription());
   
      assertEquals(10,quizDto.get(0).getCategoryId());
        
    }
     @Test
    void testFindQuizByCategoryNoQuizException() {
         int categoryId=10;
         when(quizRepo.findQuizById(10)).thenReturn(new ArrayList<>());
         assertThrows(AllNotFoundException.class , () ->{
             quizService.findQuizById(categoryId);
         });
     }
     @Test   
     void testFindQuizByQuizName() {
         Quiz q=new Quiz(1,"variables","java variables",60);   
         Category c=new Category(10,"java","java basics");
         q.setCategory(c);
         
         when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
         when(quizRepo.findQuizByName(q.getTopicName())).thenReturn(Optional.of(q));
         Optional<QuizDto> quizDto=quizService.findQuizByName(q.getTopicName());
         assertTrue(quizDto.isPresent());    
         assertEquals(q.getCategory().getCategoryId(),quizDto.get().getCategoryId());
         assertEquals(q.getTopicName(),quizDto.get().getTopicName());
         assertEquals(q.getTopicDescription(),quizDto.get().getTopicDescription());
         assertEquals(q.getQuizId(),quizDto.get().getQuizId());
    }
     @Test
   void testQuizNotFoundException() {
         String quizName="arrays";
         when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
         when(quizRepo.findQuizByName(quizName)).thenReturn(Optional.empty());
         assertThrows(NotFoundException.class , () ->{
             quizService.findQuizByName(quizName);
         });
   }
     @Test
     void testFindQuizNoQuizIsPresent() {
         String quizName="arrays";
         when(quizRepo.findAll()).thenReturn(new ArrayList<>());
         assertThrows(AllNotFoundException.class , () ->{
             quizService.findQuizByName(quizName);
         });
     }

}
