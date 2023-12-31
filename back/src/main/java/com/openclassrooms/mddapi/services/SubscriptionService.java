package com.openclassrooms.mddapi.services;


import com.openclassrooms.mddapi.dto.SubscriptionDto;
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
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    public List<Integer> getThemeIdsForCurrentUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        return subscriptionRepository.findThemeIdsByUserId(user.getId());
    }

    public void createSubscription(Integer userId, Integer themeId) {
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with ID: " + themeId));

        Subscription subscription = new Subscription();
        subscription.setUserId(userId);
        subscription.setThemeId(theme.getId());
        subscription.setCreatedAt(LocalDateTime.now());

        subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(Integer userId, Integer themeId) {
        Subscription subscription = subscriptionRepository.findByUserIdAndThemeId(userId, themeId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        subscriptionRepository.delete(subscription);
    }


}
