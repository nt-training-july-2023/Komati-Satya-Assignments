package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.CategoryDto;


/**
 * CategoryService interface.
 */
public interface CategoryService {
    /**
     * saveCat method.
     * @param categoryDto category
     * @return category
     */
    CategoryDto saveCategory(CategoryDto categoryDto);

    /**
     * findBymethod method.
     * @param id category id
     * @return category
     */
    Optional<CategoryDto> findById(int id);

    /**
     * finaAll method.
     * @return category
     */
    List<CategoryDto> findAll();

    /**
     * update category method.
     * @param categoryDto  category
     * @param id id
     * @return category
     */
    CategoryDto updateCategory(CategoryDto categoryDto,
            int id);

    /**
     * delete category method.
     * @param id category id
     */
    void deleteCategory(int id);

    /**
     * findByName method.
     * @param s category name
     * @return category
     */
    Optional<CategoryDto> findByName(String s);
}
