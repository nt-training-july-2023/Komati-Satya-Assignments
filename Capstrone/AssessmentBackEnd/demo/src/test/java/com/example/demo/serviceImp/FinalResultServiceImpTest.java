package com.example.demo.serviceImp;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.ResultDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.FinalRes;

import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.FinalResultRepo;
import com.example.demo.repository.StudentResultRepo;

class FinalResultServiceImpTest {

    @InjectMocks
    private FinalResultServiceImp finalService;
    @Mock
    private FinalResultRepo finalRepo;
    @Mock
    private StudentResultRepo resultRepo;
    @Mock
    private CategoryRepo categoryRepo;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testFindResultByUserId() {        
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
        finalRess.setUserId(1);
        finalRess.setUserName("satya");
        finalRess.setMaxMarks(101);
  
        
        Category category=new Category();
        category.setCategoryId(1);
        category.setCategoryName("java");
        category.setCategoryDescription("javaa");
        
        List<FinalRes> finallist=new ArrayList<>();
        finallist.add(finalRess);
        finalRepo.save(finalRess);
        
        when(finalRepo.findAll()).thenReturn(finallist);
        List<ResultDto> resultDtoList=finalService.getById(1);
        System.out.println(finalService.getById(1));
        }
    
   
    @Test
    void testFindByUserNoUserFound() {
        int userId=19;
        when(finalRepo.findAll()).thenReturn(Collections.emptyList());
        assertThrows(AllNotFoundException.class, () ->{
            finalService.getById(userId);
        });
    }
    @Test
    void testFindAllResult() {
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
        
        Category category=new Category();
        category.setCategoryId(1);
        category.setCategoryName("java");
        category.setCategoryDescription("javaa");
        List<FinalRes> f=new ArrayList<>();
        f.add(finalRess);
        finalRepo.save(finalRess);
        when(finalRepo.findAll()).thenReturn(Collections.singletonList(finalRess));
        List<ResultDto> r=finalService.findAll();
        assertEquals(1,r.size());  
    }
    @Test
    void testFindAllNotFound() {
        int userId=19;
        FinalRes finalRes=new FinalRes();
        when(finalRepo.findAll()).thenReturn(Collections.emptyList());
        assertThrows(AllNotFoundException.class, () ->{
            finalService.findAll();
        });
    }

}
