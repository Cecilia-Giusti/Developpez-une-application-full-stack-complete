package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.request.UserUpdate;
import com.openclassrooms.mddapi.exceptions.AccountException;
import com.openclassrooms.mddapi.exceptions.UserException;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.dto.response.UserResponse;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Optional;

/**
 * Service class for handling user-related operations.
 * This service manages user information retrieval, updates, and conversions
 * to DTOs (Data Transfer Objects) for consistent response structures.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private com.openclassrooms.mddapi.security.jwt.JwtUtils jwtUtils;


    /**
     * Retrieves the current authenticated user's information.
     * This method uses the authentication token to identify the user and retrieve their details.
     *
     * @return A User object containing the authenticated user's details.
     * @throws AccountException If the user associated with the token is not found.
     */
    public User getUserInfo() {
        Optional<User> userOptional = authService.getUserByEmailFromToken();

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new AccountException("bad token");
        }
    }

    /**
     * Updates the user's profile information based on the provided request data.
     * This method allows for updating the username and/or email of the user.
     *
     * @param userUpdate The request containing the new user details.
     * @return The updated User object.
     * @throws UserException If the email is already in use by another account.
     */
    public User updateUser(UserUpdate userUpdate) throws UserException {
        User oldUser = getUserInfo();

        if (userUpdate.getUsername() != null && !userUpdate.getUsername().equals(oldUser.getUsername())) {
            if (userRepository.existsByUsername(userUpdate.getUsername())) {
                throw new UserException("Username already in use");
            }
            oldUser.setUsername(userUpdate.getUsername());
        }

        if (userUpdate.getEmail() != null && !userUpdate.getEmail().equals(oldUser.getEmail())) {
            if (userRepository.existsByEmail(userUpdate.getEmail())) {
                throw new UserException("Email already in use");
            }
            oldUser.setEmail(userUpdate.getEmail());
        }

        if (userUpdate.getPassword() != null ) {
            oldUser.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
        }

        userRepository.save(oldUser);
        return oldUser;
    }

    /**
     * Converts a User entity to a UserResponse DTO.
     * This method is used to prepare the User object for API responses, ensuring
     * a consistent data structure is sent to the client.
     *
     * @param user The User entity to be converted.
     * @return A UserResponse DTO with user data.
     */
    public UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());

        if (user.getCreatedAt() != null) {
            userResponse.setCreated_at(user.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        if (user.getUpdatedAt() != null) {
            userResponse.setUpdated_at(user.getUpdatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        } else {
            userResponse.setUpdated_at(null);
        }
        return userResponse;
    }

}
