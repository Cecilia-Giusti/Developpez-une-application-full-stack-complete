package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * ThemeRepository interface for handling data persistence operations related to themes.
 */
public interface ThemeRepository extends JpaRepository<Theme, Integer> {

    /**
     * Finds a theme by its ID.
     * This method attempts to find a Theme entity where the ID matches the provided ID.
     *
     * @param id The ID of the theme to find.
     * @return An Optional containing the Theme entity if found, or an empty Optional if not found.
     */
    Optional<Theme> findById(Integer id);

    @Query("SELECT u.name FROM Theme u WHERE u.id = :id")
    String findNameById(Integer id);
}
