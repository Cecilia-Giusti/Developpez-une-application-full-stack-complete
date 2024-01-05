package com.openclassrooms.mddapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a subscription entity in the database.
 */
@Setter
@Getter
@Entity
@Table(name = "subscriptions")
public class Subscription {

    /**
     * Unique identifier of the subscription.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * User ID of the subscriber.
     */
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    /**
     * Theme ID to which the user has subscribed.
     */
    @Column(name = "theme_id", nullable = false)
    private Integer themeId;

    /**
     * Date and time when the subscription was created.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Default constructor for JPA.
     */
    public Subscription() {
    }
}
