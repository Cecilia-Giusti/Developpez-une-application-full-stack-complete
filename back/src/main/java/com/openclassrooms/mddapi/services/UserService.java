package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exceptions.AccountException;
import com.openclassrooms.mddapi.exceptions.UserException;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.Dto.request.UserRequest;
import com.openclassrooms.mddapi.Dto.response.UserResponse;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Optional;

/**
 * Service class responsible for handling user-related operations.
 * This service offers methods for user authentication, registration,
 * and information retrieval using the associated repositories
 * and utility services.
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
     * Retrieves the information of the user based on the authentication token.
     *
     * @return The UserInfoModel object of the authenticated user.
     * @throws AccountException if the user associated with the token is not found.
     */
    public User getUserInfo() {
        Optional<User> userOptional = authService.getUserByEmailFromToken();

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new AccountException("bad token");
        }
    }


    public User updateUser(UserRequest userRequest) throws UserException {
        User oldUser = getUserInfo();

        if (userRequest.getUsername() != null && !userRequest.getUsername().equals(oldUser.getUsername())) {
            oldUser.setUsername(userRequest.getUsername());
        }

        if (userRequest.getEmail() != null && !userRequest.getEmail().equals(oldUser.getEmail())) {
            if (userRepository.existsByEmail(userRequest.getEmail())) {
                throw new UserException("Email already in use");
            }
            oldUser.setEmail(userRequest.getEmail());
        }

        userRepository.save(oldUser);
        return oldUser;
    }

    public UserResponse convertToUserResponse(User user) {
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
        return userResponse;
    }

}
