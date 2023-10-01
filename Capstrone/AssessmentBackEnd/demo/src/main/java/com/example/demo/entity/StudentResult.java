
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
 * set the user id.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class StudentResult {
    /**
     * stores the result id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int resultId;
    /**
     * stores the marks.
     */
    @Column(nullable = false)
    private int maxMarks;
    /**
     * stores the date and time.
     */
    @Column(nullable = false)
    private String dateAndTime;
    /**
     * stores the result.
     */
    @Column(nullable = false)
    private String result;
    /**
     * stores the res.
     */
    @Column(nullable = false)
    private int attemptedQuestions;
    /**
     * stores the result id.
     */
    @ManyToOne
    @JoinColumn(name = "qz_Id")
    @JsonIgnoreProperties("se")
    private Quiz qe;
    /**
     * categoryId.
     */
    //@NotEmpty(message = ValidationMessages.CATEGORY_NOTNULL)
    private int categoryId;
    /**
     * stores the result id.
     */
    @ManyToOne
    @JoinColumn(name = "stu_id")
    @JsonIgnoreProperties("rs")
    private Student ss;
    /**
     * get result.
     * @return result
     */
    public int getResultId() {
        return resultId;
    }
    /**
     * set id.
     * @param resultid id
     */
    public void setResultId(final int resultid) {
        this.resultId = resultid;
    }
    /**
     * get marks.
     * @return marks
     */
    public int getMaxMarks() {
        return maxMarks;
    }
    /**
     * set marks.
     * @param maxMarkss marks
     */
    public void setMaxMarks(final int maxMarkss) {
        this.maxMarks = maxMarkss;
    }
    /**
     * get result.
     * @return result
     */
    public String getResult() {
        return result;
    }
    /**
     * set result.
     * @param resultt result
     */
    public void setResult(final String resultt) {
        this.result = resultt;
    }
    /**
     * get.
     * @return questions
     */
    public int getAttemptedQuestions() {
        return attemptedQuestions;
    }
    /**
     * set question.
     * @param attemptedQuestionss questions
     */
    public void setAttemptedQuestions(final int attemptedQuestionss) {
        this.attemptedQuestions = attemptedQuestionss;
    }
    /**
     * quiz.
     * @return quiz
     */
    public Quiz getQe() {
        return new Quiz(qe.getQuizId(), qe.getTopicName(),
                qe.getTopicDescription(), qe.getTimer()
                );
    }
    /**
     * set question.
     * @param qee question
     */
    public void setQe(final Quiz qee) {
        this.qe = new Quiz(qee.getQuizId(), qee.getTopicName(),
                qee.getTopicDescription(), qee.getTimer()
                );
    }
    /**
     * student.
     * @return student
     */
    public Student getSs() {
        return new Student(ss.getUserId(), ss.getUserName(),
                ss.getEmail(), ss.getGender(),
                 ss.getPhoneNumber(), ss.getRole(), ss.getDateOfBirth());
    }
    /**
     * set student.
     * @param sss student
     */
    public void setSs(final Student sss) {
        this.ss = new Student(sss.getUserId(), sss.getUserName(),
                sss.getEmail(), sss.getGender(),
                sss.getPhoneNumber(), sss.getRole(), sss.getDateOfBirth());
    }
    /**
     * get.
     * @return date
     */
    public String getDateAndTime() {
        return dateAndTime;
    }
    /**
     * set.
     * @param dateAndTimee date
     */
    public void setDateAndTime(final String dateAndTimee) {
        this.dateAndTime = dateAndTimee;
    }
    /**
     * get category id.
     * @return category id.
     */
    public int getCategoryId() {
        return categoryId;
    }
    /**
     * set category id.
     * @param categoryid category id.
     */
    public void setCategoryId(final int categoryid) {
        this.categoryId = categoryid;
    }
}
