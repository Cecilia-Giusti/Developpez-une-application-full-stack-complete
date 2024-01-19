package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * UserRepository interface for handling data persistence operations related to users.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a user by their email address.
     * This method attempts to find a User entity where the email address matches the provided email.
     *
     * @param email The email address of the user to find.
     * @return An Optional containing the User entity if found, or an empty Optional if not found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user with the given email exists.
     * This method determines whether a User entity exists with the specified email address.
     *
     * @param email The email address to check for existence.
     * @return True if a User entity with the given email exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Retrieves a user by their ID.
     * This method finds and returns the User entity where the ID matches the provided ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User entity with the specified ID.
     */
    User getById(Integer id);

    /**
     * Retrieves a user by their ID.
     * This request finds and returns the Username where the ID matches the provided ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The Username entity with the specified ID.
     */
    @Query("SELECT u.username FROM User u WHERE u.id = :id")
    String findUsernameById(Integer id);


}
