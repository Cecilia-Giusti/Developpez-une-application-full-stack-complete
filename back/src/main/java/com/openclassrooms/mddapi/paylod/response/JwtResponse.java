
package com.openclassrooms.mddapi.paylod.response;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO representing the response containing a token.
 */
@Getter
@Setter
public class JwtResponse {

    private String token;

    /**
     * Default constructor.
     */
    public JwtResponse() {
    }

    /**
     * Constructor to create an instance with a specific token.
     *
     * @param token The token string.
     */
    public JwtResponse(String token) {
        this.token = token;
    }
}
