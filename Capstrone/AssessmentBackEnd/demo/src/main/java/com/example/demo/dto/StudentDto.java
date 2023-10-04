package com.example.demo.dto;
import com.example.demo.validationMessages.ValidationMessages;

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
public class StudentDto {
    /**
     * stores user name.
     */
    @NotBlank(message = ValidationMessages.NAME_NOTBLANK)
    private String userName;
    /**
     * stores user email.
     */
    @NotBlank(message = ValidationMessages.EMAIL_NOTBLANK)
    @Pattern(regexp = "^[a-z][a-zA-Z0-9.]*@nucleusteq\\.com",
    message = ValidationMessages.EMAIL_PATTERN)

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
    @NotBlank(message = ValidationMessages.GENDER_NOTBLANK)
    private String gender;
    /**
     * stores user phone number.
     */
    @NotBlank(message = ValidationMessages.PHONENUMBER_NOTBLANK)
    @Pattern(regexp = "^[0-9]{10}$",
    message = ValidationMessages.PHONENUMBER_PATTERN)
    private String phoneNumber;
    /**
     * stores user date of birth.
     */
    @NotBlank(message = ValidationMessages.DOB_NOTBLANK)
    private String dateOfBirth;
}
