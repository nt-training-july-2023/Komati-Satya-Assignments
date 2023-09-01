package com.example.demo.dto;
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
    private String userName;
    /**
     * stores user email.
     */
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
    private String gender;
    /**
     * stores user phone number.
     */
    private String phoneNumber;
    /**
     * stores user date of birth.
     */
    private String dateOfBirth;
}
