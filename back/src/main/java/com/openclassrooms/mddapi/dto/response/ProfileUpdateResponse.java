package com.openclassrooms.mddapi.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for profile updating.
 */
@Getter
@Setter
public class ProfileUpdateResponse {

    /**
     * The token content
     */
    private String token;
    /**
     * The message content, conveying information about the result of an operation or any other relevant information.
     */
    private String message;

    /**
     * Constructor to create a ProfileUpdateResponse instance with a specific Message and a JWT token.
     *
     * @param token The JWT token string.
     * @param message The message string
     */
    public ProfileUpdateResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

}
