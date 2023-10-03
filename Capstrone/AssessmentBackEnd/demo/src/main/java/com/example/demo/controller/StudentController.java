package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentSaveDto;
import com.example.demo.response.Response;
import com.example.demo.service.StudentService;
import com.example.demo.validationMessages.Messages;

import jakarta.validation.Valid;

/**
 * Student controller class.
 */
@RestController
@CrossOrigin(origins = "*")
public class StudentController {
    /**
     * auto wiring student service class.
     */
    @Autowired
    private StudentService studentSevice;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(StudentController.class);
    /**
     * student save method.
     * @param s student
     * @return response.
     */
    @PostMapping("/student")
    public final Response save(
            @RequestBody @Valid final StudentSaveDto s) {
            studentSevice.saveStudent(s);
            LOGGER.info(Messages.SAVE_STUDENT);
            String message = Messages.SAVE_STUDENT;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message);
       return errorResponse;
    }

    /**
     * get student by id.
     * @param id student id
     * @return response
     */
    @GetMapping("/student/{id}")
    public final Response findById(
            @PathVariable final int id) {
            Optional<StudentDto> studentDto = studentSevice.findById(id);
            LOGGER.info(Messages.FIND_STUDENTBYID);
            String message = Messages.FIND_STUDENTBYID;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode,
                    message, studentDto);
       return errorResponse;
    }

    /**
     * login method.
     * @param l loginDto
     * @return response
     */
    @PostMapping("/student/login")
    public final Response login(
            @RequestBody @Valid final LoginDto l) {
            Optional<StudentDto> studentDto = studentSevice.
                    aunthenticateUser(l);
            LOGGER.info(Messages.LOGIN_STUDENT);
            String message = Messages.LOGIN_STUDENT;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode,
                    message, studentDto);
       return errorResponse;
    }

    /**
     * finding all students method.
     * @return response
     */
    @GetMapping("/student/students")
    public final Response findAllStu() {
            List<StudentDto> studentDto = studentSevice.findAllStudents();
            LOGGER.info(Messages.FIND_ALLSTUDENT);
            String message = Messages.FIND_ALLSTUDENT;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode,
                    message, studentDto);
       return errorResponse;
    }

    /**
     * update student method.
     * @param s  student
     * @param id student id
     * @return response
     */
    @PutMapping("/student/{id}")
    public final Response updateStudent(
            @RequestBody @Valid final StudentDto s,
            @PathVariable final int id) {
            studentSevice.updateStudent(s, id);
            LOGGER.info(Messages.UPDATE_STUDENT);
            String message = Messages.UPDATE_STUDENT;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message);
       return errorResponse;
    }
    /**
     * delete student method.
     * @param id student id
     * @return response
     */
    @DeleteMapping("/student/{id}")
    public final Response deleteStudent(
            @PathVariable final int id) {
            studentSevice.deleteStudent(id);
            LOGGER.info(Messages.DELETE_STUDENT);
            String message = Messages.DELETE_STUDENT;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message);
       return errorResponse;
    }
}
