package com.openclassrooms.mddapi.Dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Data Transfer Object (DTO) representing the response for user-related operations.
 * This class encapsulates user-related information typically used in responses to requests for user data.
 */
@Getter
@Setter
public class UserResponse {

    private String id;
    private String username;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    /**
     * Default constructor for UserResponse.
     */
    public UserResponse() {
    }

    /**
     * Constructs a UserResponse with detailed user information.
     * This constructor is used for creating a response object with specific user details.
     *
     * @param id         The unique identifier of the user.
     * @param username   The username of the user.
     * @param email      The email address of the user.
     * @param created_at The date and time when the user account was created.
     * @param updated_at The date and time when the user account was last updated.
     */
    public UserResponse(String id, String username, String email, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    /**
     * Constructs a UserResponse from a Map containing user information.
     * This constructor allows creating a user response object using a map with user-related key-value pairs.
     *
     * @param userInfo A map containing key-value pairs of user information.
     */
    public UserResponse(Map<String, Object> userInfo) {
        if (userInfo != null) {
            this.id = (String) userInfo.get("id");
            this.username = (String) userInfo.get("username");
            this.email = (String) userInfo.get("email");
            this.created_at = userInfo.get("created_at") != null ? LocalDateTime.parse(userInfo.get("created_at").toString()) : null;
            this.updated_at = userInfo.get("updated_at") != null ? LocalDateTime.parse(userInfo.get("updated_at").toString()) : null;
        }
    }
}
