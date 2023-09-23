package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.service.CategoryService;

/**
 * category service implementation.
 */
@Service
public class CategoryServiceImp implements CategoryService {
    /**
     * categoryRepo object.
     */
    private CategoryRepo categoryRepo;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CategoryServiceImp.class);
    /**
     * constructor.
     * @param st categoryRepo
     */
    public CategoryServiceImp(final CategoryRepo st) {
        this.categoryRepo = st;
    }
    /**
     * save category method.
     * @param category category
     * @return category
     */
    @Override
    public final CategoryDto saveCategory(final Category category) {
        if (!categoryRepo.findByCategoryName(category.getCategoryName())
                .isPresent()) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto
                    .setCategoryDescription(category.getCategoryDescription());
            categoryDto.setCategoryName(category.getCategoryName());
            categoryDto.setCategoryId(category.getCategoryId());
            categoryRepo.save(category);
            LOGGER.info("save category");
            return categoryDto;
        } else {
            LOGGER.error("Category already present");
            throw new AlreadyExistException("Category already present");
        }
    }
    /**
     * find by id method.
     * @param id category id.
     * @return category
     */
    @Override
    public final Optional<CategoryDto> findById(final int id) {
        if (categoryRepo.findAll().size() != 0) {
            if (categoryRepo.findById(id).isPresent()) {
                CategoryDto categoryDto = new CategoryDto();
                Optional<Category> c = categoryRepo.findById(id);
                Category ca = c.get();
                categoryDto.setCategoryDescription(ca.getCategoryDescription());
                categoryDto.setCategoryName(ca.getCategoryName());
                categoryDto.setCategoryId(ca.getCategoryId());
                LOGGER.info("find category by category id");
                return Optional.of(categoryDto);
            } else {
                LOGGER.error("wrong category id");
                throw new NotFoundException("wrong category id");
            }
        } else {
            LOGGER.error("no category is there");
            throw new AllNotFoundException("no category is there");
        }
    }
    /**
     * find all method.
     * @return category
     */
    @Override
    public final List<CategoryDto> findAll() {
        if (categoryRepo.findAll().size() != 0) {
            List<Category> c = categoryRepo.findAll();
            List<CategoryDto> cd = convertToDto(c);
            LOGGER.info("find all categories");
            return cd;
        } else {
            LOGGER.error("No categories are there");
            throw new AllNotFoundException("No categories are there");
        }
    }
    /**
     * convertToDto method.
     * @param c category list
     * @return list category
     */
    private List<CategoryDto> convertToDto(final List<Category> c) {
        List<CategoryDto> cd = new ArrayList<>();
        for (Category ca : c) {
            CategoryDto cad = new CategoryDto();
            cad.setCategoryDescription(ca.getCategoryDescription());
            cad.setCategoryName(ca.getCategoryName());
            cad.setCategoryId(ca.getCategoryId());
            cd.add(cad);
        }
        return cd;

    }
    /**
     * update category method.
     * @param c  category
     * @param id category id
     * @return category
     */
    @Override
    public final CategoryDto updateCategory(final CategoryDto c, final int id) {
        Optional<Category> existingCategoryOptional = categoryRepo.findById(id);
        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();
            existingCategory.setCategoryName(c.getCategoryName());
            existingCategory.setCategoryDescription(c.getCategoryDescription());
            categoryRepo.save(existingCategory);
            LOGGER.info("update category");
            return c;
        } else {
            LOGGER.error("wrong category id");
            throw new NotFoundException("wrong category id");
        }
    }
    /**
     * delete category method.
     * @param id category id
     */
    @Override
    public final void deleteCategory(final int id) {
        if (categoryRepo.findAll().size() != 0) {
            if (categoryRepo.findById(id).isPresent()) {
                categoryRepo.deleteById(id);
                LOGGER.info("delete category");
            } else {
                LOGGER.error("wrong category id");
                throw new NotFoundException("wrong category id");
            }
        } else {
            LOGGER.error("No categories are there");
            throw new AllNotFoundException("No categories are there");
        }
    }
    /**
     * find by name method.
     * @param s category name
     * @return category
     */
    @Override
    public final Optional<CategoryDto> findByName(final String s) {
        if (categoryRepo.findAll().size() != 0) {
            if (categoryRepo.findByCategoryName(s).isPresent()) {
                CategoryDto categoryDto = new CategoryDto();
                Optional<Category> c = categoryRepo.findByCategoryName(s);
                categoryDto.setCategoryDescription(
                        c.get().getCategoryDescription());
                categoryDto.setCategoryName(c.get().getCategoryName());
                LOGGER.info("find category by category name");
                return Optional.of(categoryDto);
            } else {
                LOGGER.error("Category not present");
                throw new NotFoundException("Category not present");
            }
        } else {
            LOGGER.error("No categories are there");
            throw new AllNotFoundException("No categories are there");
        }
    }

}
