package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.paylod.request.UserRequest;
import com.openclassrooms.mddapi.paylod.response.MessageResponse;
import com.openclassrooms.mddapi.paylod.response.UserResponse;
import com.openclassrooms.mddapi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.time.ZoneId;


/**
 * Controller handling authentication operations such as user registration and login.
 */
@RestController
@Slf4j
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves the details of the currently authenticated user.
     * @return A ResponseEntity containing the UserResponse object of the authenticated user.
     */
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserInfo(HttpServletRequest request) {
        User user = userService.getUserInfo();

        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());

        if (user.getCreatedAt() != null) {
            userResponse.setCreated_at(user.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        if (user.getUpdatedAt() != null) {
            userResponse.setUpdated_at(user.getUpdatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        } else {
            userResponse.setUpdated_at(null);
        }
        return ResponseEntity.ok(userResponse);
    }

    //Questions
    // Vaut il mieux retourner le profil mis à jour
    // ou il est préférable en front de faire un get après le put ?
    @PutMapping("/profile")
    public ResponseEntity<MessageResponse> updateUser(@RequestBody @Valid UserRequest userRequest) {
        User user = userService.updateUser(userRequest);

        MessageResponse response = new MessageResponse("Profile updated !");
        return ResponseEntity.ok(response);
    }
}

