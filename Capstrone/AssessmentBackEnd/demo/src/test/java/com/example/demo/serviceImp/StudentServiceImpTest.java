package com.example.demo.serviceImp;

import static org.junit.Assert.assertThrows;
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
    private StudentRepo repo;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testSaveStudent() {
        Student s=new Student();
        s.setUserName("Satya");
        s.setEmail("satya1919@nucleusteq.com");
        s.setDateOfBirth("24-02-2001");
        s.setGender("female");
        s.setPhoneNumber("8639924113");
        s.setPassword("Satya@1919");
        s.setRole("student");
        when(repo.findByEmail(s.getEmail())).thenReturn(Optional.empty());
        StudentSaveDto studentDto=studentService.saveStudent(s);
        assertEquals(s.getUserName(),studentDto.getUserName());
        assertEquals(s.getDateOfBirth(),studentDto.getDateOfBirth());
        assertEquals(s.getEmail(),studentDto.getEmail());
        assertEquals(s.getRole(),studentDto.getRole());
        assertEquals(s.getGender(),studentDto.getGender());
        assertEquals(s.getPhoneNumber(),studentDto.getPhoneNumber());  
    }
    @Test
    public void testDuplicateEmai() {
        Student s=new Student();
        s.setEmail("satya1919@gmail.com");
        when(repo.findByEmail(s.getEmail())).thenReturn(Optional.of(s));
        assertThrows(DuplicateEmailException.class, () ->{
            studentService.saveStudent(s);
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
        when(repo.findById(studentId)).thenReturn(Optional.of(s));
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
        when(repo.findById(studentId)).thenReturn(Optional.empty());
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
        when(repo.findAll()).thenReturn(s);
        List<StudentDto> studentDto=studentService.findAllStu();
        
        assertEquals(2,studentDto.size());
        assertEquals("Satya",studentDto.get(0).getUserName());
        assertEquals("Siddu",studentDto.get(1).getUserName());
    }
   
    @Test
    public void testAllStudentsNotFound() {
        when(repo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, () ->{
            studentService.findAllStu();
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
        when(repo.findAll()).thenReturn(Collections.singletonList(new Student()));
        when(repo.findById(studentId)).thenReturn(Optional.of(new Student()));
        assertDoesNotThrow(()-> {
            studentService.deleteStudent(studentId);
        });
           assertTrue(true);
        verify(repo).deleteById(studentId);
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
        repo.save(s);
        when(repo.findAll()).thenReturn(Collections.singletonList(new Student()));
        when(repo.findById(studentId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            studentService.deleteStudent(studentId);
        });
    }
    @Test
    public void testDeleteNoStudentFound() {
        when(repo.findAll()).thenReturn(new ArrayList<>());
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
        when(repo.findByEmail(email)).thenReturn(Optional.of(s));
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
        when(repo.findByEmail(email)).thenReturn(Optional.empty());
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
            when(repo.findByEmail(email)).thenReturn(Optional.of(s));
           assertFalse(new BCryptPasswordEncoder().matches(loginDto.getPassword(), hashCode));
            assertThrows(PasswordMissMatchException.class, () ->{
                studentService.aunthenticateUser(loginDto);
            });
        }
        
        @Test
        void testUpdateStudent() {
            Student s=new Student();
            int studentId=1; 
            s.setUserId(studentId);
            s.setUserName("Satya");
            s.setEmail("satya1919@nucleusteq.com");
            s.setDateOfBirth("24-02-2001");
            s.setGender("female");
            s.setPhoneNumber("8639924113");
            s.setPassword("Satya@1919");
            s.setRole("student");
            studentService.saveStudent(s);
            StudentDto s1=new StudentDto();
            s1.setDateOfBirth(s.getDateOfBirth());
            s1.setEmail(s.getEmail());
            s1.setGender(s.getGender());
            s1.setPhoneNumber(s.getPhoneNumber());
            s1.setRole(s.getRole());
            s1.setUserId(s.getUserId());
            s1.setUserName(s.getUserName());
            when(repo.findById(studentId)).thenReturn(Optional.of(s));
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
            assertEquals(s.getUserName(),studentDto.getUserName());
            
        }
        @Test
        void testUpdateNotFoundException() {
            int studentId=1;
            StudentDto s=new StudentDto();
            when(repo.findById(studentId)).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () ->{
                studentService.updateStudent(s, studentId);
            });
        }
   
}


