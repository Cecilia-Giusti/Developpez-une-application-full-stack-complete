package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exceptions.AccountException;
import com.openclassrooms.mddapi.exceptions.LoginException;
import com.openclassrooms.mddapi.exceptions.RegisterException;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.paylod.request.LoginRequest;
import com.openclassrooms.mddapi.paylod.request.RegisterRequest;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class responsible for handling user-related operations.
 * This service offers methods for user authentication, registration,
 * and information retrieval using the associated repositories
 * and utility services.
 */
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private com.openclassrooms.mddapi.security.jwt.JwtUtils jwtUtils;

    /**
     * Retrieves user information using the email from the currently authenticated token.
     *
     * @return An optional containing the user information if found, empty otherwise.
     */
    public Optional<User> getUserByEmailFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String email = (String) authentication.getPrincipal();
            return userRepository.findByEmail(email);
        }
        return Optional.empty();
    }

    /**
     * Registers a new user with the given details.
     *
     * @param registerRequest The user's registration details.
     * @return A JWT token generated for the registered user.
     * @throws RegisterException if registration fails, typically due to existing email.
     */
    public String registerUser(RegisterRequest registerRequest) {
        Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new RegisterException("The email address is already in use. Please try different credentials");
        }
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());

        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(newUser);

        return jwtUtils.generateToken(newUser.getEmail());
    }

    /**
     * Authenticates a user based on the provided login details.
     *
     * @param loginRequest The user's login details.
     * @return A JWT token for the authenticated user.
     * @throws LoginException if authentication fails due to incorrect credentials.
     */
    public String loginUser(LoginRequest loginRequest) {

        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isPresent()) {

            User user = userOptional.get();

            if  (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

                return jwtUtils.generateToken(user.getEmail());
            } else {
                throw new LoginException(" Invalid credentials. Please check your email and password and try again");
            }
        } else {
            throw new LoginException("Invalid credentials. Please check your email and password and try again");
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The UserInfoModel object for the specified user ID.
     * @throws AccountException if a user with the given ID is not found.
     */
    public User getUserById(int id) {
        Optional<User> userId = userRepository.findById(id);

        return userId.orElseThrow(() -> new AccountException("User not found"));
    }

}
