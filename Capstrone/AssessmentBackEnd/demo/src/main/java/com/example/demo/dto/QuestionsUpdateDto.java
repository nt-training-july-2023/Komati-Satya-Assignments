package com.example.demo.dto;

import jakarta.persistence.Column;
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
    private String question;
    /**
     * stores the option1.
     */
    @Column(nullable = false)
    private String option1;
    /**
     * stores the option2.
     */
    @Column(nullable = false)
    private String option2;
    /**
     * stores the option3.
     */
    @Column(nullable = false)
    private String option3;
    /**
     * stores the option4.
     */
    @Column(nullable = false)
    private String option4;
    /**
     * stores the correct option.
     */
    @Column(nullable = false)
    private String correctOption;

}
