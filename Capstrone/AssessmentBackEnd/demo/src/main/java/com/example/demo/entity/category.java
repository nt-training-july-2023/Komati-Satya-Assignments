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
    @Column(nullable = false , unique=true)
    private String categoryName;
    /**
     * store category description.
     */
    @Column(nullable = false)
    private String categoryDescription;
    /**
     * one to many relationship with quiz.
     */
    @OneToMany(targetEntity = Quiz.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "c_fk", referencedColumnName = "categoryId")
    @JsonIgnoreProperties("Category")
    private List<Quiz> qu;
    /**
     * get category.
     * @return category id
     */
    public final int getCategoryId() {
        return categoryId;
    }
    /**
     * set category.
     * @param categoryid id
     */
    public final void setCategoryId(final int categoryid) {
        this.categoryId = categoryid;
    }
    /**
     * get category Name.
     * @return category name
     */
    public final String getCategoryName() {
        return categoryName;
    }
    /**
     * set category name.
     * @param categoryname category name
     */
    public final void setCategoryName(final String categoryname) {
        this.categoryName = categoryname;
    }
    /**
     * get category Name.
     * @return name name
     */
    public final String getCategoryDescription() {
        return categoryDescription;
    }
    /**
     * set category description.
     * @param categorydescription category
     */
    public final void setCategoryDescription(final String categorydescription) {
        this.categoryDescription = categorydescription;
    }
    /**
     * get category Name.
     * @return quiz quiz
     */
    public final List<Quiz> getQu() {
        return new ArrayList<>(qu);
    }
    /**
     * set method.
     * @param quu quiz
     */
    public final void setQu(final List<Quiz> quu) {
        this.qu = new ArrayList<>(quu);
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
