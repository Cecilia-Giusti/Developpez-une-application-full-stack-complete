package com.openclassrooms.mddapi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Data Transfer Object for comment creation requests.
 */
@Data
public class CommentRequest {

    /**
     * The content of the comment.
     */
    @NotBlank(message = "Content cannot be blank")
    private String content;
}
