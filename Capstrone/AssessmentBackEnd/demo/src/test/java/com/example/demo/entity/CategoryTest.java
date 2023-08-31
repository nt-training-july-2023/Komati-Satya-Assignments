package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoryTest {
    
   @Test
   void testGetterSetter() {
       Category c=new Category();
       c.setCategoryDescription("Java basics");
   }

}
