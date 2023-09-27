package com.example.demo.dto;
import com.example.demo.validationMessages.ValidationMessages;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Student data transfer object.
 */

@NoArgsConstructor
@Getter
@Setter
public class StudentDto {
    /**
     * stores user name.
     */
    @NotEmpty(message = ValidationMessages.NAME_NOTBLANK)
    private String userName;
    /**
     * stores user email.
     */
    @NotEmpty(message = ValidationMessages.EMAIL_NOTBLANK)
    private String email;
    /**
     * stores user id.
     */
    private int userId;
    /**
     * stores user role.
     */
    private String role;
    /**
     * stores user gender.
     */
    @NotEmpty(message = ValidationMessages.GENDER_NOTBLANK)
    private String gender;
    /**
     * stores user phone number.
     */
    @NotEmpty(message = ValidationMessages.PHONENUMBER_NOTBLANK)
    private String phoneNumber;
    /**
     * stores user date of birth.
     */
    @NotEmpty(message = ValidationMessages.DOB_NOTBLANK)
    private String dateOfBirth;
}
