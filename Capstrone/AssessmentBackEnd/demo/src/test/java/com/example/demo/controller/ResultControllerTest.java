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
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ResultDto;
import com.example.demo.entity.StudentResult;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.NotFoundException;
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
      when(resultService.addRes(resultDto)).thenReturn(resultDto);
      ResponseEntity<Object> response=resultController.addRes(resultDto);
      assertEquals(HttpStatus.OK,response.getStatusCode());
      assertNotNull(response.getBody());
    }
    @Test
    void testAddResultError() {
      StudentResult studentResult=new StudentResult();
      studentResult.setResultId(1);
      ResultDto resultDto=new ResultDto();
      resultDto.setResultId(1);
      when(resultService.addRes(resultDto)).thenThrow(new NotFoundException("wrong email id"));
      ResponseEntity<Object> response=resultController.addRes(resultDto);
      assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
      assertNotNull(response.getBody());
    }
   @Test
    void testGetResultSuccess() {
       StudentResult studentResult=new StudentResult();
       studentResult.setResultId(1);
       ResultDto resultDto=new ResultDto();
       resultDto.setResultId(1);
       when(resultService.getRes(1)).thenReturn(Optional.of(resultDto));
       ResponseEntity<Object> response=resultController.getRes(1);
       assertEquals(HttpStatus.OK,response.getStatusCode());
       assertNotNull(response.getBody());
    }
   @Test
   void testGetResultError() {
      StudentResult studentResult=new StudentResult();
      studentResult.setResultId(1);
      ResultDto resultDto=new ResultDto();
      resultDto.setResultId(1);
      when(resultService.getRes(1)).thenThrow(new AllNotFoundException("no studentresult is present"));
      ResponseEntity<Object> response=resultController.getRes(1);
      assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
      assertNotNull(response.getBody());
   }
   @Test
   void testGetAllResultSuccess() {
       List<ResultDto> resultDto=new ArrayList<>();
       when(resultService.getAllRes()).thenReturn(resultDto);
       ResponseEntity<Object> response=resultController.getAllRes();
       assertEquals(HttpStatus.OK,response.getStatusCode());
       assertNotNull(response.getBody());
   }
   
   @Test
   void testGetAllResultError() {
       List<ResultDto> resultDto=new ArrayList<>();
       when(resultService.getAllRes()).thenThrow(new AllNotFoundException("no studentresult is present"));
       ResponseEntity<Object> response=resultController.getAllRes();
       assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
       assertNotNull(response.getBody());
   }
}
