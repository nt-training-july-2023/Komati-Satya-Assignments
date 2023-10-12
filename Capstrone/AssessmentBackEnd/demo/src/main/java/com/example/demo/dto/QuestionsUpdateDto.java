package com.example.demo.dto;

import java.util.Objects;

import com.example.demo.validationMessages.ValidationMessages;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
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
     * hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(correctOption, option1, option2, option3, option4,
                question);
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
        QuestionsUpdateDto other = (QuestionsUpdateDto) obj;
        return Objects.equals(correctOption, other.correctOption)
                && Objects.equals(option1, other.option1)
                && Objects.equals(option2, other.option2)
                && Objects.equals(option3, other.option3)
                && Objects.equals(option4, other.option4)
                && Objects.equals(question, other.question);
    }
}
