package com.openclassrooms.mddapi.dto.response;

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
    private String author;
    private String theme;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Constructs an ArticleResponse with the specified details.
     *
     * @param article   The article with all fields .
     * @param author    The name of the user who created the article.
     */
    public ArticleResponse(Article article, String author,String theme) {
        this.id = article.getId();
        this.author = author;
        this.theme = theme;
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }
}
