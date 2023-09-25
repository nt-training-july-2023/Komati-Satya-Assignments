package com.example.demo.dto;

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
    @NotEmpty(message = "topic name is required")
    private String topicName;
    /**
     * stores the topic description.
     */
    @Column(nullable = false)
    private String topicDescription;
    /**
     * category id.
     */
    private int categoryId;
    /**
     * stores time.
     */
    @NotNull(message = "timer is required")
    private int timer;
}
