package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
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
    private String topicName;
    /**
     * stores the topic description.
     */
    @Column(nullable = false)
    private String topicDescription;
    /**
     * stores the maximum marks.
     */
    @Column(nullable = false)
    private int maxMarks;
    /**
     * stores the pass marks.
     */
    @Column(nullable = false)
    private int passMarks;
    /**
     * category id.
     */
    private int categoryId;
}
