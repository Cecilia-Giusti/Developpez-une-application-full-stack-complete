package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.dto.request.CommentRequest;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for managing comments.
 * This class handles operations related to comments, such as retrieving and adding comments to articles.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all comments for a given article ID, verifying user subscription to the article's theme.
     *
     * @param articleId  ID of the article for which comments are to be retrieved.
     * @param userEmail  Email of the current user.
     * @return List of Comment objects.
     * @throws UsernameNotFoundException if the user is not found.
     * @throws EntityNotFoundException if the article is not found.
     * @throws AccessDeniedException if the user is not subscribed to the theme of the article.
     */
    public List<Comment> getCommentsByArticleId(Integer articleId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + articleId));

        boolean isSubscribed = subscriptionRepository.existsByUserIdAndThemeId(user.getId(), article.getThemeId());
        if (!isSubscribed) {
            throw new AccessDeniedException("User is not subscribed to the theme of this article");
        }

        return commentRepository.findByArticleId(articleId);
    }

    /**
     * Adds a new comment to an article, checking user subscription to the article's theme.
     *
     * @param articleId      ID of the article to which the comment is to be added.
     * @param commentRequest Request object containing the content of the comment.
     * @param userEmail      Email of the user adding the comment.
     * @throws UsernameNotFoundException if the user is not found.
     * @throws EntityNotFoundException if the article is not found.
     * @throws AccessDeniedException if the user is not subscribed to the theme of the article.
     */
    public void addComment(Integer articleId, CommentRequest commentRequest, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + articleId));

        boolean isSubscribed = subscriptionRepository.existsByUserIdAndThemeId(user.getId(), article.getThemeId());
        if (!isSubscribed) {
            throw new AccessDeniedException("User is not subscribed to the theme of this article");
        }

        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setUserId(user.getId());
        comment.setContent(commentRequest.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        commentRepository.save(comment);
    }

}
