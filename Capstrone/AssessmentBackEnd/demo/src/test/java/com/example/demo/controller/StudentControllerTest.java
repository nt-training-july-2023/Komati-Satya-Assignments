package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
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

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentSaveDto;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.exceptions.EmailDoesNotExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.response.Response;
import com.example.demo.serviceImp.StudentServiceImp;

class StudentControllerTest {

    @Mock
    private StudentServiceImp studentService;
    @InjectMocks
    private StudentController studentController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testAddStudentSuccess() {
       StudentSaveDto student=new StudentSaveDto();
       student.setUserName("satya");
       StudentSaveDto saveDto=new StudentSaveDto();
       saveDto.setUserName("satya");
       
       when(studentService.saveStudent(student)).thenReturn("registered");
       Response errorResponse = new Response(HttpStatus.OK.value(), "student added successfully");
       Response response=studentController.save(student);
       assertEquals(HttpStatus.OK.value(),response.getCode());
       assertEquals("User register successfully",response.getMessage());
    }
    @Test
    void testUserFindByIdSuccess() {
        Student student=new Student();
        student.setUserName("satya");
        student.setUserId(12);
        StudentDto saveDto=new StudentDto();
        saveDto.setUserName("satya");
        
        when(studentService.findById(12)).thenReturn(Optional.of(saveDto));
        Response response=studentController.findById(12);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("successfully retrieve the student by id",response.getMessage());
        assertEquals(Optional.of(saveDto),response.getData());
    }
    @Test
    void testLoginSuccess() {
        LoginDto loginDto=new LoginDto();
        loginDto.setEmail("satya19@nucleusteq.com");
        loginDto.setPassword("satya@1919");
        StudentDto saveDto=new StudentDto();
        saveDto.setUserName("satya");
        
        when(studentService.aunthenticateUser(loginDto)).thenReturn(Optional.of(saveDto));
        Response response=studentController.login(loginDto);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("student logged in",response.getMessage());
    }
    @Test
    void testFindAllStudentsSuccess() {
        List<StudentDto> studentDto=new ArrayList<>();
        when(studentService.findAllStudents()).thenReturn(studentDto);
        Response response=studentController.findAllStudents();
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("successfully retrieve the all studnet details",response.getMessage());
        assertEquals(studentDto,response.getData());
    }
    @Test
    void testUpdateStudentSuccess() {
        StudentDto saveDto=new StudentDto();
        saveDto.setUserName("satya");
        saveDto.setUserName("Satya");
        int i=1;
        
        when(studentService.updateStudent(saveDto, 1)).thenReturn(saveDto);
        Response errorResponse = new Response(HttpStatus.OK.value(), "student updated successfully");
        Response response=studentController.updateStudent(saveDto, i);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("student updated successfully",errorResponse.getMessage());
    }
    @Test
    void testDeleteSuccess() {
        Student student= new Student();
        student.setUserId(1);
        Response errorResponse = new Response(HttpStatus.CREATED.value(), "student deleted successfully");
        Response response=studentController.deleteStudent(1);
        assertEquals(HttpStatus.OK.value(),response.getCode());
        assertEquals("student deleted successfully",errorResponse.getMessage());
    }
}
