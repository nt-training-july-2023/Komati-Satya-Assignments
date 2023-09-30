package com.example.demo.entity;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.validationMessages.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
     * @param quizIdd quiz id
     */
    public void setQuizId(final int quizIdd) {
        this.quizId = quizIdd;
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
     * @param topicNamee topic
     */
    public void setTopicName(final String topicNamee) {
        this.topicName = topicNamee;
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
     * @param topicDescriptionn topic
     */
    public void setTopicDescription(final String topicDescriptionn) {
        this.topicDescription = topicDescriptionn;
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
     * @param timerr time
     */
    public void setTimer(final int timerr) {
        this.timer = timerr;
    }
    /**
     * get question.
     * @return category
     */
    public Category getCate() {
        return new Category(category.getCategoryId(),
                category.getCategoryName(),
                category.getCategoryDescription());
    }
    /**
     * get question.
     * @param categoryy category
     */
    public void setCate(final Category categoryy) {
        this.category = new Category(categoryy.getCategoryId(),
                categoryy.getCategoryName(),
                categoryy.getCategoryDescription());
    }
    /**
     * get question.
     * @return list
     */
    public List<StudentResult> getSe() {
        return new ArrayList<>(studentResult);
    }
    /**
     * get question.
     * @param student student result
     */
    public void setSe(final List<StudentResult> student) {
        this.studentResult = new ArrayList<>(student);
    }
    /**
     * get question.
     * @return question question
     */
    public List<Questions> getQue() {
        return new ArrayList<>(questions);
    }
    /**
     * set question.
     * @param question question
     */
    public void setQue(final List<Questions> question) {
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
    @JsonIgnoreProperties("qu")
    private Category category;
    /**
     * one to many relationship with quiz.
     */
    @OneToMany(targetEntity = StudentResult.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "qz_id", referencedColumnName = "quizId")
    @JsonIgnoreProperties("Quiz")
    @JsonIgnore
    private List<StudentResult> studentResult;
    /**
     * one to many relationship with questions.
     */
    @OneToMany(targetEntity = Questions.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "qz_fk", referencedColumnName = "quizId")
    @JsonIgnoreProperties("Quiz")
    private List<Questions> questions;
}
