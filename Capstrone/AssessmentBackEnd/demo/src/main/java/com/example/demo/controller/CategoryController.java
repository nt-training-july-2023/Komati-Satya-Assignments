package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.response.Responsee;
import com.example.demo.service.CategoryService;

import jakarta.validation.Valid;

/**
 * Category controller class.
 */
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CategoryController.class);
    /**
     * auto wiring category service class.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Category save method.
     * @param c category.
     * @return response
     */
    @PostMapping("/category")
    public final ResponseEntity<Object> saveCategory(
            @RequestBody @Valid final Category c) {
       // try {
            CategoryDto categoryDto = categoryService.saveCategory(c);
            LOGGER.info("Adding new category");
            return Responsee.generateResponce("succcessfully added data",
                    HttpStatus.CREATED, "Category_Information", categoryDto);
//        } catch (Exception e) {
//            LOGGER.error("category already present error");
//            return Responsee.generateResponce(e.getMessage(),
//                    HttpStatus.MULTI_STATUS, "Category_Information", null);
//        }
    }

    /**
     * Category find by id method.
     * @param id id of category.
     * @return response
     */
    @GetMapping("/category/cat/{id}")
    public final ResponseEntity<Object> findById(@PathVariable final int id) {
      //  try {
            Optional<CategoryDto> categoryDto = categoryService.findById(id);
            LOGGER.info("find category by category id");
            return Responsee.generateResponce("succcessfully retrieve the data",
                    HttpStatus.OK, "Category_Information", categoryDto);
//        } catch (Exception e) {
//            LOGGER.error("Exception occur");
//            return Responsee.generateResponce(e.getMessage(),
//                    HttpStatus.MULTI_STATUS, "Category_Information", null);
//        }
    }

    /**
     * Category find all method.
     * @return response.
     */
    @GetMapping("/category")
    public final ResponseEntity<Object> findAll() {
      //  try {
            List<CategoryDto> categoryDto = categoryService.findAll();
            LOGGER.info("fina all categories");
            return Responsee.generateResponce("succcessfully retrieve the data",
                    HttpStatus.OK, "Category_Information", categoryDto);
//        } catch (Exception e) {
//            LOGGER.error("exception occur");
//            return Responsee.generateResponce(e.getMessage(),
//                    HttpStatus.MULTI_STATUS, "Category_Information", null);
//        }
    }

    /**
     * Category update method.
     * @param c  category.
     * @param id category id.
     * @return response.
     */
    @PutMapping("/category/{id}")
    public final ResponseEntity<Object> updateCategory(
            @RequestBody @Valid final CategoryDto c,
            @PathVariable final int id) {
       try {
            CategoryDto categoryDto = categoryService.updateCategory(c, id);
            LOGGER.info("update category");
            return Responsee.generateResponce("succcessfully update the data",
                    HttpStatus.OK, "Category_Information", categoryDto);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Category_Information", null);
        }
    }

    /**
     * Category delete method.
     * @param id category id.
     * @return response.
     */
    @DeleteMapping("/category/{id}")
    public final ResponseEntity<Object> deleteCategory(
            @PathVariable final int id) {
       // try {
            categoryService.deleteCategory(id);
            LOGGER.info("delete category");
            return Responsee.generateResponce("succcessfully delete the data",
                    HttpStatus.OK, "User_Information", null);
//        } catch (Exception e) {
//            LOGGER.error("Exception occur");
//            return Responsee.generateResponce(e.getMessage(),
//                    HttpStatus.MULTI_STATUS, "Category_Information", null);
//        }
    }

    /**
     * Category find by name method.
     * @param s name.
     * @return response.
     */
    @GetMapping("/category/{s}")
    public final ResponseEntity<Object> findByName(
            @PathVariable final String s) {
//        try {
            Optional<CategoryDto> categoryDto = categoryService.findByName(s);
            LOGGER.info("find category by category name");
            return Responsee.generateResponce("succcessfully retrieve the data",
                    HttpStatus.OK, "User_Information", categoryDto);
//        } catch (Exception e) {
//            LOGGER.error("Exception occur");
//            return Responsee.generateResponce(e.getMessage(),
//                    HttpStatus.MULTI_STATUS, "Category_Information", null);
//        }
    }
}
