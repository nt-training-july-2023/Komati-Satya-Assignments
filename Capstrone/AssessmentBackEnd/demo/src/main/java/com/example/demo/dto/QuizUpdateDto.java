package com.example.demo.dto;


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
    private String topicName;
    /**
     * stores the topic description.
     */
    private String topicDescription;
    /**
     * stores the maximum marks.
     */
    private int maxMarks;
    /**
     * stores the pass marks.
     */
    private int passMarks;

}
