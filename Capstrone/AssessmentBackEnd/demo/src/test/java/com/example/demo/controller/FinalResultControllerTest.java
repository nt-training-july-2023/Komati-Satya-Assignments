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
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ResultDto;
import com.example.demo.entity.FinalRes;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.serviceImp.FinalResultServiceImp;


class FinalResultControllerTest {
    @Mock
    private FinalResultServiceImp resultService;
    @InjectMocks
    private FinalResultController resultController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetByUserIdSuccess() {
        FinalRes finalResult=new FinalRes();
        finalResult.setUserId(1);
        List<ResultDto> resultDto=new ArrayList<>();
        when(resultService.getById(1)).thenReturn(resultDto);
        ResponseEntity<Object> response=resultController.getById(1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testGetByUserIdError() {
        FinalRes finalResult=new FinalRes();
        finalResult.setUserId(1);
        when(resultService.getById(1)).thenThrow(new AllNotFoundException("No user is there"));
        ResponseEntity<Object> response=resultController.getById(1);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testAllResultSuccess() {
        List<ResultDto> resultDto=new ArrayList<>();
        when(resultService.findAll()).thenReturn(resultDto);
        ResponseEntity<Object> response=resultController.findAllResult();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    
    @Test
    void testAllResultError() {
        FinalRes finalResult=new FinalRes();
        when(resultService.findAll()).thenThrow(new AllNotFoundException("No results are there"));
        ResponseEntity<Object> response=resultController.findAllResult();
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    

}
