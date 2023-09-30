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
import com.example.demo.response.Response;
import com.example.demo.service.CategoryService;
import com.example.demo.validationMessages.Messages;


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
    public final ResponseEntity<Response> saveCategory(
            @RequestBody @Valid final CategoryDto c) {
            categoryService.saveCategory(c);
            LOGGER.info(Messages.SAVE_CATEGORY);
            String message = Messages.SAVE_CATEGORY;
            Integer errorCode = HttpStatus.CREATED.value();
            Response errorResponse = new Response(errorCode, message);
       return new ResponseEntity<Response>(errorResponse,
              HttpStatus.CREATED);
    }
    /**
     * Category find by id method.
     * @param id id of category.
     * @return response
     */
    @GetMapping("/category/cat/{id}")
    public final ResponseEntity<Optional<CategoryDto>>
                 findById(@PathVariable final int id) {
            Optional<CategoryDto> categoryDto = categoryService.findById(id);
            LOGGER.info(Messages.FIND_CATEGORY);
            return new ResponseEntity<>(categoryDto,HttpStatus.OK);
           
    }

    /**
     * Category find all method.
     * @return response.
     */
    @GetMapping("/category")
    public final ResponseEntity<List<CategoryDto>> findAll() {
            List<CategoryDto> categoryDto = categoryService.findAll();
            LOGGER.info(Messages.FIND_ALLCATEGORY);
            return ResponseEntity.ok(categoryDto);
    }

    /**
     * Category update method.
     * @param c  category.
     * @param id category id.
     * @return response.
     */
    @PutMapping("/category/{id}")
    public final ResponseEntity<Response> updateCategory(
            @RequestBody @Valid final CategoryDto c,
            @PathVariable final int id) {
            categoryService.updateCategory(c, id);
            LOGGER.info(Messages.UPDATE_CATEGORY);
            String message = Messages.UPDATE_CATEGORY;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message);
       return new ResponseEntity<Response>(errorResponse,
              HttpStatus.OK);
    }

    /**
     * Category delete method.
     * @param id category id.
     * @return response.
     */
    @DeleteMapping("/category/{id}")
    public final ResponseEntity<Response> deleteCategory(
            @PathVariable final int id) {
            categoryService.deleteCategory(id);
            LOGGER.info(Messages.DELETE_CATEGORY);
            String message = Messages.DELETE_CATEGORY;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message);
       return new ResponseEntity<Response>(errorResponse,
              HttpStatus.OK);
    }

    /**
     * Category find by name method.
     * @param s name.
     * @return response.
     */
    @GetMapping("/category/{s}")
    public final ResponseEntity<Optional<CategoryDto>> findByName(
            @PathVariable final String s) {
            Optional<CategoryDto> categoryDto = categoryService.findByName(s);
            LOGGER.info(Messages.FIND_CATEGORY);
            return ResponseEntity.ok(categoryDto);
    }
}
