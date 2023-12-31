package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Comment> getCommentsByArticleId(Integer articleId, Integer userId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + articleId));

        boolean isSubscribed = subscriptionRepository.existsByUserIdAndThemeId(userId, article.getThemeId());
        if (!isSubscribed) {
            throw new AccessDeniedException("User is not subscribed to the theme of this article");
        }

        return commentRepository.findByArticleId(articleId);
    }

}
