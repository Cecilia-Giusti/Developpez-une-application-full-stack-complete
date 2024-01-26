package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exceptions.NoSubscribedThemesException;
import com.openclassrooms.mddapi.models.Subscription;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for managing subscriptions.
 * This service provides methods to handle subscription-related operations like creating,
 * deleting, and fetching theme IDs for a given user.
 */
@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    /**
     * Retrieves theme IDs for the current user.
     * This method fetches the list of theme IDs that the given user is subscribed to.
     * If the user is not subscribed to any themes, it throws a NoSubscribedThemesException.
     *
     * @param userEmail The email of the user whose theme IDs are to be retrieved.
     * @return A list of Integer representing theme IDs.
     */
    public List<Integer> getThemeIdsForCurrentUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        List<Integer> themeId = subscriptionRepository.findThemeIdsByUserId(user.getId());
        if(themeId.isEmpty()){
            throw new NoSubscribedThemesException("User is not subscribed to any themes");
        }
        return themeId;
    }

    /**
     * Creates a subscription for the given user and theme.
     * This method adds a new subscription entry to the database linking the user to a theme.
     *
     * @param userEmail The email of the user who is subscribing.
     * @param themeId The ID of the theme to which the user is subscribing.
     */
    public void createSubscription(String userEmail, Integer themeId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with ID: " + themeId));

        Subscription subscription = new Subscription();
        subscription.setUserId(user.getId());
        subscription.setThemeId(theme.getId());
        subscription.setCreatedAt(LocalDateTime.now());

        subscriptionRepository.save(subscription);
    }

    /**
     * Deletes a subscription for the given user and theme.
     * This method removes a subscription entry from the database for the specified user and theme.
     *
     * @param userEmail The email of the user whose subscription is to be deleted.
     * @param themeId The ID of the theme from which the user is unsubscribing.
     */
    public void deleteSubscription(String userEmail, Integer themeId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        Subscription subscription = subscriptionRepository.findByUserIdAndThemeId(user.getId(), themeId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        subscriptionRepository.delete(subscription);
    }

    public List<Theme> getThemesForCurrentUser(String userEmail) {
        List<Integer> themes = this.getThemeIdsForCurrentUser(userEmail);

        List<Theme> subscribedThemes =  themeRepository.findAllById(themes);

        if (subscribedThemes.isEmpty()) {
            throw new NoSubscribedThemesException("User is not subscribed to any themes");
        }
        return subscribedThemes;
    }

}
