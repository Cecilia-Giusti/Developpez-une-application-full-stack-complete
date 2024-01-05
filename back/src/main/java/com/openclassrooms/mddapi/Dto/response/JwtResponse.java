package com.openclassrooms.mddapi.Dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for JWT (JSON Web Token) responses.
 * This class encapsulates the JWT used for client authentication.
 */
@Getter
@Setter
public class JwtResponse {

    private String token;

    /**
     * Default constructor.
     * Creates an empty JwtResponse object.
     */
    public JwtResponse() {
    }

    /**
     * Constructor to create a JwtResponse instance with a specific JWT token.
     * This token is used for client authentication and must be included in the header of authenticated requests.
     *
     * @param token The JWT token string.
     */
    public JwtResponse(String token) {
        this.token = token;
    }
}
