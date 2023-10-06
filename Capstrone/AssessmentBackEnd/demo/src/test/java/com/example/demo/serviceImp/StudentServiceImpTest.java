package com.example.demo.serviceImp;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentSaveDto;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.exceptions.EmailDoesNotExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.PasswordMissMatchException;
import com.example.demo.repository.StudentRepo;


class StudentServiceImpTest {
    @InjectMocks
    private StudentServiceImp studentService;
    @Mock
    private StudentRepo studentRepo;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testSaveStudent() {
        StudentSaveDto s=new StudentSaveDto();
        s.setUserName("Satya");
        s.setEmail("satya1919@nucleusteq.com");
        s.setDateOfBirth("24-02-2001");
        s.setGender("female");
        s.setPhoneNumber("8639924113");
        s.setPassword("Satya@1919");
        s.setRole("student");
        
        when(studentRepo.findByEmail(s.getEmail())).thenReturn(Optional.empty());
        Student student=new Student();
        student.setDateOfBirth(s.getDateOfBirth());
        student.setUserName(s.getUserName());
        student.setEmail(s.getEmail());
        student.setRole(s.getRole());
        student.setGender(s.getGender());
        student.setPhoneNumber(s.getPhoneNumber());
        String register=studentService.saveStudent(s);
     
        assertEquals(s.getUserName(),student.getUserName());
        assertEquals(s.getDateOfBirth(),student.getDateOfBirth());
        assertEquals(s.getEmail(),student.getEmail());
        assertEquals(s.getRole(),student.getRole());
        assertEquals(s.getGender(),student.getGender());
        assertEquals(s.getPhoneNumber(),student.getPhoneNumber());  
    }
    @Test
    public void testDuplicateEmai() {
        StudentSaveDto studentDto=new StudentSaveDto();
        studentDto.setEmail("satya1919@gmail.com");
        Student student=new Student();
        when(studentRepo.findByEmail(studentDto.getEmail())).thenReturn(Optional.of(student));
        assertThrows(DuplicateEmailException.class, () ->{
            studentService.saveStudent(studentDto);
        });
    }
    @Test
    public void testFindById() {
        int studentId=1;
        Student s=new Student();
        s.setUserName("Satya");
        s.setEmail("satya1919@nucleusteq.com");
        s.setDateOfBirth("24-02-2001");
        s.setGender("female");
        s.setPhoneNumber("8639924113");
        s.setPassword("Satya@1919");
        s.setRole("student");
        
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(s));
        Optional<StudentDto> studentDto=studentService.findById(studentId);
        assertTrue(studentDto.isPresent());
        assertEquals(s.getUserName(),studentDto.get().getUserName());
        assertEquals(s.getDateOfBirth(),studentDto.get().getDateOfBirth());
        assertEquals(s.getEmail(),studentDto.get().getEmail());
        assertEquals(s.getRole(),studentDto.get().getRole());
        assertEquals(s.getGender(),studentDto.get().getGender());
        assertEquals(s.getPhoneNumber(),studentDto.get().getPhoneNumber());  
        assertEquals(s.getUserId(),studentDto.get().getUserId());
    }
    @Test   
    public void testStudentNotFound() {
        int studentId=1;
        when(studentRepo.findById(studentId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            studentService.findById(studentId);
    });
    }
    @Test
    public void testFindAllStudents() {
        List<Student> s=new ArrayList<>();
        Student s1=new Student();
        s1.setUserName("Satya");
        Student s2=new Student();
        s2.setUserName("Siddu");
        s.add(s1);
        s.add(s2);
        
        when(studentRepo.findAll()).thenReturn(s);
        List<StudentDto> studentDto=studentService.findAllStudents();
        assertEquals(2,studentDto.size());
        assertEquals("Satya",studentDto.get(0).getUserName());
        assertEquals("Siddu",studentDto.get(1).getUserName());
    }
   
    @Test
    public void testAllStudentsNotFound() {
        when(studentRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, () ->{
            studentService.findAllStudents();
        });
    }
    
    @Test
    public void testDeleteStudent() {
        int studentId=1;
        Student s=new Student();
        s.setUserName("Satya");
        s.setEmail("satya1919@nucleusteq.com");
        s.setDateOfBirth("24-02-2001");
        s.setGender("female");
        s.setPhoneNumber("8639924113");
        s.setPassword("Satya@1919");
        s.setRole("student");
        
        when(studentRepo.findAll()).thenReturn(Collections.singletonList(new Student()));
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(new Student()));
        assertDoesNotThrow(()-> {
            studentService.deleteStudent(studentId);
        });
           assertTrue(true);
        verify(studentRepo).deleteById(studentId);
    }
   @Test
    public void testDeleteStudentNotFound() {
       Student s=new Student();
        int studentId=1; 
        s.setUserName("Satya");
        s.setEmail("satya1919@nucleusteq.com");
        s.setDateOfBirth("24-02-2001");
        s.setGender("female");
        s.setPhoneNumber("8639924113");
        s.setPassword("Satya@1919");
        s.setRole("student");
        studentRepo.save(s);
        
        when(studentRepo.findAll()).thenReturn(Collections.singletonList(new Student()));
        when(studentRepo.findById(studentId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            studentService.deleteStudent(studentId);
        });
    }
    @Test
    public void testDeleteNoStudentFound() {
        when(studentRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, ()->{
            studentService.deleteStudent(1);
        });
    }
    @Test
    public void testLogin() {
        String email="satya1919@nucleusteq.com";
        String password="Satya@1919";
       LoginDto loginDto=new LoginDto();
       loginDto.setEmail(email);
       loginDto.setPassword(password);
        String hashCode=new BCryptPasswordEncoder().encode(password);
        Student s=new Student();
        s.setEmail(email);
        s.setPassword(hashCode);
        
        when(studentRepo.findByEmail(email)).thenReturn(Optional.of(s));
        Optional<StudentDto> ss=studentService.aunthenticateUser(loginDto);
        assertTrue(ss.isPresent());
        assertTrue(new BCryptPasswordEncoder().matches(password, hashCode));
        
        }
    @Test
    public void testEmailNotMatch() {
        String email="satya1919@nucleusteq.com";
        String password="Satya@1919";
       LoginDto loginDto=new LoginDto();
       loginDto.setEmail(email);
       loginDto.setPassword(password);
        String hashCode=new BCryptPasswordEncoder().encode(password);
        Student s=new Student();
        s.setEmail(email);
        s.setPassword(hashCode);
        
        when(studentRepo.findByEmail(email)).thenReturn(Optional.empty());
        assertThrows(EmailDoesNotExistException.class, () ->{
          studentService.aunthenticateUser(loginDto);
      });
    }
        @Test
        public void testPasswordNotMatch() {
            String email="satya1919@nucleusteq.com";
            String password="Satya@1919";
           LoginDto loginDto=new LoginDto();
           loginDto.setEmail(email);
           loginDto.setPassword(password);
           String password2="Adhi@1919";
            String hashCode=new BCryptPasswordEncoder().encode(password2);
            Student s=new Student();
            s.setEmail(loginDto.getEmail());
            s.setPassword(hashCode);
            
            when(studentRepo.findByEmail(email)).thenReturn(Optional.of(s));
           assertFalse(new BCryptPasswordEncoder().matches(loginDto.getPassword(), hashCode));
            assertThrows(PasswordMissMatchException.class, () ->{
                studentService.aunthenticateUser(loginDto);
            });
        }
        
        @Test
        void testUpdateStudent() {
            StudentDto s=new StudentDto();
            int studentId=1; 
            s.setUserId(studentId);
            s.setUserName("Satya");
            s.setEmail("satya1919@nucleusteq.com");
            s.setDateOfBirth("24-02-2001");
            s.setGender("female");
            s.setPhoneNumber("8639924113");
            s.setRole("student");
            
            Student student=new Student();
            student.setDateOfBirth(s.getDateOfBirth());
            student.setEmail(s.getEmail());
            student.setGender(s.getGender());
            student.setPhoneNumber(s.getPhoneNumber());
            student.setRole(s.getRole());
            student.setUserId(s.getUserId());
            student.setUserName(s.getUserName());
            
            when(studentRepo.findById(studentId)).thenReturn(Optional.of(student));
            studentService.updateStudent(s,1);
            assertTrue(Optional.of(s).isPresent());
            StudentDto s2=new StudentDto();
            s2.setUserId(studentId);
            s2.setUserName("SatyaKomati");
            s2.setEmail("satya1919@nucleusteq.com");
            s2.setDateOfBirth("24-02-2001");
            s2.setGender("female");
            s2.setPhoneNumber("8639924113");
            s2.setRole("student");
            StudentDto studentDto= studentService.updateStudent(s2, studentId);
            assertEquals(student.getUserName(),studentDto.getUserName());
            
        }
        @Test
        void testUpdateNotFoundException() {
            int studentId=1;
            StudentDto s=new StudentDto();
            when(studentRepo.findById(studentId)).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () ->{
                studentService.updateStudent(s, studentId);
            });
        }
   
}


