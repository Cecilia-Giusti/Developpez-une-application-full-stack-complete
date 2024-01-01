package com.openclassrooms.mddapi.Dto.request;
import lombok.Data;
import javax.validation.constraints.Email;

@Data
public class UserRequest {

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The email of the user.
     * This field must be a valid email format.
     */
    @Email(message = "Enter a valid email")
    private String email;

}
