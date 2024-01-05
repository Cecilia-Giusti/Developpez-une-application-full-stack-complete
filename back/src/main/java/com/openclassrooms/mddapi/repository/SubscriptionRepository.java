package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

/**
 * SubscriptionRepository interface for handling data persistence operations related to subscriptions.
 */
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    /**
     * Retrieves all subscriptions for a given user.
     * Finds and returns a list of Subscription entities where the 'userId' matches the provided user ID.
     *
     * @param userId The ID of the user whose subscriptions are being retrieved.
     * @return A list of Subscription entities for the specified user.
     */
    List<Subscription> findByUserId(Integer userId);

    /**
     * Retrieves theme IDs for a specific user's subscriptions.
     * This custom query fetches the theme IDs from Subscription entities where the 'userId' matches the provided user ID.
     *
     * @param userId The ID of the user whose theme subscription IDs are being retrieved.
     * @return A list of theme IDs to which the user is subscribed.
     */
    @Query("SELECT s.themeId FROM Subscription s WHERE s.userId = :userId")
    List<Integer> findThemeIdsByUserId(Integer userId);

    /**
     * Finds a specific subscription by user ID and theme ID.
     * This method finds and returns an Optional of Subscription where both 'userId' and 'themeId' match the provided IDs.
     *
     * @param userId The ID of the user.
     * @param themeId The ID of the theme.
     * @return An Optional containing the Subscription if found, otherwise an empty Optional.
     */
    Optional<Subscription> findByUserIdAndThemeId(Integer userId, Integer themeId);

    /**
     * Checks if a subscription exists for a given user and theme.
     * Returns true if a subscription exists where both 'userId' and 'themeId' match the provided IDs, false otherwise.
     *
     * @param userId The ID of the user.
     * @param themeId The ID of the theme.
     * @return True if the subscription exists, false otherwise.
     */
    boolean existsByUserIdAndThemeId(Integer userId, Integer themeId);
}
