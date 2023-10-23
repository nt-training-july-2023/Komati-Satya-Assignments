package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentSaveDto;
import com.example.demo.entity.ContactInfo;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.exceptions.EmailDoesNotExistException;
import com.example.demo.exceptions.PasswordMissMatchException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.repository.StudentRepo;
import com.example.demo.service.StudentService;
import com.example.demo.validationMessages.ErrorMessages;
import com.example.demo.validationMessages.Messages;

/**
 * Student service implementation.
 */
@Service
public class StudentServiceImp implements StudentService {
    /**
     * auto wiring student repository.
     */
    @Autowired
    private StudentRepo studentRepo;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(StudentServiceImp.class);
    /**
     * authenticate user method.
     * @param loginDto loginDto
     * @return student
     */
    @Override
    public final StudentDto aunthenticateUser(
            final LoginDto loginDto) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Optional<Student> optionalStudent = studentRepo
                .findByEmail(loginDto.getEmail());
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            if (bcrypt.matches(loginDto.getPassword(), student.getPassword())) {
                StudentDto studentDto = new StudentDto();
                studentDto.setFirstName(student.getFirstName());
                studentDto.setLastName(student.getLastName());
                studentDto.setUserId(student.getUserId());
                studentDto.setPhoneNumber(student.getContactInfo()
                        .getPhoneNumber());
                studentDto.setGender(student.getGender());
                studentDto.setDateOfBirth(student.getDateOfBirth());
                studentDto.setEmail(student.getContactInfo().getEmail());
                studentDto.setRole(student.getRole());
                LOGGER.info(Messages.LOGIN_STUDENT);
                return studentDto;
            } else {
                LOGGER.error(ErrorMessages.WRONG_PASSWORD);
                throw new PasswordMissMatchException(
                        ErrorMessages.WRONG_PASSWORD);
            }
        } else {
            LOGGER.error(ErrorMessages.WRONG_EMAIL);
            throw new EmailDoesNotExistException(ErrorMessages.WRONG_EMAIL);
        }
    }
    /**
     * save student method.
     * @param studentSaveDto student
     * @return Student
     */
    @Override
    public final String saveStudent(final StudentSaveDto studentSaveDto) {
        if (!studentRepo.findByEmail(studentSaveDto.getEmail()).isPresent()) {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String encrypted = bcrypt.encode(studentSaveDto.getPassword());
            studentSaveDto.setPassword(encrypted);
            Student student = new Student();
            student.setFirstName(studentSaveDto.getFirstName());
            student.setLastName(studentSaveDto.getLastName());
            student.setContactInfo(new ContactInfo(studentSaveDto.getEmail(),
                    studentSaveDto.getPhoneNumber()));
            student.setGender(studentSaveDto.getGender());
            student.setDateOfBirth(studentSaveDto.getDateOfBirth());
            student.setRole(studentSaveDto.getRole());
            student.setPassword(encrypted);
            LOGGER.info(Messages.SAVE_STUDENT);
            studentRepo.save(student);
            return "register";
        } else {
            LOGGER.error(ErrorMessages.EMAIL_EXIST);
            throw new DuplicateEmailException(ErrorMessages.EMAIL_EXIST);
        }
    }
    /**
     * find by id method.
     * @param id student id
     * @return student
     */
    @Override
    public final StudentDto findById(final int id) {
        if (studentRepo.findById(id).isPresent()) {
            StudentDto studentDto = new StudentDto();
            Optional<Student> optionalStudent = studentRepo.findById(id);
            Student student = optionalStudent.get();
            studentDto.setFirstName(student.getFirstName());
            studentDto.setLastName(student.getLastName());
            studentDto.setUserId(student.getUserId());
            studentDto.setPhoneNumber(student.getContactInfo()
                    .getPhoneNumber());
            studentDto.setGender(student.getGender());
            studentDto.setDateOfBirth(student.getDateOfBirth());
            studentDto.setEmail(student.getContactInfo().getEmail());
            studentDto.setRole(student.getRole());
            LOGGER.info(Messages.FIND_STUDENTBYID);
            return studentDto;
        } else {
            LOGGER.error(ErrorMessages.USER_NOTPRESENT);
            throw new NotFoundException(ErrorMessages.USER_NOTPRESENT);
        }
    }
    /**
     * find all students method.
     * @return student
     */
    @Override
    public final List<StudentDto> findAllStudents() {
        if (studentRepo.findAll().size() != 0) {
            List<Student> student = studentRepo.findAll();
            List<StudentDto> studentDto = convertToDto(student);
            LOGGER.info(Messages.FIND_ALLSTUDENT);
            return studentDto;
        } else {
            LOGGER.error(ErrorMessages.NO_USER);
            throw new AllNotFoundException(ErrorMessages.NO_USER);
        }
    }
    /**
     * convertToDto method.
     * @param s student list
     * @return list of students
     */
    private List<StudentDto> convertToDto(final List<Student> s) {
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : s) {
            StudentDto studentDto = new StudentDto();
            studentDto.setFirstName(student.getFirstName());
            studentDto.setLastName(student.getLastName());
            studentDto.setUserId(student.getUserId());
            studentDto.setPhoneNumber(student.getContactInfo()
                    .getPhoneNumber());
            studentDto.setGender(student.getGender());
            studentDto.setDateOfBirth(student.getDateOfBirth());
            studentDto.setEmail(student.getContactInfo().getEmail());
            studentDto.setRole(student.getRole());
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }
    /**
     * update student method.
     * @param studentDto  student
     * @param id student id
     * @result student
     */
    @Override
    public final StudentDto updateStudent(final StudentDto studentDto,
            final int id) {
        Optional<Student> user = studentRepo.findById(id);
        if (user.isPresent()) {
            Student exiStudent = user.get();
            exiStudent.setDateOfBirth(studentDto.getDateOfBirth());
            exiStudent.setGender(studentDto.getGender());
            exiStudent.setContactInfo(new ContactInfo(studentDto.getEmail(),
                    studentDto.getPhoneNumber()));
            exiStudent.setFirstName(studentDto.getFirstName());
            exiStudent.setLastName(studentDto.getLastName());
            studentRepo.save(exiStudent);
            LOGGER.info(Messages.UPDATE_STUDENT);
            return studentDto;
        } else {
            LOGGER.error(ErrorMessages.WRONG_USERID);
            throw new NotFoundException(ErrorMessages.WRONG_USERID);
        }
    }
    /**
     * delete student method.
     * @param id student id
     */
    @Override
    public final void deleteStudent(final int id) {
        if (studentRepo.findAll().size() != 0) {
            if (studentRepo.findById(id).isPresent()) {
                studentRepo.deleteById(id);
                LOGGER.info(Messages.DELETE_STUDENT);
            } else {
                LOGGER.error(ErrorMessages.WRONG_USERID);
                throw new NotFoundException(ErrorMessages.WRONG_USERID);
            }
        } else {
            LOGGER.error(ErrorMessages.NO_USER);
            throw new AllNotFoundException(ErrorMessages.NO_USER);
        }
    }
}
