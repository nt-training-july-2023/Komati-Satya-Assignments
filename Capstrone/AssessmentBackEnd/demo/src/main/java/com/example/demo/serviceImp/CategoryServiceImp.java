package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private CategoryRepo a;

    /**
     * constructor.
     * @param st categoryRepo
     */
    public CategoryServiceImp(final CategoryRepo st) {
        this.a = st;
    }

    /**
     * save category method.
     * @param c category
     * @return category
     */
    @Override
    public final CategoryDto saveCat(final Category c) {
        if (!a.findByCategoryName(c.getCategoryName()).isPresent()) {
            CategoryDto cd = new CategoryDto();
            cd.setCategoryDescription(c.getCategoryDescription());
            cd.setCategoryName(c.getCategoryName());
            cd.setCategoryId(c.getCategoryId() );
            a.save(c);
            return cd;
        } else {
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
        if (a.findAll().size() != 0) {
            if (a.findById(id).isPresent()) {
                CategoryDto cad = new CategoryDto();
                Optional<Category> c = a.findById(id);
                Category ca = c.get();
                cad.setCategoryDescription(ca.getCategoryDescription());
                cad.setCategoryName(ca.getCategoryName());
                cad.setCategoryId(ca.getCategoryId());
                return Optional.of(cad);
            } else {
                throw new NotFoundException("wrong category id");
            }
        } else {
            throw new AllNotFoundException("no category is there");
        }
    }

    /**
     * find all method.
     * @return category
     */
    @Override
    public final List<CategoryDto> findAll() {
        if (a.findAll().size() != 0) {
            List<Category> c = a.findAll();
            List<CategoryDto> cd = convertToDto(c);
            return cd;
        } else {
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
    public final CategoryDto updateCat(final CategoryDto c, final int id) {
        Optional<Category> existingCategoryOptional = a.findById(id);
        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();
            existingCategory.setCategoryName(c.getCategoryName());
            existingCategory.setCategoryDescription(c.getCategoryDescription());
            a.save(existingCategory);

            return c;
        } else {
            throw new NotFoundException("wrong category id");
        }
    }

    /**
     * delete category method.
     * @param id category id
     */
    @Override
    public final void deleteCat(final int id) {
        if (a.findAll().size() != 0) {
            if (a.findById(id).isPresent()) {
                a.deleteById(id);
            } else {
                throw new NotFoundException("wrong category id");
            }
        } else {
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
        if (a.findAll().size() != 0) {
            if (a.findByCategoryName(s).isPresent()) {
                CategoryDto cad = new CategoryDto();
                Optional<Category> c = a.findByCategoryName(s);
                cad.setCategoryDescription(c.get().getCategoryDescription());
                cad.setCategoryName(c.get().getCategoryName());
                return Optional.of(cad);
            } else {
                throw new NotFoundException("Category not present");
            }
        } else {
            throw new AllNotFoundException("No categories are there");
        }
    }

}
