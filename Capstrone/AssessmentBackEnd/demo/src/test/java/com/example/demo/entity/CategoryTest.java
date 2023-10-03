package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CategoryTest {
    
    
   @Test
   void testGetterSetter() {
       Category c=new Category();
       c.setCategoryDescription("Java basics");
       assertEquals("Java basics",c.getCategoryDescription());
       c.setCategoryId(10);
       assertEquals(10,c.getCategoryId());
       c.setCategoryName("Java");
       assertEquals("Java",c.getCategoryName());
        Quiz q1= null;
        Quiz q2=null;
        List<Quiz> quizzes=new ArrayList<>();
        quizzes.add(q1);
        quizzes.add(q2);
        c.setQuiz(quizzes);
        assertEquals(2,c.getQuiz().size());
      
   }
   @Test
   void testNoArgsConstructor() {
       Category c=new Category();
       assertEquals(0,c.getCategoryId());
       assertNull(c.getCategoryDescription());
       assertNull(c.getCategoryName());
       //assertNull(c.getQu());
   }
   @Test
   void testAllArgsConstructor() {
       int categoryId=1;
       String categoryName="java";
       String categoryDescription="java basics";
       Category c=new Category(categoryId,categoryName,categoryDescription);
       assertEquals(categoryId,c.getCategoryId());
       assertEquals(categoryName,c.getCategoryName());
       assertEquals(categoryDescription,c.getCategoryDescription());
   }
   

}
