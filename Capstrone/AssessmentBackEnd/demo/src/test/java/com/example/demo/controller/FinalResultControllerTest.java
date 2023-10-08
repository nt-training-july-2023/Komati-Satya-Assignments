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
import com.example.demo.entity.FinalRes;
import com.example.demo.response.Response;
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
        Response response=resultController.getById(1);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals(resultDto,response.getData());
        assertEquals("successfully retrieve the student result",response.getMessage());
    }
    @Test
    void testAllResultSuccess() {
        List<ResultDto> resultDto=new ArrayList<>();
        when(resultService.findAll()).thenReturn(resultDto);
        Response response=resultController.findAllResult();
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals(resultDto,response.getData());
        assertEquals("successfully retrieve the"
                + "student results",response.getMessage());
    }
}
