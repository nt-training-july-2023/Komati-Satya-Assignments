package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;


/**
 * set the user id.
 */
@NoArgsConstructor
@Entity
public class StudentResult {
    /**
     * stores the result id.
     */
    @Id
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
    public final int getResultId() {
        return resultId;
    }
    /**
     * set id.
     * @param resultid id
     */
    public final void setResultId(final int resultid) {
        this.resultId = resultid;
    }
    /**
     * get marks.
     * @return marks
     */
    public final int getMaxMarks() {
        return maxMarks;
    }
    /**
     * set marks.
     * @param maxMarkss marks
     */
    public final void setMaxMarks(final int maxMarkss) {
        this.maxMarks = maxMarkss;
    }
    /**
     * get result.
     * @return result
     */
    public final String getResult() {
        return result;
    }
    /**
     * set result.
     * @param resultt result
     */
    public final void setResult(final String resultt) {
        this.result = resultt;
    }
    /**
     * get.
     * @return questions
     */
    public final int getAttemptedQuestions() {
        return attemptedQuestions;
    }
    /**
     * set question.
     * @param attemptedQuestionss questions
     */
    public final void setAttemptedQuestions(final int attemptedQuestionss) {
        this.attemptedQuestions = attemptedQuestionss;
    }
    /**
     * quiz.
     * @return quiz
     */
    public final Quiz getQe() {
        return new Quiz(qe.getQuizId(), qe.getTopicName(),
                qe.getTopicDescription(),
                qe.getMaxMarks(), qe.getPassMarks()
                );
    }
    /**
     * set question.
     * @param qee question
     */
    public final void setQe(final Quiz qee) {
        this.qe = new Quiz(qee.getQuizId(), qee.getTopicName(),
                qee.getTopicDescription(),
                qee.getMaxMarks(), qee.getPassMarks()
                );
    }
    /**
     * student.
     * @return student
     */
    public final Student getSs() {
        return new Student(ss.getUserId(), ss.getDateOfBirth(),
                ss.getEmail(), ss.getGender(),
                ss.getRole(), ss.getUserName(), ss.getPhoneNumber());
    }
    /**
     * set student.
     * @param sss student
     */
    public final void setSs(final Student sss) {
        this.ss = new Student(sss.getUserId(), sss.getDateOfBirth(),
                sss.getEmail(), sss.getGender(),
                sss.getRole(), sss.getUserName(), sss.getPhoneNumber());
    }
//    /**
//     * constructor.
//     * @param resultid id
//     * @param maxmarks marks
//     * @param dateandTime date
//     * @param resultt result
//     * @param attemptedquestions question
//     */
//    public StudentResult(final int resultid, final int maxmarks,
//            final String dateandTime,
//            final String resultt,
//            final int attemptedquestions) {
//        super();
//        this.resultId = resultid;
//        this.maxMarks = maxmarks;
//        this.dateAndTime = dateandTime;
//        this.result = resultt;
//        this.attemptedQuestions = attemptedquestions;
//    }
    /**
     * get.
     * @return date
     */
    public final String getDateAndTime() {
        return dateAndTime;
    }
    /**
     * set.
     * @param dateAndTimee date
     */
    public final void setDateAndTime(final String dateAndTimee) {
        this.dateAndTime = dateAndTimee;
    }
}
