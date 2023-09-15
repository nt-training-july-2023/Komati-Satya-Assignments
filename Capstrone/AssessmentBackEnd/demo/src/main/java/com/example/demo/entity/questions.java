package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    public Questions(final String questionn,
          final String option11,
         final String option22,
        final String option33, final String option44,
        final String correctOptionn) {
        super();
       this.question = questionn;
       this.option1 = option11;
       this.option2 = option22;
        this.option3 = option33;
        this.option4 = option44;
      this.correctOption = correctOptionn;
   }
    /**
     * stores the option2.
     */
    @Column(nullable = false)
    private String option2;
    /**
     * stores the option3.
     */
    @Column(nullable = false)
    private String option3;
    /**
     * stores the option4.
     */
    @Column(nullable = false)
    private String option4;
    /**
     * stores the correct option.
     */
    @Column(nullable = false)
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
    public final Quiz getQui() {
        return new Quiz(qui.getQuizId(), qui.getTopicName(),
                qui.getTopicDescription()
                );
    }
    /**
     * set.
     * @param quii quiz
     */
    public final void setQui(final Quiz quii) {
        this.qui = new Quiz(quii.getQuizId(), quii.getTopicName(),
                quii.getTopicDescription()
                );
    }
}
