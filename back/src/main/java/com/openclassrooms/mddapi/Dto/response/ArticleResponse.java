package com.openclassrooms.mddapi.Dto.response;

import com.openclassrooms.mddapi.models.Article;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for article responses.
 */
@Getter
@Setter
public class ArticleResponse {

    private Integer id;
    private Integer userId;
    private Integer themeId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Constructs an ArticleResponse with the specified details.
     *
     * @param id        The unique identifier of the article.
     * @param userId    The identifier of the user who created the article.
     * @param themeId   The identifier of the theme associated with the article.
     * @param title     The title of the article.
     * @param content   The content of the article.
     * @param createdAt The date and time when the article was created.
     * @param updatedAt The date and time when the article was last updated.
     */
    public ArticleResponse(Integer id, Integer userId, Integer themeId, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.themeId = themeId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Constructs an ArticleResponse from an Article model.
     * This constructor maps the fields of the Article model to the fields of the ArticleResponse DTO.
     *
     * @param article The Article model to be transformed into an ArticleResponse.
     */
    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.userId = article.getUserId();
        this.themeId = article.getThemeId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }

}
