package com.openclassrooms.mddapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a comment entity in the database.
 */
@Setter
@Getter
@Entity
@Table(name = "comments")
public class Comment {

    /**
     * Unique identifier of the comment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The ID of the article to which this comment is associated.
     */
    @Column(name = "article_id", nullable = false)
    private Integer articleId;

    /**
     * User ID of the author who posted the comment.
     */
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    /**
     * The actual content of the comment.
     */
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    /**
     * Date and time when the comment was created.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
