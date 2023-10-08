package com.example.demo.entity;

    import static org.junit.jupiter.api.Assertions.*;

    import org.junit.jupiter.api.Test;

import com.example.demo.dto.Gender;

    class StudentTest {

        @Test
       void testSetterGetter() {
           Student stu = new Student();
           
           stu.setUserName("Satya");
           assertEquals("Satya",stu.getUserName());
           stu.setUserId(10);
           assertEquals(10,stu.getUserId());
           stu.setEmail("satya1919@nucleusteq.com");
           assertEquals("satya1919@nucleusteq.com",stu.getEmail());
           stu.setDateOfBirth("24-02-2001");
           assertEquals("24-02-2001",stu.getDateOfBirth());
           stu.setGender(Gender.female);
           assertEquals(Gender.female,stu.getGender());
           stu.setPassword("Satya@1919");
           assertEquals("Satya@1919",stu.getPassword());
           stu.setPhoneNumber("8639924113");
           assertEquals("8639924113",stu.getPhoneNumber());
           stu.setRole("student");
           assertEquals("student",stu.getRole());
           }
       
        @Test
        void testDefaultConstructor() {
            Student stu=new Student();
            assertEquals(0,stu.getUserId());
            assertEquals(null,stu.getEmail());
            assertEquals(null,stu.getDateOfBirth());
            assertEquals(null,stu.getPassword());
            assertEquals(null,stu.getGender());
            assertEquals(null,stu.getPhoneNumber());
            assertEquals(null,stu.getUserName());
            assertEquals(null,stu.getRole());
        }
        @Test
        void testAllArgConstructor() {
            int id=1;
            String email="satya1919@gmail.com";
            String userName="Satya";
            String role="student";
            String phoneNumber="8639924113";
            String dateOfBirth="24-02-2001";
            Student stu=new Student(id,userName,email,Gender.female,phoneNumber,role,dateOfBirth);
            assertEquals(id,stu.getUserId());
            assertEquals(email,stu.getEmail());
            assertEquals(dateOfBirth,stu.getDateOfBirth());
            assertEquals(Gender.female,stu.getGender());
            assertEquals(phoneNumber,stu.getPhoneNumber());
            assertEquals(userName,stu.getUserName());
            assertEquals(role,stu.getRole());
            
        }
    }


