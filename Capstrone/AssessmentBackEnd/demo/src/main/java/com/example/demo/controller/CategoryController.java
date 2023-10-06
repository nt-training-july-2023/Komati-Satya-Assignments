package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
     * @param categoryDto category.
     * @return response
     */
    @PostMapping("/category")
    public final Response saveCategory(
            @RequestBody @Valid final CategoryDto categoryDto) {
            categoryService.saveCategory(categoryDto);
            LOGGER.info(Messages.SAVE_CATEGORY);
            String message = Messages.SAVE_CATEGORY;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message);
       return errorResponse;
    }
    /**
     * Category find by id method.
     * @param id id of category.
     * @return response
     */
    @GetMapping("/category/category/{id}")
    public final Response
                 findById(@PathVariable final int id) {
            Optional<CategoryDto> categoryDto = categoryService.findById(id);
            LOGGER.info(Messages.FIND_CATEGORY);
            String message = Messages.FIND_CATEGORY;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode,
                    message, categoryDto);
       return errorResponse;
    }
    /**
     * Category find all method.
     * @return response.
     */
    @GetMapping("/category")
    public final Response findAll() {
            List<CategoryDto> categoryDto = categoryService.findAll();
            LOGGER.info(Messages.FIND_ALLCATEGORY);
            String message = Messages.FIND_CATEGORY;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode,
                    message, categoryDto);
       return errorResponse;
    }

    /**
     * Category update method.
     * @param categoryDto  category.
     * @param id category id.
     * @return response.
     */
    @PutMapping("/category/{id}")
    public final Response updateCategory(
            @RequestBody @Valid final CategoryDto categoryDto,
            @PathVariable final int id) {
            categoryService.updateCategory(categoryDto, id);
            LOGGER.info(Messages.UPDATE_CATEGORY);
            String message = Messages.UPDATE_CATEGORY;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message);
       return errorResponse;
    }
    /**
     * Category delete method.
     * @param id category id.
     * @return response.
     */
    @DeleteMapping("/category/{id}")
    public final Response deleteCategory(
            @PathVariable final int id) {
            categoryService.deleteCategory(id);
            LOGGER.info(Messages.DELETE_CATEGORY);
            String message = Messages.DELETE_CATEGORY;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message);
       return errorResponse;
    }

    /**
     * Category find by name method.
     * @param s name.
     * @return response.
     */
    @GetMapping("/category/{s}")
    public final Response findByName(
            @PathVariable final String s) {
            Optional<CategoryDto> categoryDto = categoryService.findByName(s);
            LOGGER.info(Messages.FIND_CATEGORY);
            String message = Messages.FIND_CATEGORY;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode,
                    message, categoryDto);
       return errorResponse;
    }
}
