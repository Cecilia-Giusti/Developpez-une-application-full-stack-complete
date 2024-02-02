package com.openclassrooms.mddapi.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for user is updating.
 */
@Data
public class UserUpdate {
    /**
     * The username of the user.
     */
    private String username;

    /**
     * The email of the user.
     */
    @Email(message = "Enter a valid email")
    private String email;

    /**
     * The password of the user.
     */
    @NotNull(message = "Password cannot be empty")
    private String password;
}
