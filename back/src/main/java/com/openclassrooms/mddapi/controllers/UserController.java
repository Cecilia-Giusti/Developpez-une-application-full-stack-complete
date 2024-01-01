package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.Dto.request.UserRequest;
import com.openclassrooms.mddapi.Dto.response.MessageResponse;
import com.openclassrooms.mddapi.Dto.response.UserResponse;
import com.openclassrooms.mddapi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
    public ResponseEntity<UserResponse> getUserInfo() {
        User user = userService.getUserInfo();
        UserResponse userResponse = userService.convertToUserResponse(user);
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

