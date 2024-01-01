package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.paylod.request.LoginRequest;
import com.openclassrooms.mddapi.paylod.request.RegisterRequest;
import com.openclassrooms.mddapi.paylod.response.JwtResponse;
import com.openclassrooms.mddapi.services.AuthService;
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
@RequestMapping(value = "auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    /**
     * Registers a new user and returns an authentication token.
     *
     * @param registerRequest The details of the user to register.
     * @return A ResponseEntity containing the authentication token for the newly registered user.
     */
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> createUser(@RequestBody @Valid RegisterRequest registerRequest) {
        String token = authService.registerUser(registerRequest);
        JwtResponse jwtResponse = new JwtResponse(token);
        return ResponseEntity.ok(jwtResponse);
    }

    /**
     * Authenticates a user and returns an authentication token.
     *
     * @param loginRequest The details of the user for logging in.
     * @return A ResponseEntity containing the authentication token for the logged-in user.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        String token = authService.loginUser(loginRequest);
        JwtResponse jwtResponse = new JwtResponse(token);
        return ResponseEntity.ok(jwtResponse);
    }
}

