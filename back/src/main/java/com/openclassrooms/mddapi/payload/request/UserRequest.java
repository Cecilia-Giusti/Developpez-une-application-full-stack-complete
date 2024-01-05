package com.openclassrooms.mddapi.payload.request;
import lombok.Data;
import javax.validation.constraints.Email;

/**
 * Data Transfer Object (DTO) for user request.
 */
@Data
public class UserRequest {

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The email of the user.
     */
    @Email(message = "Enter a valid email")
    private String email;

}
