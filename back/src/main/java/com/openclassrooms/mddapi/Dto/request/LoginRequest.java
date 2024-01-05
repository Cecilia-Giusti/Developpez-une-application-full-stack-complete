package com.openclassrooms.mddapi.Dto.request;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Data Transfer Object (DTO) for user login requests.
 */
@Data
public class LoginRequest {

    /**
     * The email of the user.
     */
    @NotNull(message = "Email cannot be empty")
    @Email(message = "Enter a valid email")
    private String email;

    /**
     * The password of the user.
     */
    @NotNull(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[\\d])(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "Password must be at least 8 characters long, contain at least 1 uppercase letter, 1 digit and 1 special character.")
    private String password;
}



