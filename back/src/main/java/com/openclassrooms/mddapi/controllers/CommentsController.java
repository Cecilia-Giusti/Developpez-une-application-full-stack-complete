package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.Dto.request.CommentRequest;
import com.openclassrooms.mddapi.Dto.response.MessageResponse;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.CommentService;
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
@RequestMapping("articles")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{articleId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByArticleId(@PathVariable Integer articleId, Authentication authentication) {
        String userEmail = authentication.getName();

        List<Comment> comments = commentService.getCommentsByArticleId(articleId, userEmail);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{articleId}/comments")
    public ResponseEntity<?> addCommentToArticle(@PathVariable Integer articleId,
                                                 @Valid @RequestBody CommentRequest commentRequest,
                                                 Authentication authentication) {
        String userEmail = authentication.getName();
        commentService.addComment(articleId, commentRequest, userEmail);

        MessageResponse response = new MessageResponse("Comment added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
