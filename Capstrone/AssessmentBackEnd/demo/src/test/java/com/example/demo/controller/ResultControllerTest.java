package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
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
      
      Response expectedResponse =new Response();
      expectedResponse.setCode(HttpStatus.OK.value());
      expectedResponse.setMessage("result saved successfully");
      
      when(resultService.addResult(resultDto)).thenReturn(resultDto);
      Response response=resultController.addResult(resultDto);
      assertEquals(response,expectedResponse);
    }
   @Test
    void testGetResultSuccess() {
       StudentResult studentResult=new StudentResult();
       studentResult.setResultId(1);
       ResultDto resultDto=new ResultDto();
       resultDto.setResultId(1);
       
       Response expectedResponse =new Response();
       expectedResponse.setCode(HttpStatus.OK.value());
       expectedResponse.setMessage("successfully retrieve the student result");
       expectedResponse.setData(resultDto);
       
       when(resultService.getResult(1)).thenReturn(resultDto);
       Response response=resultController.getResult(1);
       assertEquals(response,expectedResponse);
    }
   @Test
   void testGetAllResultSuccess() {
       List<ResultDto> resultDto=new ArrayList<>();
       
       Response expectedResponse =new Response();
       expectedResponse.setCode(HttpStatus.OK.value());
       expectedResponse.setMessage("successfully retrieve the"
               + "student results");
       expectedResponse.setData(resultDto);
       
       when(resultService.getResults()).thenReturn(resultDto);
       Response response=resultController.getAllResults();
       assertEquals(response,expectedResponse);
   }
}
