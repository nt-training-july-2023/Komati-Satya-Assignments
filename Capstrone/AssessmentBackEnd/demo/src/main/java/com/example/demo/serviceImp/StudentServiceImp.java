package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentSaveDto;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.exceptions.EmailDoesNotExistException;
import com.example.demo.exceptions.PasswordMissMatchException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.repository.StudentRepo;
import com.example.demo.service.StudentService;

/**
 * Student service implementation.
 */
@Service
public class StudentServiceImp implements StudentService {
    /**
     * auto wiring student repository.
     */
    @Autowired
    private StudentRepo st;
    /**
     * Constructor.
     * @param repo repo
     */
    public StudentServiceImp(final StudentRepo repo) {
        this.st = repo;
    }

    /**
     * authenticate user method.
     * @param stu loginDto
     * @return student
     */
    @Override
    public final Optional<StudentDto> aunthenticateUser(final LoginDto stu) {

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        Optional<Student> student = st.findByEmail(stu.getEmail());
        if (student.isPresent()) {
            Student st1 = student.get();
            if (bcrypt.matches(stu.getPassword(), st1.getPassword())) {
                StudentDto studentDto = new StudentDto();
                studentDto.setUserName(st1.getUserName());
                studentDto.setUserId(st1.getUserId());
                studentDto.setPhoneNumber(st1.getPhoneNumber());
                studentDto.setGender(st1.getGender());
                studentDto.setDateOfBirth(st1.getDateOfBirth());
                studentDto.setEmail(st1.getEmail());
                studentDto.setRole(st1.getRole());
                return Optional.of(studentDto);
            } else {

                throw new PasswordMissMatchException("password must be same");
            }
        } else {
            throw new EmailDoesNotExistException("Email not exist");
        }

    }

    /**
     * save student method.
     * @param stu student
     * @return Student
     */
    @Override
    public final StudentSaveDto saveStudent(final Student stu) {
        if (!st.findByEmail(stu.getEmail()).isPresent()) {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String encrypted = bcrypt.encode(stu.getPassword());
            stu.setPassword(encrypted);
            st.save(stu);
            StudentSaveDto studentDto = new StudentSaveDto();
            studentDto.setUserName(stu.getUserName());
            studentDto.setPhoneNumber(stu.getPhoneNumber());
            studentDto.setGender(stu.getGender());
            studentDto.setDateOfBirth(stu.getDateOfBirth());
            studentDto.setEmail(stu.getEmail());
            studentDto.setRole(stu.getRole());

            return studentDto;
        } else {
            throw new DuplicateEmailException("Email already exist");
        }

    }

    /**
     * find by id method.
     * @param id student id
     * @return student
     */
    @Override
    public final Optional<StudentDto> findById(final int id) {
        if (st.findById(id).isPresent()) {
            StudentDto studentDto = new StudentDto();
            Optional<Student> s = st.findById(id);
            Student stu = s.get();
            studentDto.setUserName(stu.getUserName());
            studentDto.setUserId(stu.getUserId());
            studentDto.setPhoneNumber(stu.getPhoneNumber());
            studentDto.setGender(stu.getGender());
            studentDto.setDateOfBirth(stu.getDateOfBirth());
            studentDto.setEmail(stu.getEmail());
            studentDto.setRole(stu.getRole());
            return Optional.of(studentDto);
        } else {
            throw new NotFoundException("User not found");
        }
    }

    /**
     * find all students method.
     * @return student
     */
    @Override
    public final List<StudentDto> findAllStu() {
        if (st.findAll().size() != 0) {
            List<Student> s = st.findAll();
            List<StudentDto> sd = convertToDto(s);
            return sd;
        } else {
            throw new AllNotFoundException("No user is present");
        }

    }
    /**
     * convertToDto method.
     * @param s student list
     * @return list of students
     */
    private List<StudentDto> convertToDto(final List<Student> s) {
        List<StudentDto> sd = new ArrayList<>();
        for (Student stu : s) {
            StudentDto studentDto = new StudentDto();
            studentDto.setUserName(stu.getUserName());
            studentDto.setUserId(stu.getUserId());
            studentDto.setPhoneNumber(stu.getPhoneNumber());
            studentDto.setGender(stu.getGender());
            studentDto.setDateOfBirth(stu.getDateOfBirth());
            studentDto.setEmail(stu.getEmail());
            studentDto.setRole(stu.getRole());

            sd.add(studentDto);
        }
        return sd;
    }

    /**
     * update student method.
     * @param s  student
     * @param id student id
     * @result student
     */
    @Override
    public final StudentDto updateStudent(final StudentDto s, final int id) {
        Optional<Student> user = st.findById(id);
        if (user.isPresent()) {
            Student exiStudent = user.get();
            exiStudent.setDateOfBirth(s.getDateOfBirth());
            exiStudent.setGender(s.getGender());
            exiStudent.setPhoneNumber(s.getPhoneNumber());
            exiStudent.setUserName(s.getUserName());
            st.save(exiStudent);
            return s;

        } else {
            throw new NotFoundException("User not found,give a correct id");
        }
    }
    /**
     * delete student method.
     * @param id student id
     */
    @Override
    public final void deleteStudent(final int id) {
        if (st.findAll().size() != 0) {
            if (st.findById(id).isPresent()) {
                st.deleteById(id);
            } else {
                throw new NotFoundException("User not found,give a correct id");
            }
        } else {
            throw new AllNotFoundException("No user is present");
        }
    }
}
