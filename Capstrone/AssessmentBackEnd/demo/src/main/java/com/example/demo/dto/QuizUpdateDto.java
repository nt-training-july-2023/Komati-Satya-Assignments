package com.example.demo.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Quiz update data transfer object.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizUpdateDto {

    /**
     * stores the quiz id.
     */
    private int quizId;
    /**
     * stores the topic name.
     */
    @NotEmpty(message = "topic name is required")
    private String topicName;
    /**
     * stores the topic description.
     */
    private String topicDescription;
    /**
     * stores the time.
     */
    @NotNull(message = "timer is required")
    private int timer;
}
