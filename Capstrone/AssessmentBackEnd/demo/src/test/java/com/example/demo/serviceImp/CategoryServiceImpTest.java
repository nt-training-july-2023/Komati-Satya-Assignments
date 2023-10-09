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
    private CategoryRepo categoryRepo;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testAddCategory() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryName("java");
        categoryDto.setCategoryDescription("java basics");
        Category category=new Category();
        category.setCategoryId(1);
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryName(categoryDto.getCategoryName());
        
        when(categoryRepo.findByCategoryName(categoryDto.getCategoryName())).thenReturn(Optional.empty());
        when(categoryRepo.save(category)).thenReturn(category);
        CategoryDto categoryDto1=categoryService.saveCategory(categoryDto);
        assertEquals(categoryDto.getCategoryName(),categoryDto1.getCategoryName());
        assertEquals(categoryDto.getCategoryDescription(),categoryDto1.getCategoryDescription());
    }
    
    @Test
    void testAlreadyExistException() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryName("java");
        Category category=new Category();
        
        when(categoryRepo.findByCategoryName(categoryDto.getCategoryName())).thenReturn(Optional.of(category));
        assertThrows(AlreadyExistException.class, () ->{   categoryService.saveCategory(categoryDto);
        });
    }
    
    @Test
    void testFindById() {
        Category category=new Category();
        category.setCategoryId(1);
        category.setCategoryName("java");
        category.setCategoryDescription("java basics");
        
        when(categoryRepo.findAll()).thenReturn(Collections.singletonList(new Category()));
        when(categoryRepo.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        Optional<CategoryDto> categoryDto=categoryService.findById(category.getCategoryId());
        assertTrue(categoryDto.isPresent());
        assertEquals(category.getCategoryId(),categoryDto.get().getCategoryId());
        assertEquals(category.getCategoryName(),categoryDto.get().getCategoryName());
        assertEquals(category.getCategoryDescription(),categoryDto.get().getCategoryDescription());
      }
    @Test
    void testAllCategoryNotFound() {
        int categoryId=1;
        when(categoryRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, ()->{  categoryService.findById(categoryId);
        });
    }
    @Test
    void testCategoryNotFound() {
        int categoryId=1;
        when(categoryRepo.findAll()).thenReturn(Collections.singletonList(new Category()));
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{  categoryService.findById(categoryId);
        });
    }
    @Test
    void testFindAllCategories() {
        List<Category> categoryList=new ArrayList<Category>();
        Category category =new Category(1,"java","java basics");
        categoryList.add(category);

        when(categoryRepo.findAll()).thenReturn(categoryList);
        List<CategoryDto> categoryDto=categoryService.findAll();
        assertEquals(1,categoryDto.size());
        assertEquals("java",categoryDto.get(0).getCategoryName());
        assertEquals("java basics",categoryDto.get(0).getCategoryDescription());
    }
    @Test
    void testAllCategoriesNotFound() {
        when(categoryRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, ()->{categoryService.findAll();
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
         when(categoryRepo.findById(categoryDto.getCategoryId())).thenReturn(Optional.of(category));
         assertTrue(Optional.of(category).isPresent());
         CategoryDto cd=new CategoryDto();
         cd.setCategoryName("Sql");
         cd.setCategoryDescription("java basics");
         cd.setCategoryId(1);
         CategoryDto c=categoryService.updateCategory(cd, 1);
         assertEquals(category.getCategoryName(),c.getCategoryName());
         assertEquals(category.getCategoryDescription(),c.getCategoryDescription());
   }
    
    @Test
    void testCategoryNotFoundUpdate() {
        int categoryId=1;
        CategoryDto c=new CategoryDto();
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{ categoryService.updateCategory(c, categoryId);
        });
    }
    
    @Test
    public void testDeleteNoCategoriesFound() {
        when(categoryRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AllNotFoundException.class, ()->{ categoryService.deleteCategory(1);
        });
    }
   @Test
   public void testDeleteById() {
       Category c=new Category(1,"java","java basics");
       categoryRepo.save(c);
       when(categoryRepo.findAll()).thenReturn(Collections.singletonList(new Category()));
       when(categoryRepo.findById(1)).thenReturn(Optional.of(c));
       categoryService.deleteCategory(1);
   }
   @Test
   public void testDeleteNotFound() {
       int categoryId=1;
       when(categoryRepo.findAll()).thenReturn(Collections.singletonList(new Category()));
       when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());
       assertThrows(NotFoundException.class, () ->{ categoryService.deleteCategory(categoryId);
       });
   }
   @Test
   public void testFindByName() {
       Category category=new Category(1,"java","java basics");
       
       when(categoryRepo.findAll()).thenReturn(Collections.singletonList(new Category()));
       when(categoryRepo.findByCategoryName(category.getCategoryName())).thenReturn(Optional.of(category));
       Optional<CategoryDto> categoryDto=categoryService.findByName(category.getCategoryName());
       assertTrue(categoryDto.isPresent());
       assertEquals(category.getCategoryName(),categoryDto.get().getCategoryName());
       assertEquals(category.getCategoryDescription(),categoryDto.get().getCategoryDescription());
   }
   @Test
   public void testCategoryNameNotPresent() {
       String categoryName="java";
       when(categoryRepo.findAll()).thenReturn(Collections.singletonList(new Category()));
       when(categoryRepo.findByCategoryName(categoryName)).thenReturn(Optional.empty());
       assertThrows(NotFoundException.class , () ->{  categoryService.findByName(categoryName);
       });
   }
   @Test
   public void testNoCategoryPresent() {
       String categoryName="java";
       when(categoryRepo.findAll()).thenReturn(new ArrayList<>());
       assertThrows(AllNotFoundException.class, ()->{
           categoryService.findByName(categoryName);
       });
   }
}
