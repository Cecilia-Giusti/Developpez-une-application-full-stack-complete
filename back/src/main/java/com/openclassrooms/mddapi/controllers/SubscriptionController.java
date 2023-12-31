package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.models.Subscription;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.paylod.request.RegisterRequest;
import com.openclassrooms.mddapi.paylod.request.SubscriptionRequest;
import com.openclassrooms.mddapi.paylod.response.MessageResponse;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
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
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        List<Integer> themeIds = subscriptionService.getThemeIdsForCurrentUser(userEmail);
        return ResponseEntity.ok(themeIds);
    }

    @PostMapping("/{themeId}")
    public ResponseEntity<MessageResponse> createSubscription(@PathVariable Integer themeId, Authentication authentication) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        subscriptionService.createSubscription(user.getId(), themeId);

        MessageResponse response = new MessageResponse("You are subscribed to this theme");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{themeId}")
    public ResponseEntity<MessageResponse> deleteSubscription(@PathVariable Integer themeId,Authentication authentication) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        subscriptionService.deleteSubscription(user.getId(), themeId);

        MessageResponse response = new MessageResponse("You have unsubscribed from this theme");
        return ResponseEntity.ok(response);
    }
}
