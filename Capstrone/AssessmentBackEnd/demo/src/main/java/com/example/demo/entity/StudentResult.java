
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
    @JsonIgnoreProperties("studentResult")
    private Quiz quiz;
    /**
     * categoryId.
     */
    private int categoryId;
    /**
     * stores the result id.
     */
    @ManyToOne
    @JoinColumn(name = "stu_id")
    @JsonIgnoreProperties("studentResult")
    private Student studentResult;
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
     * @param maxmarks marks
     */
    public void setMaxMarks(final int maxmarks) {
        this.maxMarks = maxmarks;
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
     * @param userresult result
     */
    public void setResult(final String userresult) {
        this.result = userresult;
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
     * @param attemptedquestions questions
     */
    public void setAttemptedQuestions(final int attemptedquestions) {
        this.attemptedQuestions = attemptedquestions;
    }
    /**
     * quiz.
     * @return quiz
     */
    public Quiz getQuiz() {
        return new Quiz(quiz.getQuizId(), quiz.getTopicName(),
                quiz.getTopicDescription(), quiz.getTimer()
                );
    }
    /**
     * set question.
     * @param quizParam question
     */
    public void setQuiz(final Quiz quizParam) {
        this.quiz = new Quiz(quizParam.getQuizId(), quizParam.getTopicName(),
                quizParam.getTopicDescription(), quizParam.getTimer()
                );
    }
    /**
     * student.
     * @return student
     */
    public Student getStudentResult() {
        return new Student(studentResult.getUserId(),
                studentResult.getUserName(),
                studentResult.getEmail(), studentResult.getGender(),
                studentResult.getPhoneNumber(), studentResult.getRole(),
                studentResult.getDateOfBirth());
    }
    /**
     * set student.
     * @param student student
     */
    public void setStudentResult(final Student student) {
        this.studentResult = new Student(student.getUserId(),
                student.getUserName(),
                student.getEmail(), student.getGender(),
                student.getPhoneNumber(), student.getRole(),
                student.getDateOfBirth());
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
     * @param dateAndtime date
     */
    public void setDateAndTime(final String dateAndtime) {
        this.dateAndTime = dateAndtime;
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
