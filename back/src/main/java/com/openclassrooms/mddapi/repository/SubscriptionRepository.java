package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    List<Subscription> findByUserId(Integer userId);

    @Query("SELECT s.themeId FROM Subscription s WHERE s.userId = :userId")
    List<Integer> findThemeIdsByUserId(Integer userId);

    Optional<Subscription> findByUserIdAndThemeId(Integer userId, Integer themeId);
}
