package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.response.Response;
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
    void testAddCategorySucscess() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryName("java");
        CategoryDto category=new CategoryDto();
        category.setCategoryName("java");
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("category added successfully");
        when(categoryService.saveCategory(category)).thenReturn(categoryDto);
        Response response=categoryController.saveCategory(category);
        assertEquals(response,expectedResponse);
    }

    @Test
    void testFindByIdSuccess() {
        Category category=new Category();
        category.setCategoryName("java");
        category.setCategoryId(1);
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryName("java");
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("successfully retrieve the category data");
        expectedResponse.setData(categoryDto);
        when(categoryService.findById(1)).thenReturn(categoryDto);
        Response response=categoryController.findById(1);
        assertEquals(response,expectedResponse);
       
    }

    @Test
    void testFindAllCategoriesSuccess() {
        List<CategoryDto> categoryDto=new ArrayList<>();
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("successfully retrieve the category data");
        expectedResponse.setData(categoryDto);
        
        when(categoryService.findAll()).thenReturn(categoryDto);
        Response response=categoryController.findAll();
        assertEquals(response,expectedResponse);
    }
    @Test
    void testUpdateCategorySuccess() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryName("java");
        categoryDto.setCategoryName("React");
        int i=1;
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("updated category");
        when(categoryService.updateCategory(categoryDto, i)).thenReturn(categoryDto);
        Response response=categoryController.updateCategory(categoryDto, i);
        assertEquals(response,expectedResponse);
    }
    @Test
    void testDeleteCategorySuccess() {
        Category c=new Category();
        c.setCategoryId(1);
        
        Response expectedResponse =new Response();
        expectedResponse.setCode(HttpStatus.OK.value());
        expectedResponse.setMessage("deleted category");
        Response response=categoryController.deleteCategory(1);
        assertEquals(response,expectedResponse);
    }
   
     @Test
    void testFindByCategoryNameSuccess() {
         CategoryDto categoryDto=new CategoryDto();
         categoryDto.setCategoryName("java");
         
         Response expectedResponse =new Response();
         expectedResponse.setCode(HttpStatus.OK.value());
         expectedResponse.setMessage("successfully retrieve the category data");
         expectedResponse.setData(categoryDto);
         when(categoryService.findByName("java")).thenReturn(categoryDto);
         Response response=categoryController.findByName("java");
         assertEquals(response,expectedResponse);
    }
}

