package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.CategoryDto;

import com.example.demo.entity.Category;

import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.serviceImp.CategoryServiceImp;


class CategoryControllerTest {
    @Mock
    private CategoryServiceImp categoryService;
    @InjectMocks
    private CategoryController categoryController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddCategorySuccess() {
        Category category=new Category();
        category.setCategoryName("java");
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryName("java");
        when(categoryService.saveCat(category)).thenReturn(categoryDto);
        ResponseEntity<Object> response=categoryController.saveCat(category);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testAddCategoryError() {
        Category category=new Category();
        category.setCategoryName("java");
        when(categoryService.saveCat(category)).thenThrow(new AlreadyExistException("Category already present"));
        ResponseEntity<Object> response=categoryController.saveCat(category);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testFindByIdSuccess() {
        Category category=new Category();
        category.setCategoryName("java");
        category.setCategoryId(1);
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryName("java");
        when(categoryService.findById(1)).thenReturn(Optional.of(categoryDto));
        ResponseEntity<Object> response=categoryController.findById(1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());  
    }
    @Test
    void testFindByIdError() {
        Category category=new Category();
        category.setCategoryName("java");
        category.setCategoryId(1);
        when(categoryService.findById(1)).thenThrow(new NotFoundException("wrong category id"));
        ResponseEntity<Object> response=categoryController.findById(1);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody()); 
    }
    @Test
    void testFindAllCategoriesSuccess() {
        List<CategoryDto> categoryDto=new ArrayList<>();
        when(categoryService.findAll()).thenReturn(categoryDto);
        ResponseEntity<Object> response=categoryController.findAll();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());    
    }
    @Test
    void testFindAllCategoriesError() {
        List<CategoryDto> categoryDto=new ArrayList<>();
        when(categoryService.findAll()).thenThrow(new AllNotFoundException("No categories are there"));
        ResponseEntity<Object> response=categoryController.findAll();
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody()); 
    }
    @Test
    void testUpdateCategorySuccess() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryName("java");
        categoryDto.setCategoryName("React");
        int i=1;
        when(categoryService.updateCat(categoryDto, i)).thenReturn(categoryDto);
        ResponseEntity<Object> response=categoryController.updateCat(categoryDto, i);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());  
    }
    @Test
    void testUpdateCategoryError() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryName("java");
        categoryDto.setCategoryName("React");
        int i=1;
        when(categoryService.updateCat(categoryDto, i)).thenThrow(new NotFoundException("wrong category id"));
        ResponseEntity<Object> response=categoryController.updateCat(categoryDto, i);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody()); 
    }
    @Test
    void testDeleteCategorySuccess() {
        Category c=new Category();
        c.setCategoryId(1);
        ResponseEntity<Object> response=categoryController.deleteCat(1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());     
    }
    @Test
    void testDeleteCategoryError() {
        Category c=new Category();
        c.setCategoryId(1);
        doThrow(new NotFoundException("wrong category id")).when(categoryService).deleteCat(1);
        ResponseEntity<Object> response=categoryController.deleteCat(1);
        assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
        assertNotNull(response.getBody());  
    }
     @Test
    void testFindByCategoryNameSuccess() {
         CategoryDto categoryDto=new CategoryDto();
         categoryDto.setCategoryName("java");
         when(categoryService.findByName("java")).thenReturn(Optional.of(categoryDto));
         ResponseEntity<Object> response=categoryController.findByName("java");
         assertEquals(HttpStatus.OK,response.getStatusCode());
         assertNotNull(response.getBody()); 
    }
     @Test
     void testFindByCategoryNameError() {
         CategoryDto categoryDto=new CategoryDto();
         categoryDto.setCategoryName("java");
         when(categoryService.findByName("java")).thenThrow(new NotFoundException("Category not present"));
         ResponseEntity<Object> response=categoryController.findByName("java");
         assertEquals(HttpStatus.MULTI_STATUS,response.getStatusCode());
         assertNotNull(response.getBody()); 
     }
}
