package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.payload.request.CommentRequest;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.services.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Controller for handling comment-related operations on articles.
 */
@RestController
@Slf4j
@RequestMapping("articles")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    /**
     * Retrieves comments for a specific article identified by its ID.
     *
     * @param articleId The ID of the article for which comments are requested.
     * @param authentication The authentication context containing the user's details.
     * @return A ResponseEntity containing a list of comments.
     */
    @GetMapping("/{articleId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByArticleId(@PathVariable Integer articleId, Authentication authentication) {
        String userEmail = authentication.getName();
        List<Comment> comments = commentService.getCommentsByArticleId(articleId, userEmail);
        return ResponseEntity.ok(comments);
    }

    /**
     * Adds a comment to a specific article identified by its ID.
     *
     * @param articleId The ID of the article to which the comment will be added.
     * @param commentRequest The request payload containing the comment details.
     * @param authentication The authentication context containing the user's details.
     * @return A ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/{articleId}/comments")
    public ResponseEntity<MessageResponse> addCommentToArticle(@PathVariable Integer articleId,
                                                               @Valid @RequestBody CommentRequest commentRequest,
                                                               Authentication authentication) {
        String userEmail = authentication.getName();
        commentService.addComment(articleId, commentRequest, userEmail);
        MessageResponse response = new MessageResponse("Comment added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

