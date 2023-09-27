package com.example.demo.dto;




import com.example.demo.validationMessages.ValidationMessages;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
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
     * store the category Name.
     */
    @NotEmpty(message = ValidationMessages.CATEGORY_NOTBLANK)
    private String categoryName;
    /**
     * store category description.
     */
    @NotEmpty(message = ValidationMessages.CATEGORYDESCRIPTION_NOTBLANK)
    private String categoryDescription;
    /**
     * store the category id.
     */
    @Id
    private int categoryId;
}
