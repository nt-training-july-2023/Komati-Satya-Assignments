package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import com.example.demo.dto.ResultDto;
import com.example.demo.entity.StudentResult;
import com.example.demo.response.Response;
import com.example.demo.serviceImp.ResultServiceImp;

class ResultControllerTest {

    @Mock
    private ResultServiceImp resultService;
    @InjectMocks
    private ResultController resultController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddResultSuccess() {
      StudentResult studentResult=new StudentResult();
      studentResult.setResultId(1);
      ResultDto resultDto=new ResultDto();
      resultDto.setResultId(1);
      
      when(resultService.addResult(resultDto)).thenReturn(resultDto);
      Response response=resultController.addResult(resultDto);
      assertEquals(HttpStatus.OK.value(),response.getCode());
      assertEquals("result saved successfully",response.getMessage());
    }
   @Test
    void testGetResultSuccess() {
       StudentResult studentResult=new StudentResult();
       studentResult.setResultId(1);
       ResultDto resultDto=new ResultDto();
       resultDto.setResultId(1);
       
       when(resultService.getResult(1)).thenReturn(Optional.of(resultDto));
       Response response=resultController.getResult(1);
       assertEquals(HttpStatus.OK.value(),response.getCode());
       assertEquals(Optional.of(resultDto),response.getData());
    }
   @Test
   void testGetAllResultSuccess() {
       List<ResultDto> resultDto=new ArrayList<>();
       when(resultService.getResults()).thenReturn(resultDto);
       Response response=resultController.getAllResults();
       assertEquals(HttpStatus.OK.value(),response.getCode());
       assertEquals(resultDto,response.getData());
   }
}
