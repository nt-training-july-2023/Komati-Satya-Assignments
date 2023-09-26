package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;
/**
 * category entity class.
 */

@NoArgsConstructor
@Entity
public class Category {
    /**
     * store the category id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;
    /**
     * store category name.
     */
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "category name is required")
    private String categoryName;
    /**
     * store category description.
     */
    @Column(nullable = false)
    @NotEmpty(message = "category description is required")
    private String categoryDescription;
    /**
     * one to many relationship with quiz.
     */
    @OneToMany(targetEntity = Quiz.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "c_fk", referencedColumnName = "categoryId")
    @JsonIgnoreProperties("Category")
    private List<Quiz> quiz;
    /**
     * get category.
     * @return category id
     */
    public int getCategoryId() {
        return categoryId;
    }
    /**
     * set category.
     * @param categoryid id
     */
    public void setCategoryId(final int categoryid) {
        this.categoryId = categoryid;
    }
    /**
     * get category Name.
     * @return category name
     */
    public String getCategoryName() {
        return categoryName;
    }
    /**
     * set category name.
     * @param categoryname category name
     */
    public void setCategoryName(final String categoryname) {
        this.categoryName = categoryname;
    }
    /**
     * get category Name.
     * @return name name
     */
    public String getCategoryDescription() {
        return categoryDescription;
    }
    /**
     * set category description.
     * @param categorydescription category
     */
    public void setCategoryDescription(final String categorydescription) {
        this.categoryDescription = categorydescription;
    }
    /**
     * get category Name.
     * @return quiz quiz
     */
    public List<Quiz> getQu() {
        return new ArrayList<>(quiz);
    }
    /**
     * set method.
     * @param quizz quiz
     */
    public void setQu(final List<Quiz> quizz) {
        this.quiz = new ArrayList<>(quizz);
    }
    /**
     * Constructor.
     * @param categoryIdd id
     * @param categoryNamee name
     * @param categoryDescriptionn description
     */
    public Category(final int categoryIdd, final String categoryNamee,
           final String categoryDescriptionn) {
        super();
        this.categoryId = categoryIdd;
        this.categoryName = categoryNamee;
        this.categoryDescription = categoryDescriptionn;
    }
}
