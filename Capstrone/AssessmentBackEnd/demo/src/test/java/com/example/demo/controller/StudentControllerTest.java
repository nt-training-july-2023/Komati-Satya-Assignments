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
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentSaveDto;
import com.example.demo.entity.Student;
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
       
       Response expectedResponse =new Response();
       expectedResponse.setCode(HttpStatus.OK.value());
       expectedResponse.setMessage("User register successfully");
       
       when(studentService.saveStudent(student)).thenReturn("registered");
       Response response=studentController.save(student);
       assertEquals(response,expectedResponse);
    }
    @Test
    void testUserFindByIdSuccess() {
        Student student=new Student();
        student.setUserName("satya");
        student.setUserId(12);
        StudentDto saveDto=new StudentDto();
        saveDto.setUserName("satya");

        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("successfully retrieve the student by id");
        expectedResponse.setData(saveDto);
        
        when(studentService.findById(12)).thenReturn(saveDto);
        Response response=studentController.findById(12);
        assertEquals(response,expectedResponse);
    }
    @Test
    void testLoginSuccess() {
        LoginDto loginDto=new LoginDto();
        loginDto.setEmail("satya19@nucleusteq.com");
        loginDto.setPassword("satya@1919");
        StudentDto saveDto=new StudentDto();
        saveDto.setUserName("satya");
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("student logged in");
        expectedResponse.setData(saveDto);
        
        when(studentService.aunthenticateUser(loginDto)).thenReturn(saveDto);
        Response response=studentController.login(loginDto);
        assertEquals(response,expectedResponse);
    }
    @Test
    void testFindAllStudentsSuccess() {
        List<StudentDto> studentDto=new ArrayList<>();
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("successfully retrieve the all studnet details");
        expectedResponse.setData(studentDto);
        
        when(studentService.findAllStudents()).thenReturn(studentDto);
        Response response=studentController.findAllStudents();
        assertEquals(response,expectedResponse);
        }
    @Test
    void testUpdateStudentSuccess() {
        StudentDto saveDto=new StudentDto();
        saveDto.setUserName("satya");
        saveDto.setUserName("Satya");
        int i=1;

        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("user updated successfully");
        
        when(studentService.updateStudent(saveDto, 1)).thenReturn(saveDto);
        Response response=studentController.updateStudent(saveDto, i);
        assertEquals(response,expectedResponse);
    }
    @Test
    void testDeleteSuccess() {
        Student student= new Student();
        student.setUserId(1);
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("User deleted successfully");
        
        Response response=studentController.deleteStudent(1);
        assertEquals(response,expectedResponse);
    }
}
