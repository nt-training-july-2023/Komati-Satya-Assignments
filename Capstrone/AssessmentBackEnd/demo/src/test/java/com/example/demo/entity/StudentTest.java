package com.example.demo.entity;

    import static org.junit.jupiter.api.Assertions.*;

    import org.junit.jupiter.api.Test;

import com.example.demo.dto.Gender;

    class StudentTest {

        @Test
       void testSetterGetter() {
           Student stu = new Student();
           
           ContactInfo contactInfo = new ContactInfo();

           contactInfo.setEmail("satya1919@nucleusteq.com");
           assertEquals("satya1919@nucleusteq.com", contactInfo.getEmail());

           contactInfo.setPhoneNumber("8639924113");
           assertEquals("8639924113", contactInfo.getPhoneNumber());
           
           stu.setFirstName("Satya");
           assertEquals("Satya",stu.getFirstName());
           stu.setUserId(10);
           assertEquals(10,stu.getUserId());
           stu.setDateOfBirth("24-02-2001");
           assertEquals("24-02-2001",stu.getDateOfBirth());
           stu.setGender(Gender.female);
           assertEquals(Gender.female,stu.getGender());
           stu.setPassword("Satya@1919");
           assertEquals("Satya@1919",stu.getPassword());
           stu.setRole("student");
           assertEquals("student",stu.getRole());
           }
       
        @Test
        void testDefaultConstructor() {
            Student stu=new Student();
            assertEquals(0,stu.getUserId());
            assertEquals(null,stu.getDateOfBirth());
            assertEquals(null,stu.getPassword());
            assertEquals(null,stu.getGender());
            assertEquals(null,stu.getFirstName());
            assertEquals(null,stu.getLastName());
            assertEquals(null,stu.getRole());
        }
        @Test
        void testAllArgConstructor() {
            int id=1;
            String email="satya1919@gmail.com";
            String firstName="Satya";
            String lastName="komati";
            String role="student";
            String phoneNumber="8639924113";
            String dateOfBirth="24-02-2001";
           

            ContactInfo contactInfo = new ContactInfo(email, phoneNumber);

            assertEquals(email, contactInfo.getEmail());
            assertEquals(phoneNumber, contactInfo.getPhoneNumber());
            Student stu=new Student(id,firstName,lastName,contactInfo,Gender.female,role,dateOfBirth);
            assertEquals(id,stu.getUserId());
            assertEquals(dateOfBirth,stu.getDateOfBirth());
            assertEquals(Gender.female,stu.getGender());
            assertEquals(firstName,stu.getFirstName());
            assertEquals(lastName,stu.getLastName());
            assertEquals(role,stu.getRole());
            
        }
    }


