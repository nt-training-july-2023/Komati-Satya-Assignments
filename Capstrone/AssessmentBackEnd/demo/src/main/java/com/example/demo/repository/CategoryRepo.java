package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Category;

/**
 * repository for category.
 */
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    /**
     * retrieves category.
     * @param name category name
     * @return category
     */
    @Query("select c from Category c where c.categoryName=:name")
    Optional<Category> findByCategoryName(String name);
}
