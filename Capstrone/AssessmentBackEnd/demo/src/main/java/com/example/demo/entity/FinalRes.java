package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Final result entity class.
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
public class FinalRes {
    /**
     * stores the final student result id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int finalId;
    /**
     * stores the student id.
     */
    @Column(nullable = false)
    private int userId;
    /**
     * stores the final student user name.
     */
    @Column(nullable = false)
    private String userName;
    /**
     * stores the quiz topic.
     */
    @Column(nullable = false)
    private String quizTopic;
    /**
     * stores the category name.
     */
    @Column(nullable = false)
    private String categoryName;
    /**
     * stores the final student result.
     */
    @Column(nullable = false)
    private String result;
    /**
     * stores the date and time.
     */
    @Column(nullable = false)
    private String dateAndTime;
    /**
     * stores the student marks.
     */
    @Column(nullable = false)
    private int marks;
    /**
     * stores the student result id.
     */
    @Column(nullable = false)
    private int resultId;
    /**
     * stores the maximum marks of quiz.
     */
    @Column(nullable = false)
    private int maxMarks;
    /**
     * stores the student email.
     */
    @Column(nullable = false)
    private String email;
    /**
     * category id.
     */
    private int categoryId;
    /**
     * stores attempted questions.
     */
    private int attemptedQuestions;
    /**
     * total questions.
     */
    private int totalNoOfQuestions;
}
