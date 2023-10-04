package com.example.demo.dto;

import com.example.demo.validationMessages.ValidationMessages;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * questions data transfer object class.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionsDto {
    /**
     * stores the question.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessages.QUESTION_NOTBLANK)
    private String question;
    /**
     * stores the option1.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessages.OPTION1_NOTBLANK)
    private String option1;
    /**
     * stores the option2.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessages.OPTION2_NOTBLANK)
    private String option2;
    /**
     * stores the option3.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessages.OPTION3_NOTBLANK)
    private String option3;
    /**
     * stores the option4.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessages.OPTION4_NOTBLANK)
    private String option4;
    /**
     * stores the correct option.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessages.CORRECTANSWER_NOTBLANK)
    private String correctOption;
    /**
     * quiz id.
     */
    private int quizId;
    /**
     * questionId.
     */
    private int questionId;
}
