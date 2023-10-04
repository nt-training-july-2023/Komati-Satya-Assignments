package com.example.demo.dto;


import com.example.demo.validationMessages.ValidationMessages;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Student data transfer object.
 */
@NoArgsConstructor
@Getter
@Setter
public class StudentSaveDto {
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
    @NotBlank(message = ValidationMessages.NAME_NOTBLANK)
    private String userName;
    /**
     * stores the user email.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessages.EMAIL_NOTBLANK)
    @Pattern(regexp = "^[a-z][a-zA-Z0-9.]*@nucleusteq\\.com",
    message = ValidationMessages.EMAIL_PATTERN)
    private String email;
    /**
     * stores the user gender.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessages.GENDER_NOTBLANK)
    private String gender;
    /**
     * stores the user phone number.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessages.PHONENUMBER_NOTBLANK)
    @Pattern(regexp = "^[0-9]{10}$",
    message = ValidationMessages.PASSWORD_PATTERN)
    private String phoneNumber;
    /**
     * stores the user role.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessages.ROLE_NOTBLANK)
    private String role;
    /**
     * stores the user password.
     */
    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&-+=])(?=\\S+$).{8,}$",
    message = ValidationMessages.PASSWORD_PATTERN)
    @NotBlank(message = ValidationMessages.PASSWORD_NOTBLANK)
    private String password;
    /**
     * stores the user date of birth.
     */
    @NotBlank(message = ValidationMessages.DOB_NOTBLANK)
    @Column(nullable = false)
    private String dateOfBirth;
}
