package com.example.demo.dto;

import com.example.demo.validationMessages.ValidationMessages;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = ValidationMessages.TOTALMARKS_NOTNULL)
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
    @NotNull(message = ValidationMessages.ATTEMPTEDQUESTIONS_NOTNULL)
    private int attemptedQuestions;
    /**
     * stores userName.
     */
    @NotEmpty(message = ValidationMessages.NAME_NOTBLANK)
    private String userName;
    /**
     * stores email.
     */
    @NotEmpty(message = ValidationMessages.EMAIL_NOTBLANK)
    private String email;
    /**
     * stores quiz Name.
     */
    @NotEmpty(message = ValidationMessages.QUIZNAME_NOTBLANK)
    private String quizName;
    /**
     * stores category name.
     */
    @NotEmpty(message = ValidationMessages.CATEGORY_NOTBLANK)
    private String categoryName;
    /**
     * stores obtainMarks.
     */
    @NotNull(message = ValidationMessages.MARKSOBTAINED_NOTNULL)
    private int obtainMarks;
    /**
     * stores categortId.
     */
   @NotNull(message = ValidationMessages.CATEGORY_NOTNULL)
    private int categoryId;
    /**
     * result id.
     */
    @NotNull(message = ValidationMessages.ID_NOTBLANK)
    private int resultId;
    /**
     * stores user id.
     */
    @NotNull(message = ValidationMessages.ID_NOTBLANK)
    private int userId;
    /**
     * total questions.
     */
    @NotNull(message = ValidationMessages.TOTALQUESTIONS_NOTNULL)
    private int totalQuestions;
}
