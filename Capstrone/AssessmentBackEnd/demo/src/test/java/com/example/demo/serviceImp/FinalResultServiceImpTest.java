package com.example.demo.serviceImp;

import static org.junit.Assert.assertThrows;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.dto.ResultDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.FinalRes;

import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.FinalResultRepo;
import com.example.demo.repository.StudentResultRepo;

class FinalResultServiceImpTest {

    
    private FinalResultServiceImp finalService;
    private FinalResultRepo finalRepo;
    private StudentResultRepo resultRepo;
    private CategoryRepo categoryRepo;
    
    @BeforeEach
    void setUp() {
        finalRepo=mock(FinalResultRepo.class);
        categoryRepo=mock(CategoryRepo.class);
        resultRepo=mock(StudentResultRepo.class);
        finalService=new FinalResultServiceImp(finalRepo,categoryRepo,resultRepo);
    }
    @Test
    void testFindResultByUserId() {
        
//        int userId=1;
//        FinalRes finalRess=new FinalRes();
//        finalRess.setCategoryName("java");
//        finalRess.setDateAndTime("");
//        finalRess.setEmail("satya@nucleusteq.com");
//        finalRess.setMarks(100);
//        finalRess.setFinalId(12);
//        finalRess.setQuizTopic("variable");
//        finalRess.setResult("fail");
//        finalRess.setResultId(13);
//        finalRess.setUserId(userId);
//        finalRess.setUserName("satya");
//        List<FinalRes> f=new ArrayList<>();
//        when(finalRepo.findAll()).thenReturn(Collections.singletonList(finalRess));
//        when(finalRepo.getByUserId(userId)).thenReturn(Optional.of(finalRess));
//        StudentResult studentResult=new StudentResult();
//        when(resultRepo.findById(userId)).thenReturn(Optional.of(studentResult));
//        studentResult.setResultId(13);
//        studentResult.setResult("fail");
//        studentResult.setCategoryId(89);
//        studentResult.setDateAndTime("120987");
//        studentResult.setMaxMarks(100);
      //  studentResult.set
//        StudentResult fr=Optional.of(studentResult).get();
//        Optional<FinalRes> finalRes=finalRepo.getByUserId(userId);
//        assertEquals(13,finalRes.get().getResultId());
//        ResultDto resultDto=new ResultDto();
//        Optional<StudentResult>// sa=resulOptional<StudentResult> sa;
 //       when(resultRepo.findById(13).thenReturn(Optional.of(studentResult));
    //    StudentResult fr=sa.get();
//       resultDto.setUserName(fr.getSs().getUserName());
//        resultDto.setEmail(fr.getSs().getEmail());
//        Optional<Category> c=categoryRepo.findById(fr.getCategoryId());
//        resultDto.setCategoryName(c.get().getCategoryName());
//        resultDto.setQuizName(fr.getQe().getTopicName());
//        resultDto.setResult(fr.getResult());
//        resultDto.setMaxMarks(finalRes.get().getMaxMarks());
//        resultDto.setDateAndTime(fr.getDateAndTime());
//        resultDto.setObtainMarks(finalRes.get().getMarks());
//        resultDto.setAttemptedQuestions(fr.getAttemptedQuestions());
//        resultDto.setCategoryId(c.get().getCategoryId());
//        resultDto.setResultId(fr.getResultId());
//        when(finalService.getById(userId)).thenReturn(Optional.of(resu ltDto));
//      //m  assertTrue(result.isPresent());
        
        int studentId=1;
        ResultDto finalDto = new ResultDto(101,"23-10-23","pass",19, "Madhuri","satya@nucleusteq.com","Array",
                "Java",97,9,10,1,12);
        FinalRes finalRess=new FinalRes();
        finalRess.setCategoryName("java");
        finalRess.setDateAndTime("23-10-23");
        finalRess.setEmail("satya@nucleusteq.com");
        finalRess.setMarks(97);
        finalRess.setFinalId(12);
        finalRess.setQuizTopic("Array");
        finalRess.setResultId(13);
        finalRess.setUserId(studentId);
        finalRess.setUserName("satya");
        finalRess.setMaxMarks(101);
        Category c=new Category();
        c.setCategoryId(1);
        c.setCategoryName("java");
        c.setCategoryDescription("javaa");
        List<FinalRes> f=new ArrayList<>();
        f.add(finalRess);
        finalRepo.save(finalRess);
        when(finalRepo.getByUserId(studentId)).thenReturn(f);
        when(finalRepo.findAll()).thenReturn(Collections.singletonList(finalRess));
        List<ResultDto> r=finalService.getById(studentId);
        assertEquals(f.get(0).getCategoryName(),r.get(0).getCategoryName());  
    }
    
    @Test
    void testFindByUserNotFound() {
        int userId=19;
        FinalRes finalRes=new FinalRes();
        when(finalRepo.findAll()).thenReturn(Collections.singletonList(finalRes));
        when(finalRepo.getByUserId(userId)).thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class, () ->{
            finalService.getById(userId);
        });
    }
    @Test
    void testFindByUserNoUserFound() {
        int userId=19;
        when(finalRepo.findAll()).thenReturn(Collections.emptyList());
        assertThrows(AllNotFoundException.class, () ->{
            finalService.getById(userId);
        });
    }

}
