package com.example.demo.entity;

import java.util.List;

import com.example.demo.validationMessages.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;

/**
 * Student entity class.
 */

@NoArgsConstructor
@Entity
public class Student {
    /**
     * stores the user id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    /**
     * stores the user name.
     */
    @Column(nullable = false)
    @NotEmpty(message = ValidationMessages.NAME_NOTBLANK)
    private String userName;
    /**
     * stores the user email.
     */
    @Column(nullable = false)
    @NotEmpty(message = ValidationMessages.EMAIL_NOTBLANK)
    @Pattern(regexp = "^[A-Z0-9a-z.]+@nucleusteq[.]com$",
    message = ValidationMessages.EMAIL_PATTERN)
    private String email;
    /**
     * stores the user gender.
     */
    @Column(nullable = false)
    @NotEmpty(message = ValidationMessages.GENDER_NOTBLANK)
    private String gender;
    /**
     * stores the user phone number.
     */
    @Column(nullable = false)
    @NotEmpty(message = ValidationMessages.PHONENUMBER_NOTBLANK)
    @Pattern(regexp = "^[0-9]{10}$",
    message = ValidationMessages.PHONENUMBER_PATTERN)
    private String phoneNumber;
    /**
     * stores the user role.
     */
    @Column(nullable = false)
    @NotEmpty(message = ValidationMessages.ROLE_NOTBLANK)
    private String role;
    /**
     * stores the user password.
     */
    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&-+=])(?=\\S+$).{8,}$",
    message = ValidationMessages.PASSWORD_PATTERN)
    @NotEmpty(message = ValidationMessages.PASSWORD_NOTBLANK)
    private String password;
    /**
     * stores the user date of birth.
     */
    @NotEmpty(message = ValidationMessages.DOB_NOTBLANK)
    @Column(nullable = false)
    private String dateOfBirth;

    /**
     * get the user id.
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * set the user id.
     * @param userIdd stores id
     */
    public void setUserId(final int userIdd) {
        this.userId = userIdd;
    }

    /**
     * get the user name.
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * set the user name.
     * @param userNamee stores name
     */
    public void setUserName(final String userNamee) {
        this.userName = userNamee;
    }

    /**
     * get the user email.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set the user email.
     * @param emaill stores email
     */
    public void setEmail(final String emaill) {
        this.email = emaill;
    }

    /**
     * get the user gender.
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * set the user gender.
     * @param genderr stores gender
     */
    public void setGender(final String genderr) {
        this.gender = genderr;
    }

    /**
     * get the user phone number.
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * set the user phone number.
     * @param phoneNumberr stores number
     */
    public void setPhoneNumber(final String phoneNumberr) {
        this.phoneNumber = phoneNumberr;
    }

    /**
     * get the user role.
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * set the user role.
     * @param rolee stores role
     */
    public void setRole(final String rolee) {
        this.role = rolee;
    }

    /**
     * get the user password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set the user password.
     * @param passwordd stores password
     */
    public void setPassword(final String passwordd) {
        this.password = passwordd;
    }

    /**
     * get the user date of birth.
     * @return dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * set the user date of birth.
     * @param dateOfBirthh stores DOB
     */
    public void setDateOfBirth(final String dateOfBirthh) {
        this.dateOfBirth = dateOfBirthh;
    }

    /**
     * one to many relationship with student result.
     */
    @OneToMany(targetEntity = StudentResult.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "stu_id", referencedColumnName = "userId")
    @JsonIgnoreProperties("Student")
    private List<StudentResult> rs;
    /**
     * Constructor.
     * @param userIdd userId
     * @param userNamee user name
     * @param emaill email
     * @param genderr gender
     * @param phoneNumberr phone number
     * @param rolee role
     * @param dateofBirth date of birth
     */
    public Student(final int userIdd, final String userNamee,
         final String emaill,
           final String genderr,
             final String phoneNumberr,
             final String rolee,
             final String dateofBirth) {
        super();
        this.userId = userIdd;
        this.userName = userNamee;
        this.email = emaill;
        this.gender = genderr;
        this.phoneNumber = phoneNumberr;
        this.role = rolee;
        this.dateOfBirth = dateofBirth;
    }
}
