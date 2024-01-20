package com.openclassrooms.mddapi.dto.response;

import com.openclassrooms.mddapi.models.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * Data Transfer Object (DTO) for comment responses.
 */
@Getter
@Setter
public class CommentResponse {

    private Integer id;
    private Integer articleId;
    private String author;
    private String content;
    private LocalDateTime createdAt;

    /**
     * Constructs an CommentResponse with the specified details.
     *
     * @param comment   The comment with all fields .
     * @param author    The name of the user who created the comment.
     */
    public CommentResponse(Comment comment, String author) {
        this.id = comment.getId();
        this.author = author;
        this.content = comment.getContent();
    }
}
