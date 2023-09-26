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
        when(categoryService.saveCategory(category)).thenReturn(categoryDto);
        ResponseEntity<String> response=categoryController.saveCategory(category);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("added", response.getBody());
    }

    @Test
    void testFindByIdSuccess() {
        Category category=new Category();
        category.setCategoryName("java");
        category.setCategoryId(1);
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryName("java");
        when(categoryService.findById(1)).thenReturn(Optional.of(categoryDto));
        ResponseEntity<Optional<CategoryDto>> response=categoryController.findById(1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
       
    }

    @Test
    void testFindAllCategoriesSuccess() {
        List<CategoryDto> categoryDto=new ArrayList<>();
        when(categoryService.findAll()).thenReturn(categoryDto);
        ResponseEntity<List<CategoryDto>> response=categoryController.findAll();
        assertEquals(HttpStatus.OK,response.getStatusCode());   
    }
    @Test
    void testUpdateCategorySuccess() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryName("java");
        categoryDto.setCategoryName("React");
        int i=1;
        when(categoryService.updateCategory(categoryDto, i)).thenReturn(categoryDto);
        ResponseEntity<String> response=categoryController.updateCategory(categoryDto, i);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Updated successfully", response.getBody());
        
          
    }
    @Test
    void testDeleteCategorySuccess() {
        Category c=new Category();
        c.setCategoryId(1);
        ResponseEntity<String> response=categoryController.deleteCategory(1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());   
        assertEquals("Deleted successfully", response.getBody());
    }
   
     @Test
    void testFindByCategoryNameSuccess() {
         CategoryDto categoryDto=new CategoryDto();
         categoryDto.setCategoryName("java");
         when(categoryService.findByName("java")).thenReturn(Optional.of(categoryDto));
         ResponseEntity<Optional<CategoryDto>> response=categoryController.findByName("java");
         assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}

