package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.Dto.request.ArticleRequest;
import com.openclassrooms.mddapi.Dto.response.ArticleResponse;
import com.openclassrooms.mddapi.Dto.response.MessageResponse;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.ArticlesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "articles")
public class ArticlesController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticlesService articlesService;

    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getArticlesForCurrentUser(Authentication authentication) {
        String userEmail = authentication.getName();

        List<ArticleResponse> articleResponses = articlesService.getArticlesForCurrentUser(userEmail);
        return ResponseEntity.ok(articleResponses);
    }


    @PostMapping
    public ResponseEntity<MessageResponse> createArticle(@Valid @RequestBody ArticleRequest articleRequest, Authentication authentication) {
        String userEmail = authentication.getName();

        articlesService.createArticle(articleRequest, userEmail);

        MessageResponse response = new MessageResponse("Your article are edited");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> getArticleById(@PathVariable Integer articleId) {
        ArticleResponse articleResponse = articlesService.getArticleById(articleId);
        return ResponseEntity.ok(articleResponse);
    }
}
