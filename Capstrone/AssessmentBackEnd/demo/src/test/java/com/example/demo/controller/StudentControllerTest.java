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
       Student student=new Student();
       student.setUserName("satya");
       StudentSaveDto saveDto=new StudentSaveDto();
       saveDto.setUserName("satya");
       when(studentService.saveStudent(student)).thenReturn(saveDto);
       ResponseEntity<Object> response=studentController.save(student);
       assertEquals(HttpStatus.OK,response.getStatusCode());
       assertNotNull(response.getBody());
    }
    @Test
    void testAddStudentError() {
        Student student=new Student();
        student.setUserName("satya");
        when(studentService.saveStudent(student)).thenThrow(new DuplicateEmailException("Email already exist"));
        ResponseEntity<Object> response=studentController.save(student);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testUserFindByIdSuccess() {
        Student student=new Student();
        student.setUserName("satya");
        student.setUserId(12);
        StudentDto saveDto=new StudentDto();
        saveDto.setUserName("satya");
        when(studentService.findById(12)).thenReturn(Optional.of(saveDto));
        ResponseEntity<Object> response=studentController.findById(12);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());      
    }
    @Test
    void testUserFindByIdError() {
        StudentDto saveDto=new StudentDto();
        saveDto.setUserName("satya");
        when(studentService.findById(12)).thenThrow(new NotFoundException("User not found"));
        ResponseEntity<Object> response=studentController.findById(12);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody()); 
    }
    @Test
    void testLoginSuccess() {
        LoginDto loginDto=new LoginDto();
        loginDto.setEmail("satya19@nucleusteq.com");
        loginDto.setPassword("satya@1919");
        StudentDto saveDto=new StudentDto();
        saveDto.setUserName("satya");
        when(studentService.aunthenticateUser(loginDto)).thenReturn(Optional.of(saveDto));
        ResponseEntity<Object> response=studentController.login(loginDto);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());      
    }
    @Test
    void testLoginError() {
        LoginDto loginDto=new LoginDto();
        loginDto.setEmail("satya19@nucleusteq.com");
        loginDto.setPassword("satya@1919");
        StudentDto saveDto=new StudentDto();
        saveDto.setUserName("satya");
        when(studentService.aunthenticateUser(loginDto)).thenThrow(new EmailDoesNotExistException("Email not exist"));
        ResponseEntity<Object> response=studentController.login(loginDto);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testFindAllStudentsSuccess() {
        List<StudentDto> studentDto=new ArrayList<>();
        when(studentService.findAllStu()).thenReturn(studentDto);
        ResponseEntity<Object> response=studentController.findAllStu();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());  
    }
    @Test
    void testFindAllStudentsError() {
        List<StudentDto> studentDto=new ArrayList<>();
        when(studentService.findAllStu()).thenThrow(new AllNotFoundException("No user is present"));
        ResponseEntity<Object> response=studentController.findAllStu();
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());  
    }
    
    @Test
    void testUpdateStudentSuccess() {
        StudentDto saveDto=new StudentDto();
        saveDto.setUserName("satya");
        saveDto.setUserName("Satya");
        int i=1;
        when(studentService.updateStudent(saveDto, 1)).thenReturn(saveDto);
        ResponseEntity<Object> response=studentController.updateStudent(saveDto, i);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testUpdateStudentError() {
        StudentDto saveDto=new StudentDto();
        saveDto.setUserName("satya");
        saveDto.setUserName("Satya");
        int i=1;
        when(studentService.updateStudent(saveDto, 1)).thenThrow(new NotFoundException("User not found,give a correct id"));
        ResponseEntity<Object> response=studentController.updateStudent(saveDto, i);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testDeleteSuccess() {
        Student student= new Student();
        student.setUserId(1);
        ResponseEntity<Object> response=studentController.deleteStudent(1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());    
    }
    @Test
    void testDeleteError() {
        Student student= new Student();
        student.setUserId(1);
       
        doThrow(new AllNotFoundException("No user is present")).when(studentService).deleteStudent(1);
        ResponseEntity<Object> response=studentController.deleteStudent(1);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());  
    }

}
