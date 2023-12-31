package com.example.demo.serviceImp;


import static org.junit.jupiter.api.Assertions.*;
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
import com.example.demo.dto.Gender;
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
        StudentSaveDto studentSaveDto=new StudentSaveDto();
        studentSaveDto.setUserName("Satya");
        studentSaveDto.setEmail("satya1919@nucleusteq.com");
        studentSaveDto.setDateOfBirth("24-02-2001");
        studentSaveDto.setGender(Gender.female);
        studentSaveDto.setPhoneNumber("8639924113");
        studentSaveDto.setPassword("Satya@1919");
        studentSaveDto.setRole("student");
        
        when(studentRepo.findByEmail(studentSaveDto.getEmail())).thenReturn(Optional.empty());
        Student student=new Student();
        student.setDateOfBirth(studentSaveDto.getDateOfBirth());
        student.setUserName(studentSaveDto.getUserName());
        student.setEmail(studentSaveDto.getEmail());
        student.setRole(studentSaveDto.getRole());
        student.setGender(studentSaveDto.getGender());
        student.setPhoneNumber(studentSaveDto.getPhoneNumber());
        String register=studentService.saveStudent(studentSaveDto);
        assertEquals(register,"register");
    }
    @Test
    public void testDuplicateEmai() {
        StudentSaveDto studentDto=new StudentSaveDto();
        studentDto.setEmail("satya1919@nucleusteq.com");
        Student student=new Student();
        when(studentRepo.findByEmail(studentDto.getEmail())).thenReturn(Optional.of(student));
        assertThrows(DuplicateEmailException.class, () ->{   studentService.saveStudent(studentDto);
        });
    }
    @Test
    public void testFindById() {
        StudentDto studentDto=new StudentDto();
        int studentId=1; 
        studentDto.setUserId(studentId);
        studentDto.setUserName("Satya");
        studentDto.setEmail("satya1919@nucleusteq.com");
        studentDto.setDateOfBirth("24-02-2001");
        studentDto.setGender(Gender.female);
        studentDto.setPhoneNumber("8639924113");
        studentDto.setRole("student");
        
        Student student=new Student();
        student.setUserId(studentId);;
        student.setUserName("Satya");
        student.setEmail("satya1919@nucleusteq.com");
        student.setDateOfBirth("24-02-2001");
        student.setGender(Gender.female);
        student.setPhoneNumber("8639924113");
        student.setPassword("Satya@1919");
        student.setRole("student");
        
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(student));
        StudentDto studentDto2=studentService.findById(studentId);
        assertEquals(studentDto2,studentDto);
        
    }
    @Test   
    public void testStudentNotFound() {
        int studentId=1;
        when(studentRepo.findById(studentId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{   studentService.findById(studentId);
    });
    }
    @Test
    public void testFindAllStudents() {
        List<Student> studentList=new ArrayList<>();
        StudentDto studentDto=new StudentDto();
        int studentId=1; 
        studentDto.setUserId(studentId);
        studentDto.setUserName("Satya");
        studentDto.setEmail("satya1919@nucleusteq.com");
        studentDto.setDateOfBirth("24-02-2001");
        studentDto.setGender(Gender.female);
        studentDto.setPhoneNumber("8639924113");
        studentDto.setRole("student");
        
        Student student=new Student();
        student.setUserId(studentId);
        student.setUserName("Satya");
        student.setEmail("satya1919@nucleusteq.com");
        student.setDateOfBirth("24-02-2001");
        student.setGender(Gender.female);
        student.setPhoneNumber("8639924113");
        student.setPassword("Satya@1919");
        student.setRole("student");
        studentList.add(student);

        when(studentRepo.findAll()).thenReturn(studentList);
        List<StudentDto> studentDtoList=studentService.findAllStudents();
        assertEquals(studentDtoList,Collections.singletonList(studentDto));
    }
   
    @Test
    public void testAllStudentsNotFound() {
        when(studentRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, () ->{ studentService.findAllStudents();
        });
    }
    
    @Test
    public void testDeleteStudent() {
        int studentId=1;
        Student student=new Student();
        student.setUserName("Satya");
        student.setEmail("satya1919@nucleusteq.com");
        student.setDateOfBirth("24-02-2001");
        student.setGender(Gender.female);
        student.setPhoneNumber("8639924113");
        student.setPassword("Satya@1919");
        student.setRole("student");
        
        when(studentRepo.findAll()).thenReturn(Collections.singletonList(new Student()));
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(new Student()));
        assertDoesNotThrow(()-> {  studentService.deleteStudent(studentId);
        });
    }
   @Test
    public void testDeleteStudentNotFound() {
       Student student=new Student();
        int studentId=1; 
        student.setUserName("Satya");
        student.setEmail("satya1919@nucleusteq.com");
        student.setDateOfBirth("24-02-2001");
        student.setGender(Gender.female);
        student.setPhoneNumber("8639924113");
        student.setPassword("Satya@1919");
        student.setRole("student");
        studentRepo.save(student);
        
        when(studentRepo.findAll()).thenReturn(Collections.singletonList(new Student()));
        when(studentRepo.findById(studentId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{  studentService.deleteStudent(studentId);
        });
    }
    @Test
    public void testDeleteNoStudentFound() {
        when(studentRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, ()->{  studentService.deleteStudent(1);
        });
    }
    @Test
    public void testLogin() {
        StudentDto studentDto=new StudentDto();
        int studentId=1; 
        studentDto.setUserId(studentId);
        studentDto.setUserName("Satya");
        studentDto.setEmail("satya1919@nucleusteq.com");
        studentDto.setDateOfBirth("24-02-2001");
        studentDto.setGender(Gender.female);
        studentDto.setPhoneNumber("8639924113");
        studentDto.setRole("student");
        
        Student student=new Student();
        student.setUserId(studentId);
        student.setUserName("Satya");
        student.setEmail("satya1919@nucleusteq.com");
        student.setDateOfBirth("24-02-2001");
        student.setGender(Gender.female);
        student.setPhoneNumber("8639924113");
        student.setPassword(new BCryptPasswordEncoder().encode("Satya@1919"));
        student.setRole("student");
        
        String email="satya1919@nucleusteq.com";
        String password="Satya@1919";
        LoginDto loginDto=new LoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword(password);
        String hashCode=new BCryptPasswordEncoder().encode(password);
        
        when(studentRepo.findByEmail(email)).thenReturn(Optional.of(student));
        StudentDto studentDto2=studentService.aunthenticateUser(loginDto);
        assertTrue(new BCryptPasswordEncoder().matches(password, hashCode));
        assertEquals(studentDto2,studentDto);
        
        }
    @Test
    public void testEmailNotMatch() {
        String email="satya1919@nucleusteq.com";
        String password="Satya@1919";
       LoginDto loginDto=new LoginDto();
       loginDto.setEmail(email);
       loginDto.setPassword(password);
        String hashCode=new BCryptPasswordEncoder().encode(password);
        Student student=new Student();
        student.setEmail(email);
        student.setPassword(hashCode);
        
        when(studentRepo.findByEmail(email)).thenReturn(Optional.empty());
        assertThrows(EmailDoesNotExistException.class, () ->{   studentService.aunthenticateUser(loginDto);
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
            Student student=new Student();
            student.setEmail(loginDto.getEmail());
            student.setPassword(hashCode);
            
            when(studentRepo.findByEmail(email)).thenReturn(Optional.of(student));
           assertFalse(new BCryptPasswordEncoder().matches(loginDto.getPassword(), hashCode));
            assertThrows(PasswordMissMatchException.class, () ->{  studentService.aunthenticateUser(loginDto);
            });
        }
        
        @Test
        void testUpdateStudent() {
            StudentDto studentDto=new StudentDto();
            int studentId=1; 
            studentDto.setUserId(studentId);
            studentDto.setUserName("Satya");
            studentDto.setEmail("satya1919@nucleusteq.com");
            studentDto.setDateOfBirth("24-02-2001");
            studentDto.setGender(Gender.female);
            studentDto.setPhoneNumber("8639924113");
            studentDto.setRole("student");
            
            Student student=new Student();
            student.setDateOfBirth(studentDto.getDateOfBirth());
            student.setEmail(studentDto.getEmail());
            student.setGender(studentDto.getGender());
            student.setPhoneNumber(studentDto.getPhoneNumber());
            student.setRole(studentDto.getRole());
            student.setUserId(studentDto.getUserId());
            student.setUserName(studentDto.getUserName());
            
            when(studentRepo.findById(studentId)).thenReturn(Optional.of(student));
            studentService.updateStudent(studentDto,1);
            assertTrue(Optional.of(studentDto).isPresent());
            StudentDto studentDto2=new StudentDto();
            studentDto2.setUserId(studentId);
            studentDto2.setUserName("SatyaKomati");
            studentDto2.setEmail("satya1919@nucleusteq.com");
            studentDto2.setDateOfBirth("24-02-2001");
            studentDto2.setGender(Gender.female);
            studentDto2.setPhoneNumber("8639924113");
            studentDto2.setRole("student");
            StudentDto studentDto3= studentService.updateStudent(studentDto2, studentId);
            assertEquals(studentDto3,studentDto2);
            
        }
        @Test
        void testUpdateNotFoundException() {
            int studentId=1;
            StudentDto studentDto=new StudentDto();
            when(studentRepo.findById(studentId)).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () ->{    studentService.updateStudent(studentDto, studentId);
            });
        }
   
}


