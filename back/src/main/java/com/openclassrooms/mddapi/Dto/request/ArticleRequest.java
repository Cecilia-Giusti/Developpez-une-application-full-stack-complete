package com.openclassrooms.mddapi.Dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class ArticleRequest {

    @NotNull(message = "Theme ID cannot be null")
    private Integer themeId;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    private String content;

}
