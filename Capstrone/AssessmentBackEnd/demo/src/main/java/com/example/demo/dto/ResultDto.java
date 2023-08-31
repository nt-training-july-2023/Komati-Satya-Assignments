package com.example.demo.dto;

import java.sql.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Result data transfer object.
 */
@NoArgsConstructor
@Getter
@Setter
public class ResultDto {
    /**
     * stores the marks.
     */
    private int maxMarks;
    /**
     * stores the date and time.
     */
    private Date dateAndTime;
    /**
     * stores the result.
     */
    private String result;
    /**
     * stores the res.
     */
    private int attemptedQuestions;
    /**
     * stores userName.
     */
    private String userName;
    /**
     * set Date and time.
     * @param newDateAndTime time
     */
    public final void setDateAndTime(final Date newDateAndTime) {
        this.dateAndTime = new Date(newDateAndTime.getTime());
    }
    /**
     * get Date and time.
     *@return date
     */
    public final Date getDateAndTime() {
        return new Date(dateAndTime.getTime());
    }
}
