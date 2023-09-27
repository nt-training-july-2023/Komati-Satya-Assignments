package com.example.demo.dto;


import com.example.demo.validationMessages.ValidationMessages;

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
    @NotEmpty(message = ValidationMessages.QUIZNAME_NOTBLANK)
    private String topicName;
    /**
     * stores the topic description.
     */
    @NotEmpty(message = ValidationMessages.QUIZDESCRIPTION_NOTBLANK)
    private String topicDescription;
    /**
     * stores the time.
     */
    @NotNull(message = ValidationMessages.TIMELIMIT_NOTBLANK)
    private int timer;
}
