package com.openclassrooms.mddapi.models;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a theme entity in the database.
 */
@Getter
@Entity
@Table(name = "themes")
public class Theme {

    /**
     * Unique identifier of the theme.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Name of the theme.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Description of the theme.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * Date and time when the theme was created.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}
