package com.example.demo.entity;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;
/**
 * Quiz entity class.
 */
@NoArgsConstructor
@Entity
public class Quiz {
    /**
     * stores the quiz id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * stores the time.
     */
    @Column(nullable = false)
    private int timer;
    /**
     * get quiz.
     * @return quiz
     */
    public int getQuizId() {
        return quizId;
    }
    /**
     * set quiz id.
     * @param quizid quiz id
     */
    public void setQuizId(final int quizid) {
        this.quizId = quizid;
    }
    /**
     * get question.
     * @return topic
     */
    public String getTopicName() {
        return topicName;
    }
    /**
     * set topic name.
     * @param topicname topic
     */
    public void setTopicName(final String topicname) {
        this.topicName = topicname;
    }
    /**
     * get question.
     * @return topic
     */
    public String getTopicDescription() {
        return topicDescription;
    }
    /**
     * get question.
     * @param topicdescription topic
     */
    public void setTopicDescription(final String topicdescription) {
        this.topicDescription = topicdescription;
    }
    /**
     * get timer.
     * @return time
     */
    public int getTimer() {
        return timer;
    }
    /**
     * set time.
     * @param timervalue time
     */
    public void setTimer(final int timervalue) {
        this.timer = timervalue;
    }
    /**
     * get question.
     * @return category
     */
    public Category getCategory() {
        return new Category(category.getCategoryId(),
                category.getCategoryName(),
                category.getCategoryDescription());
    }
    /**
     * get question.
     * @param categoryy category
     */
    public void setCategory(final Category categoryy) {
        this.category = new Category(categoryy.getCategoryId(),
                categoryy.getCategoryName(),
                categoryy.getCategoryDescription());
    }
    /**
     * get question.
     * @return question question
     */
    public List<Questions> getQuestions() {
        return new ArrayList<>(questions);
    }
    /**
     * set question.
     * @param question question
     */
    public void setQuestions(final List<Questions> question) {
        this.questions = new ArrayList<>(question);
    }
    /**
     * Constructor.
     * @param quizid quiz id
     * @param topicname topic name
     * @param topicdescription description
     * @param timerr time
     */
    public Quiz(final int quizid, final String topicname,
            final String topicdescription, final int timerr) {
        super();
        this.quizId = quizid;
        this.topicName = topicname;
        this.topicDescription = topicdescription;
        this.timer = timerr;
    }
    /**
     * Many to one relationship with category.
     */
    @ManyToOne
    @JoinColumn(name = "c_fk")
    @JsonIgnoreProperties("quiz")
    private Category category;
    /**
     * one to many relationship with questions.
     */
    @OneToMany(targetEntity = Questions.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "qz_fk", referencedColumnName = "quizId")
    @JsonIgnoreProperties("Quiz")
    private List<Questions> questions;
}
