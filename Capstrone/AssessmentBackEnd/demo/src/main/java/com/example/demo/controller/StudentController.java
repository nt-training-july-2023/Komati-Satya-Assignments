package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

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
    public final ResponseEntity<String> save(
            @RequestBody @Valid final Student s) {
            studentSevice.saveStudent(s);
            LOGGER.info("Student registration");
            return ResponseEntity.ok("User register successfully");
    }

    /**
     * get student by id.
     * @param id student id
     * @return response
     */
    @GetMapping("/student/{id}")
    public final ResponseEntity<Optional<StudentDto>> findById(
            @PathVariable final int id) {
            Optional<StudentDto> studentDto = studentSevice.findById(id);
            LOGGER.info("Student registration");
            return ResponseEntity.ok(studentDto);
    }

    /**
     * login method.
     * @param l loginDto
     * @return response
     */
    @PostMapping("/student/login")
    public final ResponseEntity<Optional<StudentDto>> login(
            @RequestBody @Valid final LoginDto l) {
            Optional<StudentDto> studentDto = studentSevice.
                    aunthenticateUser(l);
            LOGGER.info("Student login");
            return ResponseEntity.ok(studentDto);
    }

    /**
     * finding all students method.
     * @return response
     */
    @GetMapping("/student/students")
    public final ResponseEntity<List<StudentDto>> findAllStu() {
            List<StudentDto> studentDto = studentSevice.findAllStu();
            LOGGER.info("Find all students");
            return ResponseEntity.ok(studentDto);
    }

    /**
     * update student method.
     * @param s  student
     * @param id student id
     * @return response
     */
    @PutMapping("/student/{id}")
    public final ResponseEntity<String> updateStudent(
            @RequestBody @Valid final StudentDto s,
            @PathVariable final int id) {
            studentSevice.updateStudent(s, id);
            LOGGER.info("Updatation of student");
            return ResponseEntity.ok("user updated successfully");
    }
    /**
     * delete student method.
     * @param id student id
     * @return response
     */
    @DeleteMapping("/student/{id}")
    public final ResponseEntity<String> deleteStudent(
            @PathVariable final int id) {
            studentSevice.deleteStudent(id);
            LOGGER.info("Deletion of student");
            return ResponseEntity.ok("user deleted successfully");
    }
}
