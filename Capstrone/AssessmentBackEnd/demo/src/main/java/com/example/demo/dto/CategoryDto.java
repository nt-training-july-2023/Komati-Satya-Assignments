package com.example.demo.dto;

import java.util.Objects;

import com.example.demo.validationMessages.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * category data transfer object.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    /**
     * store the category id.
     */
    private int categoryId;
    /**
     * store the category Name.
     */
    @NotBlank(message = ValidationMessages.CATEGORY_NOTBLANK)
    private String categoryName;
    /**
     * store category description.
     */
    @NotBlank(message = ValidationMessages.CATEGORYDESCRIPTION_NOTBLANK)
    private String categoryDescription;
    /**
     * hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(categoryDescription, categoryId, categoryName);
    }
    /**
     * equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CategoryDto other = (CategoryDto) obj;
        return Objects.equals(categoryDescription, other.categoryDescription)
                && categoryId == other.categoryId
                && Objects.equals(categoryName, other.categoryName);
    }
}
