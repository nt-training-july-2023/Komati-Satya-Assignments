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
     * @param questionn question
     * @param option11 question
     * @param option22 question
     * @param option33 question
     * @param option44 question
     * @param correctOptionn question
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
    @JsonIgnoreProperties("que")
    private Quiz qui;
    /**
     * get.
     * @return quiz
     */
    public Quiz getQui() {
        return new Quiz(qui.getQuizId(), qui.getTopicName(),
                qui.getTopicDescription(), qui.getTimer()
                );
    }
    /**
     * set.
     * @param quii quiz
     */
    public void setQui(final Quiz quii) {
        this.qui = new Quiz(quii.getQuizId(), quii.getTopicName(),
                quii.getTopicDescription(), quii.getTimer()
                );
    }
}
