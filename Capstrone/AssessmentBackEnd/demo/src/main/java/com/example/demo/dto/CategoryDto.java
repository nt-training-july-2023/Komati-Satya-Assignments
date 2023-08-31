package com.example.demo.dto;




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
    private String categoryName;
    /**
     * store category description.
     */
    private String categoryDescription;
    /**
     * store the category id.
     */
    private int categoryId;
}
