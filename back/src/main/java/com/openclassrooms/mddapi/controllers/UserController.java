package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.dto.request.UserRequest;
import com.openclassrooms.mddapi.dto.response.MessageResponse;
import com.openclassrooms.mddapi.dto.response.UserResponse;
import com.openclassrooms.mddapi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Controller for managing user profiles and details.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves the profile information of the currently authenticated user.
     *
     * @return A ResponseEntity containing the UserResponse with user profile details.
     */
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserInfo() {
        User user = userService.getUserInfo();
        UserResponse userResponse = userService.convertToUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    /**
     * Updates the profile information of the currently authenticated user.
     *
     * @param userRequest The user's updated profile data.
     * @return A ResponseEntity containing a MessageResponse indicating a successful update.
     */
    @PutMapping("/profile")
    public ResponseEntity<MessageResponse> updateUser(@RequestBody @Valid UserRequest userRequest) {
        User user = userService.updateUser(userRequest);

        MessageResponse response = new MessageResponse("Profile updated !");
        return ResponseEntity.ok(response);
    }
}


