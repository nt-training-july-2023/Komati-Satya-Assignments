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

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.QuizDto;
import com.example.demo.dto.QuizUpdateDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Quiz;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.QuizRepo;

class QuizServiceImpTest {

    @InjectMocks
    private QuizServiceImp quizService;
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
        Category category=new Category(4,"java","java basics");
        when(categoryRepo.findById(quizDto.getCategoryId())).thenReturn(Optional.of(category));
        when(quizRepo.findQuizByName(quizDto.getTopicName())).thenReturn(Optional.empty());
        Quiz quiz=new Quiz();
        quiz.setCategory(category);
        quiz.setQuizId(quizDto.getQuizId());
        quiz.setTopicDescription(quizDto.getTopicDescription());
        quiz.setTopicName(quizDto.getTopicName());
        QuizDto quizDto2 = quizService.addQuiz(quizDto);
        assertEquals(quizDto2,quizDto);
    }
    @Test
    public void testCategoryNotPresent() {
        QuizDto quizDto=new QuizDto(1,"variables","java variables",60,3);
        when(categoryRepo.findById(quizDto.getCategoryId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{  quizService.addQuiz(quizDto);
        });
    }
    @Test 
    public void testQuizAlreadyPresent() {
     QuizDto quizDto=new QuizDto(1,"variables","java variables",60,2);   
     Category category=new Category(2,"java","java basics");
     Quiz quiz=new Quiz(1,"variables","java variables",60);
     
     when(categoryRepo.findById(quizDto.getCategoryId())).thenReturn(Optional.of(category));
     when(quizRepo.findQuizByName(quizDto.getTopicName())).thenReturn(Optional.of(quiz));
     assertThrows(AlreadyExistException.class, () ->{ quizService.addQuiz(quizDto);
     });
    }
    @Test
    public void testGetQuizById() {
        Quiz quiz=new Quiz(1,"variables","java variables",60);   
        Category category=new Category(10,"java","java basics");
        quiz.setCategory(category);
        QuizDto expectedQuizDto=new QuizDto(1,"variables","java variables",10,60);   
        
        when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
        when(quizRepo.findById(quiz.getQuizId())).thenReturn(Optional.of(quiz));
        QuizDto quizDto=quizService.getQuiz(quiz.getQuizId());
        assertEquals(quizDto,expectedQuizDto);
    }
    @Test
    public void testQuizNotPresent() {
        int quizId=1;
        when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
        when(quizRepo.findById(quizId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{   quizService.getQuiz(quizId);
    });
    }
    
    @Test
    public void testNoQuizIsPresent() {
        int quizId=1;
        when(quizRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, () ->{  quizService.getQuiz(quizId);
    });
    }
    
    @Test
    public void testFindAll() {
        QuizDto quizDto = new QuizDto();
        quizDto.setTopicName("arrays");
        quizDto.setQuizId(1);
        Category category = new Category(2,"java","java basics");
        quizDto.setCategoryId(2);
        
        Quiz quiz = new Quiz();
        quiz.setQuizId(quizDto.getQuizId());
        quiz.setTopicName(quizDto.getTopicName());
        quiz.setCategory(category);
        List<Quiz> listofquiz = new ArrayList<>();
        listofquiz.add(quiz);
        
        quizRepo.save(quiz);
        when(quizRepo.findAll()).thenReturn(listofquiz);
        List<QuizDto> quizList = quizService.findAll();
        assertEquals(Collections.singletonList(quizDto),quizList);
    }
    @Test
    public void testAllquizNotFound() {
        when(quizRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, ()->{ quizService.findAll();
        });
    }
    @Test
    public void testUpdateQuiz() {
        Quiz quiz=new Quiz(1,"variables","java variables",60);   
        Category c=new Category(10,"java","java basics");
        quiz.setCategory(c);
        quizRepo.save(quiz);
        QuizDto quizDto=new QuizDto();
        quizDto.setCategoryId(quiz.getCategory().getCategoryId());
        quizDto.setQuizId(quiz.getQuizId());
        quizDto.setTopicName(quiz.getTopicName());
        quizDto.setTopicDescription(quiz.getTopicDescription());
        
        when(quizRepo.findById(quiz.getQuizId())).thenReturn(Optional.of(quiz));
        assertTrue(Optional.of(quiz).isPresent());
        QuizUpdateDto quizUpdateDto=new QuizUpdateDto(1,"variables","java basics",60);
        List<Quiz> quizList=new ArrayList<>();
        quizList.add(quiz);
        when(quizRepo.findAll()).thenReturn(quizList);
        QuizUpdateDto qd=quizService.updateQuiz(quizUpdateDto, 1);
        assertEquals(qd,quizUpdateDto);
        
    }
    @Test
    public void testNoQuizisPresent() {
        QuizUpdateDto quizUpdateDto=new QuizUpdateDto(1,"variables","java basics",20);
        when(quizRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class , () ->{  quizService.updateQuiz(quizUpdateDto,1);
        });
    }
    @Test
    public void testUpdateQuizNotPresent() {
        QuizUpdateDto quizUpdateDto=new QuizUpdateDto(1,"variables","java basics",20);
        when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
        when(quizRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{ quizService.updateQuiz(quizUpdateDto,1);
    });
    }
    
    @Test
    public void testDeleteQuiz() {
        Quiz quiz=new Quiz(1,"variables","java variables",60);   
        Category category=new Category(10,"java","java basics");
        quiz.setCategory(category);
        quizRepo.save(quiz);
        
        when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
        when(quizRepo.findById(1)).thenReturn(Optional.of(quiz));
        quizService.deleteQuiz(1); 
        assertFalse(quizRepo.existsById(1));
    }
    @Test
    public void testDeleteIdNotFound() {
        int quizId=1;
        when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
        when(quizRepo.findById(quizId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{ quizService.deleteQuiz(quizId);
        });  
    }
    @Test
    public void testDeleteNoQuizNotFind() {
        int quizId=1;
        when(quizRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class , () ->{ quizService.deleteQuiz(quizId);
        });
    }
    @Test
    public void testQuizByCategoryId() {
        QuizDto quizDto=new QuizDto(1,"variables","java variables",10,60); 
        CategoryDto categoryDto=new CategoryDto(10,"java","java basics");
        Category category = new Category();
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryId(categoryDto.getCategoryId());
        Quiz quiz=new Quiz();
        quiz.setCategory(category);
        quiz.setQuizId(quizDto.getQuizId());
        quiz.setTopicName(quizDto.getTopicName());
        quiz.setTopicDescription(quizDto.getTopicDescription());
        quiz.setTimer(quizDto.getTimer());
     
        List<Quiz> quizList=new ArrayList<>();
        quizList.add(quiz);
        
        when(quizRepo.findQuizById(1)).thenReturn(quizList);
        List<QuizDto> quizDtoList=quizService.findQuizById(1);
       assertEquals(quizDtoList,Collections.singletonList(quizDto));
    }
     @Test
    void testFindQuizByCategoryNoQuizException() {
         int categoryId=10;
         when(quizRepo.findQuizById(10)).thenReturn(new ArrayList<>());
         assertThrows(AllNotFoundException.class , () ->{ quizService.findQuizById(categoryId);
         });
     }
     @Test   
     void testFindQuizByQuizName() {
         Quiz quiz=new Quiz(1,"variables","java variables",60);   
         Category category=new Category(10,"java","java basics");
         quiz.setCategory(category);
         QuizDto quizDto2=new QuizDto(1,"variables","java variables",10,60);
         
         when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
         when(quizRepo.findQuizByName(quiz.getTopicName())).thenReturn(Optional.of(quiz));
         QuizDto quizDto=quizService.findQuizByName(quiz.getTopicName());
         assertEquals(quizDto,quizDto2);
    }
     @Test
   void testQuizNotFoundException() {
         String quizName="arrays";
         when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
         when(quizRepo.findQuizByName(quizName)).thenReturn(Optional.empty());
         assertThrows(NotFoundException.class , () ->{  quizService.findQuizByName(quizName);
         });
   }
     @Test
     void testFindQuizNoQuizIsPresent() {
         String quizName="arrays";
         when(quizRepo.findAll()).thenReturn(new ArrayList<>());
         assertThrows(AllNotFoundException.class , () ->{ quizService.findQuizByName(quizName);
         });
     }

}
