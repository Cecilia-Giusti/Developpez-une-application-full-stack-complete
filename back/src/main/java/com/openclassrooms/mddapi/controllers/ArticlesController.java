package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.request.ArticleRequest;
import com.openclassrooms.mddapi.dto.response.ArticleResponse;
import com.openclassrooms.mddapi.dto.response.MessageResponse;
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

/**
 * Controller for handling requests related to articles.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping(value = "articles")
public class ArticlesController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticlesService articlesService;

    /**
     * Retrieves a list of article responses for the current authenticated user.
     *
     * @param authentication The authentication object that contains details about the current user.
     * @return A ResponseEntity containing a list of ArticleResponse.
     */
    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getArticlesForCurrentUser(Authentication authentication) {
        String userEmail = authentication.getName();
        List<ArticleResponse> articleResponses = articlesService.getArticlesForCurrentUser(userEmail);
        return ResponseEntity.ok(articleResponses);
    }

    /**
     * Creates a new article based on the provided article request and the current authenticated user.
     *
     * @param articleRequest The request body containing article data.
     * @param authentication The authentication object that contains details about the current user.
     * @return A ResponseEntity with a message response indicating the status.
     */
    @PostMapping
    public ResponseEntity<MessageResponse> createArticle(@Valid @RequestBody ArticleRequest articleRequest, Authentication authentication) {
        String userEmail = authentication.getName();
        articlesService.createArticle(articleRequest, userEmail);
        MessageResponse response = new MessageResponse("Your article has been created.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Retrieves an article response by its ID.
     *
     * @param articleId The ID of the article to retrieve.
     * @return A ResponseEntity containing the ArticleResponse.
     */
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> getArticleById(@PathVariable Integer articleId) {
        ArticleResponse articleResponse = articlesService.getArticleById(articleId);
        return ResponseEntity.ok(articleResponse);
    }
}
