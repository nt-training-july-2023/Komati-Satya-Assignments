package com.example.demo.dto;

import java.util.Objects;

import com.example.demo.validationMessages.ValidationMessages;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = ValidationMessages.QUIZNAME_NOTBLANK)
    private String topicName;
    /**
     * stores the topic description.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessages.QUIZDESCRIPTION_NOTBLANK)
    private String topicDescription;
    /**
     * category id.
     */
    @NotNull(message = ValidationMessages.CATEGORY_NOTNULL)
    private int categoryId;
    /**
     * stores time.
     */
    @Min(value = 1, message = ValidationMessages.TIMELIMIT_NOTBLANK)
    private int timer;
    /**
     * hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(categoryId, quizId, timer, topicDescription,
                topicName);
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
        QuizDto other = (QuizDto) obj;
        return categoryId == other.categoryId && quizId == other.quizId
                && timer == other.timer
                && Objects.equals(topicDescription, other.topicDescription)
                && Objects.equals(topicName, other.topicName);
    }
}
