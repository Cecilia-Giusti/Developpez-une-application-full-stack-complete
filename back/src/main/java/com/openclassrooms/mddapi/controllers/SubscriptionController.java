package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.Dto.response.MessageResponse;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Integer>> getSubscriptionsForCurrentUser(Authentication authentication) {
        String userEmail = authentication.getName();
        List<Integer> themeIds = subscriptionService.getThemeIdsForCurrentUser(userEmail);
        return ResponseEntity.ok(themeIds);
    }

    @PostMapping("/{themeId}")
    public ResponseEntity<MessageResponse> createSubscription(@PathVariable Integer themeId, Authentication authentication) {
        String userEmail = authentication.getName();

        subscriptionService.createSubscription(userEmail, themeId);

        MessageResponse response = new MessageResponse("You are subscribed to this theme");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{themeId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Integer themeId, Authentication authentication) {
        String userEmail = authentication.getName();

        subscriptionService.deleteSubscription(userEmail, themeId);

        return ResponseEntity.noContent().build();
    }
}
