package com.example.demo.dto;

import java.util.Objects;

import com.example.demo.validationMessages.ValidationMessages;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * Result data transfer object.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
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
     * stores the attempted questions.
     */
    @NotNull(message = ValidationMessages.ATTEMPTEDQUESTIONS_NOTNULL)
    private int attemptedQuestions;
    /**
     * stores userName.
     */
    @NotEmpty(message = ValidationMessages.NAME_NOTBLANK)
    private String firstName;
    /**
     * stores userName.
     */
    @NotEmpty(message = ValidationMessages.NAME_NOTBLANK)
    private String lastName;
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
    /**
     * hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(attemptedQuestions, categoryId, categoryName,
                dateAndTime, email, firstName, lastName, maxMarks, obtainMarks,
                quizName, resultId, totalQuestions, userId);
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
        ResultDto other = (ResultDto) obj;
        return attemptedQuestions == other.attemptedQuestions
                && categoryId == other.categoryId
                && Objects.equals(categoryName, other.categoryName)
                && Objects.equals(dateAndTime, other.dateAndTime)
                && Objects.equals(email, other.email)
                && Objects.equals(firstName, other.firstName)
                && Objects.equals(lastName, other.lastName)
                && maxMarks == other.maxMarks
                && obtainMarks == other.obtainMarks
                && Objects.equals(quizName, other.quizName)
                && resultId == other.resultId
                && totalQuestions == other.totalQuestions
                && userId == other.userId;
    }
  }
