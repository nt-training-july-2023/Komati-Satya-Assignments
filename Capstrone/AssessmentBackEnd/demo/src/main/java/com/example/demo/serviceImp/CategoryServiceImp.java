package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.service.CategoryService;
import com.example.demo.validationMessages.ErrorMessages;
import com.example.demo.validationMessages.Messages;

/**
 * category service implementation.
 */
@Service
public class CategoryServiceImp implements CategoryService {
    /**
     * categoryRepo object.
     */
    @Autowired
    private CategoryRepo categoryRepo;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CategoryServiceImp.class);
    /**
     * save category method.
     * @param categoryDto category
     * @return category
     */
    @Override
    public final CategoryDto saveCategory(final CategoryDto categoryDto) {
        if (!categoryRepo.findByCategoryName(categoryDto.getCategoryName())
                .isPresent()) {
            Category category = new Category();
            category.setCategoryDescription(
                    categoryDto.getCategoryDescription());
            category.setCategoryName(categoryDto.getCategoryName());
            category.setCategoryId(categoryDto.getCategoryId());
            categoryRepo.save(category);
            LOGGER.info(Messages.SAVE_CATEGORY);
            return categoryDto;
        } else {
            LOGGER.error(ErrorMessages.CATEGORY_EXIST);
            throw new AlreadyExistException(ErrorMessages.CATEGORY_EXIST);
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
                Optional<Category> category = categoryRepo.findById(id);
                Category categoryy = category.get();
                categoryDto.setCategoryDescription(categoryy.
                        getCategoryDescription());
                categoryDto.setCategoryName(categoryy.getCategoryName());
                categoryDto.setCategoryId(categoryy.getCategoryId());
                LOGGER.info(Messages.FIND_CATEGORY);
                return Optional.of(categoryDto);
            } else {
                LOGGER.error(ErrorMessages.WRONG_CATEGORYID);
                throw new NotFoundException(ErrorMessages.WRONG_CATEGORYID);
            }
        } else {
            LOGGER.error(ErrorMessages.NO_CATEGORY);
            throw new AllNotFoundException(ErrorMessages.NO_CATEGORY);
        }
    }
    /**
     * find all method.
     * @return category
     */
    @Override
    public final List<CategoryDto> findAll() {
        if (categoryRepo.findAll().size() != 0) {
            List<Category> category = categoryRepo.findAll();
            List<CategoryDto> categoryDto = convertToDto(category);
            LOGGER.info(Messages.FIND_ALLCATEGORY);
            return categoryDto;
        } else {
            LOGGER.error(ErrorMessages.NO_CATEGORY);
            throw new AllNotFoundException(ErrorMessages.NO_CATEGORY);
        }
    }
    /**
     * convertToDto method.
     * @param c category list
     * @return list category
     */
    private List<CategoryDto> convertToDto(final List<Category> c) {
        List<CategoryDto> categoryDto = new ArrayList<>();
        for (Category ca : c) {
            CategoryDto category = new CategoryDto();
            category.setCategoryDescription(ca.getCategoryDescription());
            category.setCategoryName(ca.getCategoryName());
            category.setCategoryId(ca.getCategoryId());
            categoryDto.add(category);
        }
        return categoryDto;

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
            Optional<Category> category = categoryRepo
                    .findByCategoryName(c.getCategoryName());
            if (category.isPresent()
                    && !(category.get().getCategoryId() == id)) {
                throw new AlreadyExistException(ErrorMessages.CATEGORY_EXIST);
            }
            existingCategory.setCategoryName(c.getCategoryName());
            existingCategory.setCategoryDescription(c.getCategoryDescription());
            categoryRepo.save(existingCategory);
            LOGGER.info(Messages.UPDATE_CATEGORY);
            return c;
        } else {
            LOGGER.error(ErrorMessages.WRONG_CATEGORYID);
            throw new NotFoundException(ErrorMessages.WRONG_CATEGORYID);
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
                LOGGER.info(Messages.DELETE_CATEGORY);
            } else {
                LOGGER.error(ErrorMessages.NO_CATEGORY);
                throw new NotFoundException(ErrorMessages.NO_CATEGORY);
            }
        } else {
            LOGGER.error(ErrorMessages.NO_CATEGORY);
            throw new AllNotFoundException(ErrorMessages.NO_CATEGORY);
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
                Optional<Category> category = categoryRepo.
                        findByCategoryName(s);
                categoryDto.setCategoryDescription(
                        category.get().getCategoryDescription());
                categoryDto.setCategoryName(category.get().getCategoryName());
                LOGGER.info(Messages.FIND_CATEGORY);
                return Optional.of(categoryDto);
            } else {
                LOGGER.error(ErrorMessages.CATEGORY_NOTPRESENT);
                throw new NotFoundException(ErrorMessages.CATEGORY_NOTPRESENT);
            }
        } else {
            LOGGER.error(ErrorMessages.NO_CATEGORY);
            throw new AllNotFoundException(ErrorMessages.NO_CATEGORY);
        }
    }

}
