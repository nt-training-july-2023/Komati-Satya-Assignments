package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentSaveDto;


/**
 * student service method.
 */
public interface StudentService {
    /**
     * save student method.
     * @param s student
     * @return student
     */
    String saveStudent(StudentSaveDto s);

    /**
     * find by id method.
     * @param id student id
     * @return student
     */
    Optional<StudentDto> findById(int id);

    /**
     * authenticateUser method.
     * @param l loginDto
     * @return student
     */
    Optional<StudentDto> aunthenticateUser(LoginDto l);

    /**
     * find all student method.
     * @return student
     */
    List<StudentDto> findAllStudents();

    /**
     * update student method.
     * @param s  student
     * @param id student id
     * @return student
     */
    StudentDto updateStudent(StudentDto s, int id);

    /**
     * delete student method.
     * @param id student id
     */
    void deleteStudent(int id);
}
