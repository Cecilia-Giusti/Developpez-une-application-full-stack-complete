package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.request.UserUpdate;
import com.openclassrooms.mddapi.dto.response.ProfileUpdateResponse;
import com.openclassrooms.mddapi.models.User;
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

    @Autowired
    private com.openclassrooms.mddapi.security.jwt.JwtUtils jwtUtils;

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
     * @param userUpdate The user's updated profile data.
     * @return A ResponseEntity containing a ProfilUpdateResponse when successfully.
     */
    @PutMapping("/profile")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserUpdate userUpdate) {

        User user = userService.updateUser(userUpdate);

            String token = jwtUtils.generateToken(user.getEmail());
            ProfileUpdateResponse profileUpdateResponse = new ProfileUpdateResponse(token, "Profile updated");

            return ResponseEntity.ok(profileUpdateResponse);

    }

}


