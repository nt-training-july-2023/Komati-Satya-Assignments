package com.example.demo.entity;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    private String userName;
    /**
     * stores the user email.
     */
    @Column(nullable = false)
    private String email;
    /**
     * stores the user gender.
     */
    @Column(nullable = false)
    private String gender;
    /**
     * stores the user phone number.
     */
    @Column(nullable = false)
    private String phoneNumber;
    /**
     * stores the user role.
     */
    @Column(nullable = false)
    private String role;
    /**
     * stores the user password.
     */
    @Column(nullable = false)
    private String password;
    /**
     * stores the user date of birth.
     */
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
     * @param userid stores id
     */
    public void setUserId(final int userid) {
        this.userId = userid;
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
     * @param username stores name
     */
    public void setUserName(final String username) {
        this.userName = username;
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
     * @param useremail stores email
     */
    public void setEmail(final String useremail) {
        this.email = useremail;
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
     * @param usergender stores gender
     */
    public void setGender(final String usergender) {
        this.gender = usergender;
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
     * @param phonenumber stores number
     */
    public void setPhoneNumber(final String phonenumber) {
        this.phoneNumber = phonenumber;
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
     * @param userrole stores role
     */
    public void setRole(final String userrole) {
        this.role = userrole;
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
     * @param userPassword stores password
     */
    public void setPassword(final String userPassword) {
        this.password = userPassword;
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
     * @param dateofBirth stores DOB
     */
    public void setDateOfBirth(final String dateofBirth) {
        this.dateOfBirth = dateofBirth;
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
     * @param userid userId
     * @param username user name
     * @param useremail email
     * @param usergender gender
     * @param phonenumber phone number
     * @param userrole role
     * @param dateofbirth date of birth
     */
    public Student(final int userid, final String username,
         final String useremail,
           final String usergender,
             final String phonenumber,
             final String userrole,
             final String dateofbirth) {
        super();
        this.userId = userid;
        this.userName = username;
        this.email = useremail;
        this.gender = usergender;
        this.phoneNumber = phonenumber;
        this.role = userrole;
        this.dateOfBirth = dateofbirth;
    }
}
