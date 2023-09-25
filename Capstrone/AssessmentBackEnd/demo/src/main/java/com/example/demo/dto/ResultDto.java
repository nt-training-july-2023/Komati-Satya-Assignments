package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Result data transfer object.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultDto {
    /**
     * stores the marks.
     */
    @NotEmpty(message = "marks is required")
    private int maxMarks;
    /**
     * stores the date and time.
     */
    @NotEmpty(message = "date and time is required")
    private String dateAndTime;
    /**
     * stores the result.
     */
    @NotEmpty(message = "result is required")
    private String result;
    /**
     * stores the res.
     */
    @NotEmpty(message = "attempted questions are required")
    private int attemptedQuestions;
    /**
     * stores userName.
     */
    @NotEmpty(message = "user name is required")
    private String userName;
    /**
     * stores email.
     */
    @NotEmpty(message = "email is required")
    private String email;
    /**
     * stores quiz Name.
     */
    @NotEmpty(message = "quiz name is required")
    private String quizName;
    /**
     * stores category name.
     */
    @NotEmpty(message = "category name is required")
    private String categoryName;
    /**
     * stores obtainMarks.
     */
    @NotEmpty(message = "obtain marks is required")
    private int obtainMarks;
    /**
     * stores categortId.
     */
    @NotEmpty(message = "category id is required")
    private int categoryId;
    /**
     * result id.
     */
    @NotEmpty(message = "result id is required")
    private int resultId;
    /**
     * stores user id.
     */
    @NotEmpty(message = "user id is required")
    private int userId;
    /**
     * total questions.
     */
    @NotEmpty(message = "total questions are required")
    private int totalQuestions;
}
