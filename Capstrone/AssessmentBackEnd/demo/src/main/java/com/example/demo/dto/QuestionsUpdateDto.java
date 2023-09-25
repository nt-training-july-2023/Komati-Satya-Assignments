package com.example.demo.dto;

import jakarta.persistence.Column;
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
public class QuestionsUpdateDto {
    /**
     * stores the question.
     */
    @Column(nullable = false)
    @NotEmpty(message = "question is required")
    private String question;
    /**
     * stores the option1.
     */
    @Column(nullable = false)
    @NotEmpty(message = "option1 is required")
    private String option1;
    /**
     * stores the option2.
     */
    @Column(nullable = false)
    @NotEmpty(message = "option2 is required")
    private String option2;
    /**
     * stores the option3.
     */
    @Column(nullable = false)
    @NotEmpty(message = "option3 is required")
    private String option3;
    /**
     * stores the option4.
     */
    @Column(nullable = false)
    @NotEmpty(message = "option4 is required")
    private String option4;
    /**
     * stores the correct option.
     */
    @Column(nullable = false)
    @NotEmpty(message = "correct option is required")
    private String correctOption;

}
