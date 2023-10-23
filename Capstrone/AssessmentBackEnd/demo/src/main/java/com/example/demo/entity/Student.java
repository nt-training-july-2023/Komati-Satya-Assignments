package com.example.demo.entity;

import java.util.List;
import com.example.demo.dto.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Student entity class.
 */
@NoArgsConstructor
@Entity
@ToString
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
    private String firstName;
    /**
     * stores the last name.
     */
    @Column(nullable = false)
    private String lastName;
    /**
     * stores the user gender.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    /**
     * Stores contact information (email and phone number).
     */
    @Embedded
    private ContactInfo contactInfo;
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
    public String getFirstName() {
        return firstName;
    }

    /**
     * set the first name.
     * @param firstname stores name
     */
    public void setFirstName(final String firstname) {
        this.firstName = firstname;
    }
    /**
     * get the last name.
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * set last name.
     * @param lastname last name
     */
    public void setLastName(final String lastname) {
        this.lastName = lastname;
    }
    /**
     * get the contact information (email and phone number).
     * @return contactInfo
     */
    public ContactInfo getContactInfo() {
        return new ContactInfo(contactInfo.getEmail(),
                contactInfo.getPhoneNumber());
    }
    /**
     * set the contact information (email and phone number).
     * @param contactinfo stores email and phone number
     */
    public void setContactInfo(final ContactInfo contactinfo) {
        this.contactInfo = new ContactInfo(contactinfo.getEmail(),
                contactinfo.getPhoneNumber());
    }
    /**
     * get the user gender.
     * @return gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * set the user gender.
     * @param usergender stores gender
     */
    public void setGender(final Gender usergender) {
        gender = usergender;
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
    @JsonIgnoreProperties("student")
    private List<StudentResult> studentResult;
    /**
     * Constructor.
     * @param userid userId
     * @param firstname first name
     * @param lastname last name
     * @param contactinfo contact information (email and phone number)
     * @param usergender gender
     * @param userrole role
     * @param dateofbirth date of birth
     */
    public Student(final int userid, final String firstname,
            final String lastname,
            final ContactInfo contactinfo, final Gender usergender,
                   final String userrole,
                final String dateofbirth) {
        this.userId = userid;
        this.firstName = firstname;
        this.lastName = lastname;
       this.contactInfo = new ContactInfo(contactinfo.getEmail(),
               contactinfo.getPhoneNumber());
        this.gender = usergender;
        this.role = userrole;
        this.dateOfBirth = dateofbirth;
    }
}
