package com.example.demo.serviceImp;



import static org.junit.jupiter.api.Assertions.*;
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

import com.example.demo.exceptions.AllNotFoundException;import com.example.demo.repository.CategoryRepo;
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
        ResultDto resultDto = new ResultDto(101,"23-10-23",19, "Madhuri","satya@nucleusteq.com","Array",
                "Java",97,9,10,1,12);
        FinalRes finalRess=new FinalRes();
        finalRess.setCategoryName("Java");
        finalRess.setDateAndTime("23-10-23");
        finalRess.setEmail("satya@nucleusteq.com");
        finalRess.setMarks(97);
        finalRess.setFinalId(12);
        finalRess.setQuizTopic("Array");
        finalRess.setUserId(1);
        finalRess.setUserName("Madhuri");
        finalRess.setMaxMarks(101);
  
        
        Category category=new Category();
        category.setCategoryId(9);
        category.setCategoryName("Java");
        category.setCategoryDescription("javaa");
        
        List<FinalRes> finallist=new ArrayList<>();
        finallist.add(finalRess);
        finalRepo.save(finalRess);
        
        when(finalRepo.findAll()).thenReturn(finallist);
        List<ResultDto> resultDtoList=finalService.getById(1);
        
    }
    
   
    @Test
    void testFindByUserNoUserFound() {
        int userId=19;
        when(finalRepo.findAll()).thenReturn(Collections.emptyList());
        assertThrows(AllNotFoundException.class, () ->{ finalService.getById(userId);
        });
    }
    @Test
    void testFindAllResult() {
        ResultDto resultDto = new ResultDto(101,"23-10-23",19, "Madhuri","satya@nucleusteq.com","Array",
                "java",97,9,10,1,12);
        int studentId=1;
        FinalRes finalRess=new FinalRes();
        finalRess.setCategoryName("java");
        finalRess.setDateAndTime("23-10-23");
        finalRess.setEmail("satya@nucleusteq.com");
        finalRess.setMarks(102);
        finalRess.setFinalId(12);
        finalRess.setQuizTopic("Array");
        finalRess.setUserId(studentId);
        finalRess.setUserName("Madhuri");
        finalRess.setMaxMarks(97);
        finalRess.setQuizTopic("Array");
        finalRess.setAttemptedQuestions(19);
        finalRess.setTotalNoOfQuestions(12);

        Category category=new Category();
        category.setCategoryId(9);
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
        when(finalRepo.findAll()).thenReturn(Collections.emptyList());
        assertThrows(AllNotFoundException.class, () ->{ finalService.findAll();
        });
    }

}
