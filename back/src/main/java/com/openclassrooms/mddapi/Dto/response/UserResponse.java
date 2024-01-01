package com.openclassrooms.mddapi.Dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class UserResponse {

    private String id;
    private String username;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    /**
     * Default constructor.
     */
    public UserResponse() {
    }

    /**
     * Constructor to create an instance with specific user details.
     *
     * @param id         The unique identifier of the user.
     * @param username   The username of the user.
     * @param email      The email address of the user.
     * @param created_at The date and time the user was created.
     * @param updated_at The date and time the user was last updated.
     */
    public UserResponse(String id, String username, String email, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    /**
     * Constructor to create an instance from a Map.
     *
     * @param userInfo A map containing user information.
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
