package com.example.demo.entity;
import java.util.ArrayList;
import java.util.List;
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
    @Column(nullable = false, unique = true)
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
    public final int getQuizId() {
        return quizId;
    }
    /**
     * set quiz id.
     * @param quizIdd quiz id
     */
    public final void setQuizId(final int quizIdd) {
        this.quizId = quizIdd;
    }
    /**
     * get question.
     * @return topic
     */
    public final String getTopicName() {
        return topicName;
    }
    /**
     * set topic name.
     * @param topicNamee topic
     */
    public final void setTopicName(final String topicNamee) {
        this.topicName = topicNamee;
    }
    /**
     * get question.
     * @return topic
     */
    public final String getTopicDescription() {
        return topicDescription;
    }
    /**
     * get question.
     * @param topicDescriptionn topic
     */
    public final void setTopicDescription(final String topicDescriptionn) {
        this.topicDescription = topicDescriptionn;
    }
    /**
     * get timer.
     * @return time
     */
    public final int getTimer() {
        return timer;
    }
    /**
     * set time.
     * @param timerr time
     */
    public final void setTimer(final int timerr) {
        this.timer = timerr;
    }
    /**
     * get question.
     * @return category
     */
    public final Category getCate() {
        return new Category(cate.getCategoryId(), cate.getCategoryName(),
                cate.getCategoryDescription());
    }
    /**
     * get question.
     * @param catee category
     */
    public final void setCate(final Category catee) {
        this.cate = new Category(catee.getCategoryId(), catee.getCategoryName(),
                catee.getCategoryDescription());
    }
    /**
     * get question.
     * @return list
     */
    public final List<StudentResult> getSe() {
        return new ArrayList<>(se);
    }
    /**
     * get question.
     * @param see student result
     */
    public final void setSe(final List<StudentResult> see) {
        this.se = new ArrayList<>(see);
    }
    /**
     * get question.
     * @return question question
     */
    public final List<Questions> getQue() {
        return new ArrayList<>(que);
    }
    /**
     * set question.
     * @param quee question
     */
    public final void setQue(final List<Questions> quee) {
        this.que = new ArrayList<>(quee);
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
    private Category cate;
    /**
     * one to many relationship with quiz.
     */
    @OneToMany(targetEntity = StudentResult.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "qz_id", referencedColumnName = "quizId")
    @JsonIgnoreProperties("Quiz")
    @JsonIgnore
    private List<StudentResult> se;
    /**
     * one to many relationship with questions.
     */
    @OneToMany(targetEntity = Questions.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "qz_fk", referencedColumnName = "quizId")
    @JsonIgnoreProperties("Quiz")
    private List<Questions> que;
}
