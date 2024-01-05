package com.openclassrooms.mddapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents an article entity in the database.
 */
@Getter
@Setter
@Entity
@Table(name = "articles")
public class Article {

    /**
     * Unique identifier of the article.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * User ID of the author who created the article.
     */
    @Setter
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    /**
     * Theme ID to which the article is associated.
     */
    @Setter
    @Column(name = "theme_id", nullable = false)
    private Integer themeId;

    /**
     * Title of the article.
     */
    @Setter
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Content of the article.
     */
    @Setter
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    /**
     * Date and time when the article was created.
     */
    @Setter
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
