package com.openclassrooms.mddapi.paylod.request;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


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
