package com.example.demo.dto;


import com.example.demo.validationMessages.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotBlank(message = ValidationMessages.PASSWORD_NOTBLANK)
    private String password;
    /**
     * email variable.
     * @param email email variable
     */
    @NotBlank(message = ValidationMessages.EMAIL_NOTBLANK)
    private String email;
}
