package com.example.demo.dto;

import com.example.demo.validationMessages.ValidationMessages;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Quiz data transfer object.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizDto {
    /**
     * stores the quiz id.
     */
    @Column(nullable = false)
    private int quizId;
    /**
     * stores the topic name.
     */
    @Column(nullable = false)
    @NotEmpty(message = ValidationMessages.QUIZNAME_NOTBLANK)
    private String topicName;
    /**
     * stores the topic description.
     */
    @Column(nullable = false)
    @NotEmpty(message = ValidationMessages.QUIZDESCRIPTION_NOTBLANK)
    private String topicDescription;
    /**
     * category id.
     */
    @NotEmpty(message = ValidationMessages.CATEGORY_NOTNULL)
    private int categoryId;
    /**
     * stores time.
     */
    @NotNull(message = ValidationMessages.TIMELIMIT_NOTBLANK)
    private int timer;
}
