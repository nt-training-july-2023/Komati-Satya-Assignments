package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.entity.Student;
import com.example.demo.response.Responsee;
import com.example.demo.service.StudentService;

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
    private StudentService stu;

    /**
     * student save method.
     * @param s student
     * @return response.
     */
    @PostMapping("/student")
    public final ResponseEntity<Object> save(@RequestBody final Student s) {
        try {
            StudentSaveDto user = stu.saveStudent(s);
            return Responsee.generateResponce("successfully added data",
                    HttpStatus.OK, "User_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "User_Information", null);
        }
    }

    /**
     * get student by id.
     * @param id student id
     * @return response
     */
    @GetMapping("/student/{id}")
    public final ResponseEntity<Object> findById(@PathVariable final int id) {
        try {
            Optional<StudentDto> user = stu.findById(id);
            return Responsee.generateResponce("successfully get the data",
                    HttpStatus.OK, "User_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "User_Information", null);
        }
    }

    /**
     * login method.
     * @param l loginDto
     * @return response
     */
    @PostMapping("/student/login")
    public final ResponseEntity<Object> login(@RequestBody final LoginDto l) {
        try {
            Optional<StudentDto> user = stu.aunthenticateUser(l);
            return Responsee.generateResponce("succcessfully retrieve the data",
                    HttpStatus.OK, "User_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "User_Information", null);
        }
    }

    /**
     * finding all students method.
     * @return response
     */
    @GetMapping("/student/students")
    public final ResponseEntity<Object> findAllStu() {
        try {
            List<StudentDto> user = stu.findAllStu();
            return Responsee.generateResponce(
                    "successfully retrieve all the data", HttpStatus.OK,
                    "User_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "User_Information", null);
        }
    }

    /**
     * update student method.
     * @param s  student
     * @param id student id
     * @return response
     */
    @PutMapping("/student/{id}")
    public final ResponseEntity<Object> updateStudent(
            @RequestBody final StudentDto s, @PathVariable final int id) {
        try {
            StudentDto user = stu.updateStudent(s, id);
            return Responsee.generateResponce("successfully update the data",
                    HttpStatus.OK, "User_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "User_Information", null);
        }
    }

    /**
     * delete student method.
     * @param id student id
     * @return response
     */
    @DeleteMapping("/student/{id}")
    public final ResponseEntity<Object> deleteStudent(
            @PathVariable final int id) {
        try {
            stu.deleteStudent(id);
            return Responsee.generateResponce("successfully delete the data",
                    HttpStatus.OK, "User_Information", null);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "User_Information", null);
        }
    }
}
