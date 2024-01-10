package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.response.MessageResponse;
import com.openclassrooms.mddapi.services.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing user subscriptions to themes.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping(value = "subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    /**
     * Retrieves a list of theme IDs to which the current user is subscribed.
     *
     * @param authentication The security context of the authenticated user.
     * @return A ResponseEntity containing a list of subscribed theme IDs.
     */
    @GetMapping
    public ResponseEntity<List<Integer>> getSubscriptionsForCurrentUser(Authentication authentication) {
        String userEmail = authentication.getName();
        List<Integer> themeIds = subscriptionService.getThemeIdsForCurrentUser(userEmail);
        return ResponseEntity.ok(themeIds);
    }

    /**
     * Creates a new subscription to a theme for the current user.
     *
     * @param themeId The ID of the theme to which the user wants to subscribe.
     * @param authentication The security context of the authenticated user.
     * @return A ResponseEntity with a message indicating successful subscription.
     */
    @PostMapping("/{themeId}")
    public ResponseEntity<MessageResponse> createSubscription(@PathVariable Integer themeId, Authentication authentication) {
        String userEmail = authentication.getName();

        subscriptionService.createSubscription(userEmail, themeId);

        MessageResponse response = new MessageResponse("You are subscribed to this theme");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Deletes a subscription to a theme for the current user.
     * This endpoint removes the subscription of the authenticated user to the specified theme ID.
     *
     * @param themeId The ID of the theme from which the user wants to unsubscribe.
     * @param authentication The security context of the authenticated user.
     * @return A ResponseEntity with HTTP 204 No Content status, indicating successful unsubscription.
     */
    @DeleteMapping("/{themeId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Integer themeId, Authentication authentication) {
        String userEmail = authentication.getName();

        subscriptionService.deleteSubscription(userEmail, themeId);

        return ResponseEntity.noContent().build();
    }
}
