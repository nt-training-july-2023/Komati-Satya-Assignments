package com.example.demo.serviceImp;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
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

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;

import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepo;


class CategoryServiceImpTest {

    @InjectMocks
    private CategoryServiceImp categoryService;
    @Mock
    private CategoryRepo repo;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testAddCategory() {
        CategoryDto c=new CategoryDto();
        c.setCategoryId(1);
        c.setCategoryName("java");
        c.setCategoryDescription("java basics");
        Category category=new Category();
        category.setCategoryId(1);
        category.setCategoryDescription(c.getCategoryDescription());
        category.setCategoryName(c.getCategoryName());
        when(repo.findByCategoryName(c.getCategoryName())).thenReturn(Optional.empty());
        when(repo.save(category)).thenReturn(category);
        CategoryDto categoryDto1=categoryService.saveCategory(c);
        assertEquals(c.getCategoryName(),categoryDto1.getCategoryName());
        assertEquals(c.getCategoryDescription(),categoryDto1.getCategoryDescription());
    }
    
    @Test
    void testAlreadyExistException() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryName("java");
        Category c=new Category();
        when(repo.findByCategoryName(categoryDto.getCategoryName())).thenReturn(Optional.of(c));
        assertThrows(AlreadyExistException.class, () ->{
            categoryService.saveCategory(categoryDto);
        });
    }
    
    @Test
    void testFindById() {
        Category c=new Category();
        c.setCategoryId(1);
        c.setCategoryName("java");
        c.setCategoryDescription("java basics");
        when(repo.findAll()).thenReturn(Collections.singletonList(new Category()));
        when(repo.findById(c.getCategoryId())).thenReturn(Optional.of(c));
        Optional<CategoryDto> categoryDto=categoryService.findById(c.getCategoryId());
        assertTrue(categoryDto.isPresent());
        assertEquals(c.getCategoryId(),categoryDto.get().getCategoryId());
        assertEquals(c.getCategoryName(),categoryDto.get().getCategoryName());
        assertEquals(c.getCategoryDescription(),categoryDto.get().getCategoryDescription());
      }
    @Test
    void testAllCategoryNotFound() {
        int categoryId=1;
        when(repo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, ()->{
            categoryService.findById(categoryId);
        });
    }
    @Test
    void testCategoryNotFound() {
        int categoryId=1;
        when(repo.findAll()).thenReturn(Collections.singletonList(new Category()));
        when(repo.findById(categoryId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            categoryService.findById(categoryId);
        });
    }
    @Test
    void testFindAllCategories() {
        List<Category> c=new ArrayList<Category>();
        Category category =new Category(1,"java","java basics");
        c.add(category);
        when(repo.findAll()).thenReturn(c);
        List<CategoryDto> categoryDto=categoryService.findAll();
        assertEquals(1,categoryDto.size());
        assertEquals("java",categoryDto.get(0).getCategoryName());
        assertEquals("java basics",categoryDto.get(0).getCategoryDescription());
    }
    @Test
    void testAllCategoriesNotFound() {
        when(repo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, ()->{
            categoryService.findAll();
        });
    }
    @Test
   void testUpdateCategory() {
       Category category =new Category(1,"java","java basics");
      
         CategoryDto categoryDto=new CategoryDto();
         categoryDto.setCategoryId(category.getCategoryId());
         categoryDto.setCategoryName(category.getCategoryName());
         categoryDto.setCategoryDescription(category.getCategoryDescription());
         categoryService.saveCategory(categoryDto);
         when(repo.findById(categoryDto.getCategoryId())).thenReturn(Optional.of(category));
         assertTrue(Optional.of(category).isPresent());
         
         CategoryDto cd=new CategoryDto();
         cd.setCategoryName("Sql");
         cd.setCategoryDescription("java basics");
         cd.setCategoryId(1);
         CategoryDto c=categoryService.updateCategory(cd, 1);
         assertEquals(category.getCategoryName(),cd.getCategoryName());
         assertEquals(category.getCategoryDescription(),cd.getCategoryDescription());
   }
    
    @Test
    void testCategoryNotFoundUpdate() {
        int categoryId=1;
        CategoryDto c=new CategoryDto();
        when(repo.findById(categoryId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            categoryService.updateCategory(c, categoryId);
        });
    }
    
    @Test
    public void testDeleteNoCategoriesFound() {
        when(repo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, ()->{
            categoryService.deleteCategory(1);
        });
    }
   @Test
   public void testDeleteById() {
       Category c=new Category(1,"java","java basics");
       repo.save(c);
       when(repo.findAll()).thenReturn(Collections.singletonList(new Category()));
       when(repo.findById(1)).thenReturn(Optional.of(c));
       categoryService.deleteCategory(1);
   }
   @Test
   public void testDeleteNotFound() {
       int categoryId=1;
       when(repo.findAll()).thenReturn(Collections.singletonList(new Category()));
       when(repo.findById(categoryId)).thenReturn(Optional.empty());
       assertThrows(NotFoundException.class, () ->{
           categoryService.deleteCategory(categoryId);
       });
   }
   @Test
   public void testFindByName() {
       Category c=new Category(1,"java","java basics");
       when(repo.findAll()).thenReturn(Collections.singletonList(new Category()));
       when(repo.findByCategoryName(c.getCategoryName())).thenReturn(Optional.of(c));
       Optional<CategoryDto> categoryDto=categoryService.findByName(c.getCategoryName());
       assertTrue(categoryDto.isPresent());
       assertEquals(c.getCategoryName(),categoryDto.get().getCategoryName());
       assertEquals(c.getCategoryDescription(),categoryDto.get().getCategoryDescription());
   }
   @Test
   public void testCategoryNameNotPresent() {
       String categoryName="java";
       when(repo.findAll()).thenReturn(Collections.singletonList(new Category()));
       when(repo.findByCategoryName(categoryName)).thenReturn(Optional.empty());
       assertThrows(NotFoundException.class , () ->{
           categoryService.findByName(categoryName);
       });
   }
   @Test
   public void testNoCategoryPresent() {
       String categoryName="java";
       when(repo.findAll()).thenReturn(new ArrayList<>());
       assertThrows(AllNotFoundException.class, ()->{
           categoryService.findByName(categoryName);
       });
   }
}
