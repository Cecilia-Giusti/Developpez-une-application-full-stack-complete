package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.request.LoginRequest;
import com.openclassrooms.mddapi.dto.request.RegisterRequest;
import com.openclassrooms.mddapi.dto.response.JwtResponse;
import com.openclassrooms.mddapi.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Controller for handling authentication operations such as user registration and login.
 */
@RestController
@Slf4j
@RequestMapping(value = "auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Registers a new user in the system.
     *
     * @param registerRequest The user registration details.
     * @return A ResponseEntity containing the JWT token for the newly registered user.
     */
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> createUser(@Valid @RequestBody RegisterRequest registerRequest) {
        String token = authService.registerUser(registerRequest);
        JwtResponse jwtResponse = new JwtResponse(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtResponse);
    }

    /**
     * Authenticates a user and issues a JWT token.
     *
     * @param loginRequest The user login credentials.
     * @return A ResponseEntity containing the JWT token for the authenticated user.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authService.loginUser(loginRequest);
        JwtResponse jwtResponse = new JwtResponse(token);
        return ResponseEntity.ok(jwtResponse);
    }
}


