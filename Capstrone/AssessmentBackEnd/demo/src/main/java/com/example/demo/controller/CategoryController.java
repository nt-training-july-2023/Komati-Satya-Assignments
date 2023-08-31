package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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

/**
 * Category controller class.
 */
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    /**
     * auto wiring category service class.
     */
    @Autowired
    private CategoryService cs;
    /**
     * Category save method.
     * @param c category.
     * @return response
     */
    @PostMapping("/category")
    public final ResponseEntity<Object> saveCat(@RequestBody final Category c) {
        try {
            CategoryDto user = cs.saveCat(c);
            return Responsee.generateResponce("succcessfully added data",
                    HttpStatus.OK, "Category_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Category_Information", null);
        }
    }
    /**
     * Category find by id method.
     * @param id id of category.
     * @return response
     */
    @GetMapping("/cat/{id}")
    public final ResponseEntity<Object> findById(@PathVariable final int id) {
        try {
            Optional<CategoryDto> user = cs.findById(id);
            return Responsee.generateResponce("succcessfully retrieve the data",
                    HttpStatus.OK, "Category_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Category_Information", null);
        }
    }
    /**
     * Category find all method.
     * @return response.
     */
    @GetMapping("/cat")
    public final ResponseEntity<Object> findAll() {
        try {
            List<CategoryDto> user = cs.findAll();
            return Responsee.generateResponce("succcessfully retrieve the data",
                    HttpStatus.OK, "Category_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Category_Information", null);
        }
    }
    /**
     * Category update method.
     * @param c  category.
     * @param id category id.
     * @return response.
     */
    @PutMapping("/cat/{id}")
    public final ResponseEntity<Object> updateCat(@RequestBody final
            CategoryDto c,
            @PathVariable final int id) {
        try {
            CategoryDto user = cs.updateCat(c, id);
            return Responsee.generateResponce("succcessfully update the data",
                    HttpStatus.OK, "Category_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Category_Information", null);
        }
    }
    /**
     * Category delete method.
     * @param id category id.
     * @return response.
     */
    @DeleteMapping("/cat/{id}")
    public final ResponseEntity<Object> deleteCat(@PathVariable final int id) {
        try {
            cs.deleteCat(id);
            return Responsee.generateResponce("succcessfully delete the data",
                    HttpStatus.OK, "User_Information", null);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Category_Information", null);
        }
    }
    /**
     * Category find by name method.
     * @param s name.
     * @return response.
     */
    @GetMapping("/category/{s}")
    public final ResponseEntity<Object> findByName(
            @PathVariable final String s) {
        try {
            Optional<CategoryDto> c = cs.findByName(s);
            return Responsee.generateResponce("succcessfully retrieve the data",
                    HttpStatus.OK, "User_Information", c);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Category_Information", null);
        }
    }
}
