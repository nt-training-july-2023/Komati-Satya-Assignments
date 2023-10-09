package com.example.demo.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.demo.entity.Category;

class ResponseTest {

    @Test
    void testGetterSetter() {
       Response response=new Response();
       Category category=new Category();
       response.setCode(200);
       response.setMessage("ok");
       response.setData(category);
       assertEquals(200,response.getCode());
       assertEquals("ok",response.getMessage());
       assertEquals(category,response.getData());
    }
    @Test
    void testTwoArgsConstructor() {
        Response response=new Response(200,"ok");
        assertEquals(200,response.getCode());
        assertEquals("ok",response.getMessage());
    }
    @Test
    void testAllArgsConstructor() {
        Category category = new Category();
        Response response=new Response(200,"ok",category);
        assertEquals(200,response.getCode());
        assertEquals("ok",response.getMessage());
        assertEquals(category,response.getData());
    }

}
