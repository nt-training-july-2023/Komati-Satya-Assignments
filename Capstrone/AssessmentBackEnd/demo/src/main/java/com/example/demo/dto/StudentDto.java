package com.example.demo.dto;
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
    @NotEmpty(message = "username is required")
    private String userName;
    /**
     * stores user email.
     */
    @NotEmpty(message = "email is required")
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
    @NotEmpty(message = "gender is required")
    private String gender;
    /**
     * stores user phone number.
     */
    @NotEmpty(message = "phone number is required")
    private String phoneNumber;
    /**
     * stores user date of birth.
     */
    @NotEmpty(message = "date of birth is required")
    private String dateOfBirth;
}
