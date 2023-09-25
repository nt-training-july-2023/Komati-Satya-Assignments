package com.example.demo.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @NotEmpty(message = "userName is required")
    private String userName;
    /**
     * stores the user email.
     */
    @Column(nullable = false)
    @NotEmpty(message = "email is required")
    @Pattern(regexp = "^[A-Z0-9a-z+_-]+@nucleusteq[.]com$",
    message = "Email is not valid")
    private String email;
    /**
     * stores the user gender.
     */
    @Column(nullable = false)
    @NotEmpty(message = "gender is required")
    private String gender;
    /**
     * stores the user phone number.
     */
    @Column(nullable = false)
    @NotEmpty(message = "phoneNumber is required")
    @Pattern(regexp = "^[0-9]{10}$",
    message = "Phone number must contain 10 digits")
    private String phoneNumber;
    /**
     * stores the user role.
     */
    @Column(nullable = false)
    @NotEmpty(message = "role is required")
    private String role;
    /**
     * stores the user password.
     */
    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&-+=])(?=\\S+$).{8,}$",
    message = "Minimum eight and m")
    @NotEmpty(message = "password is required")
    private String password;
    /**
     * stores the user date of birth.
     */
    @NotEmpty(message = "dateOfBirth is required")
    @Column(nullable = false)
    private String dateOfBirth;
}
