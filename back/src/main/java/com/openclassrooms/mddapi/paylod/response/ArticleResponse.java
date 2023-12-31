package com.openclassrooms.mddapi.paylod.response;

import com.openclassrooms.mddapi.models.Article;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    public ArticleResponse(Integer id, Integer userId, Integer themeId, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.themeId = themeId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.userId = article.getUserId();
        this.themeId = article.getThemeId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }

}
