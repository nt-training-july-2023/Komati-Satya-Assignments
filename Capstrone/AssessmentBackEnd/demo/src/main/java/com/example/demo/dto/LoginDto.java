package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * loginDto class.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
    /**
     * password variable.
     * @param password variable
     */
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&-+=])(?=\\S+$).{8,}$",
    message = "Minimum eight and m")
    @NotEmpty(message = "password is required")
    private String password;
    /**
     * email variable.
     * @param email email variable
     */
    @NotEmpty(message = "email is required")
    @Pattern(regexp = "^[A-Z0-9a-z+_-]+@nucleusteq[.]com$",
    message = "Email is not valid")
    private String email;
}
