package com.example.demo.dto;

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
    private int maxMarks;
    /**
     * stores the date and time.
     */
    private String dateAndTime;
    /**
     * stores the result.
     */
    private String result;
    /**
     * stores the res.
     */
    private int attemptedQuestions;
    /**
     * stores userName.
     */
    private String userName;
    /**
     * stores email.
     */
    private String email;
    /**
     * stores quiz Name.
     */
    private String quizName;
    /**
     * stores category name.
     */
    private String categoryName;
    /**
     * stores obtainMarks.
     */
    private int obtainMarks;
    /**
     * stores categortId.
     */
    private int categoryId;
    /**
     * result id.
     */
    private int resultId;
    /**
     * stores user id.
     */
    private int userId;
}
