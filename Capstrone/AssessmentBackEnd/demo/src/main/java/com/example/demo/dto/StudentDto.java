package com.example.demo.dto;

import java.util.Objects;

import com.example.demo.validationMessages.ValidationMessages;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
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
     * stores first name.
     */
    @NotBlank(message = ValidationMessages.NAME_NOTBLANK)
    private String firstName;
    /**
     * stores last name.
     */
    @NotBlank(message = ValidationMessages.NAME_NOTBLANK)
    private String lastName;
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
    @Enumerated(EnumType.STRING)
    private Gender gender;
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
    /**
     * hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(dateOfBirth, email, firstName, gender, lastName,
                phoneNumber, role, userId);
    }
    /**
     * equals code.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        StudentDto other = (StudentDto) obj;
        return Objects.equals(dateOfBirth, other.dateOfBirth)
                && Objects.equals(email, other.email)
                && Objects.equals(firstName, other.firstName)
                && gender == other.gender
                && Objects.equals(lastName, other.lastName)
                && Objects.equals(phoneNumber, other.phoneNumber)
                && Objects.equals(role, other.role) && userId == other.userId;
    }
}
