package com.example.demo.entity;
import com.example.demo.validationMessages.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * questions entity class.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Questions {
    /**
     * stores the question id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int qid;
    /**
     * stores the question.
     */
    @Column(nullable = false, unique = true)
    private String question;
    /**
     * stores the option1.
     */
    @Column(nullable = false)
    private String option1;
    /**
     * Constructor.
     * @param newquestion question
     * @param optionA question
     * @param optionB question
     * @param optionC question
     * @param optionD question
     * @param correctoption question
     */
    public Questions(final String newquestion,
          final String optionA,
         final String optionB,
        final String optionC, final String optionD,
        final String correctoption) {
        super();
       this.question = newquestion;
       this.option1 = optionA;
       this.option2 = optionB;
        this.option3 = optionC;
        this.option4 = optionD;
      this.correctOption = correctoption;
   }
    /**
     * stores the option2.
     */
    @Column(nullable = false)
    @NotEmpty(message = ValidationMessages.OPTION2_NOTBLANK)
    private String option2;
    /**
     * stores the option3.
     */
    @Column(nullable = false)
    @NotEmpty(message = ValidationMessages.OPTION3_NOTBLANK)
    private String option3;
    /**
     * stores the option4.
     */
    @Column(nullable = false)
    @NotEmpty(message = ValidationMessages.OPTION4_NOTBLANK)
    private String option4;
    /**
     * stores the correct option.
     */
    @Column(nullable = false)
    @NotEmpty(message = ValidationMessages.CORRECTANSWER_NOTBLANK)
    private String correctOption;
    /**
     * many to one relationship with quiz.
     */
    @ManyToOne
    @JoinColumn(name = "qz_fk")
    @JsonIgnoreProperties("questions")
    private Quiz quiz;
    /**
     * get.
     * @return quiz
     */
    public Quiz getQuiz() {
        return new Quiz(quiz.getQuizId(), quiz.getTopicName(),
                quiz.getTopicDescription(), quiz.getTimer()
                );
    }
    /**
     * set.
     * @param quizParam quiz
     */
    public void setQuiz(final Quiz quizParam) {
        this.quiz = new Quiz(quizParam.getQuizId(), quizParam.getTopicName(),
                quizParam.getTopicDescription(), quizParam.getTimer()
                );
    }
}
