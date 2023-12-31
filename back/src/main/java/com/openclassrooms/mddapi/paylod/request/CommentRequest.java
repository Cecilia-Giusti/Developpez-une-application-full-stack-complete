package com.openclassrooms.mddapi.paylod.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentRequest {

    @NotBlank(message = "Content cannot be blank")
    private String content;
}
