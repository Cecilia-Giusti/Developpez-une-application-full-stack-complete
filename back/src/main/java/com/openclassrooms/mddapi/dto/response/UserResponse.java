package com.openclassrooms.mddapi.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Data Transfer Object (DTO) representing the response for user-related operations.
 */
@Getter
@Setter
public class UserResponse {

    private String id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    /**
     * Default constructor for UserResponse.
     */
    public UserResponse() {
    }
}
