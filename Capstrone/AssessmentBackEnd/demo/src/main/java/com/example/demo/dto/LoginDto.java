package com.example.demo.dto;

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
    private String password;
    /**
     * email variable.
     * @param email email variable
     */
    private String email;
}
