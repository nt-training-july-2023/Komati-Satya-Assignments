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

class QuizSirviceImpTest {

    private QuizSirviceImp quizService;
    private QuizRepo quizRepo;
    private CategoryRepo categoryRepo;
    
    @BeforeEach
    void setUp() {
        quizRepo=mock(QuizRepo.class);
        categoryRepo=mock(CategoryRepo.class);
        quizService=new QuizSirviceImp(quizRepo,categoryRepo);
    }
    
    
    @Test
    void testAddQuiz() {
        Quiz q=new Quiz(1,"variables","java variables",15,10);
        Category c=new Category(10,"java","java basics");
        q.setCate(c);
        when(categoryRepo.findById(q.getCate().getCategoryId())).thenReturn(Optional.of(c));
        when(quizRepo.findQuizByName(q.getTopicName())).thenReturn(Optional.empty());
        QuizDto quizDto=new QuizDto();
        quizDto.setCategoryId(c.getCategoryId());
        quizDto.setMaxMarks(q.getMaxMarks());
        quizDto.setPassMarks(q.getMaxMarks());
        quizDto.setQuizId(q.getQuizId());
        quizDto.setTopicDescription(q.getTopicDescription());
        quizDto.setTopicName(q.getTopicName());
        quizService.addQuiz(q);
    }
    @Test
    public void testCategoryNotPresent() {
        Quiz q=new Quiz(1,"variables","java variables",15,10);
        Category c=new Category(10,"java","java basics");
        q.setCate(c);
        when(categoryRepo.findById(q.getCate().getCategoryId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            quizService.addQuiz(q);
        });
    }
    @Test 
    public void testQuizAlreadyPresent() {
     Quiz q=new Quiz(1,"variables","java variables",15,10);   
     Category c=new Category(10,"java","java basics");
     q.setCate(c);
     when(categoryRepo.findById(q.getCate().getCategoryId())).thenReturn(Optional.of(c));
     when(quizRepo.findQuizByName(q.getTopicName())).thenReturn(Optional.of(q));
     assertThrows(AlreadyExistException.class, () ->{
         quizService.addQuiz(q);
     });
    }
    @Test
    public void testGetQuizById() {
        Quiz q=new Quiz(1,"variables","java variables",15,10);   
        Category c=new Category(10,"java","java basics");
        q.setCate(c);
        when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
        when(quizRepo.findById(q.getQuizId())).thenReturn(Optional.of(q));
        Optional<QuizDto> quizDto=quizService.getQuiz(q.getQuizId());
        assertEquals(q.getCate().getCategoryId(),quizDto.get().getCategoryId());
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
        Quiz q=new Quiz(1,"variables","java variables",15,10);   
        Category c=new Category(10,"java","java basics");
        q.setCate(c);
        quiz.add(q);
        when(quizRepo.findAll()).thenReturn(quiz);
        List<QuizDto> quizDto=quizService.findAll();
        assertEquals(1,quizDto.size());
        assertEquals(q.getQuizId(),quizDto.get(0).getQuizId());
        assertEquals(q.getTopicName(),quizDto.get(0).getTopicName());
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
        Quiz q=new Quiz(1,"variables","java variables",15,10);   
        Category c=new Category(10,"java","java basics");
        q.setCate(c);
        quizRepo.save(q);
        QuizDto quizDto=new QuizDto();
        quizDto.setCategoryId(q.getCate().getCategoryId());
        quizDto.setQuizId(q.getQuizId());
        quizDto.setTopicName(q.getTopicName());
        quizDto.setTopicDescription(q.getTopicDescription());
        quizDto.setPassMarks(q.getPassMarks());
        quizDto.setMaxMarks(q.getMaxMarks());
        when(quizRepo.findById(q.getQuizId())).thenReturn(Optional.of(q));
        assertTrue(Optional.of(q).isPresent());
        QuizUpdateDto quiz=new QuizUpdateDto(1,"variables","java basics",20,15);
        List<Quiz> quizList=new ArrayList<>();
        quizList.add(q);
        when(quizRepo.findAll()).thenReturn(quizList);
        QuizUpdateDto qd=quizService.updateQuiz(quiz, 1);
        assertEquals(quiz.getPassMarks(),qd.getPassMarks());
    }
    @Test
    public void testNoQuizisPresent() {
        QuizUpdateDto d=new QuizUpdateDto(1,"variables","java basics",20,15);
        when(quizRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class , () ->{
            quizService.updateQuiz(d,1);
        });
    }
    @Test
    public void testUpdateQuizNotPresent() {
        QuizUpdateDto d=new QuizUpdateDto(1,"variables","java basics",20,15);
        when(quizRepo.findAll()).thenReturn(Collections.singletonList(new Quiz()));
        when(quizRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            quizService.updateQuiz(d,1);
    });
    }

}
