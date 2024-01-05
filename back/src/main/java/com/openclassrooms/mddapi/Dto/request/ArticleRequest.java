package com.openclassrooms.mddapi.Dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Data Transfer Object for article creation requests.
 */
@Data
public class ArticleRequest {

    /**
     * The ID of the theme to which the article belongs.
     */
    @NotNull(message = "Theme ID cannot be null")
    private Integer themeId;

    /**
     * The title of the article.
     */
    @NotBlank(message = "Title cannot be blank")
    private String title;

    /**
     * The content of the article.
     */
    @NotBlank(message = "Content cannot be blank")
    private String content;
}

