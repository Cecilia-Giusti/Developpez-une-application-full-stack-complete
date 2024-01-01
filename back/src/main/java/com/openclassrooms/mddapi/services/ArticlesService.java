package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exceptions.NoArticlesFoundException;
import com.openclassrooms.mddapi.exceptions.NoSubscribedThemesException;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.paylod.request.ArticleRequest;
import com.openclassrooms.mddapi.paylod.response.ArticleResponse;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticlesService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    public List<Article> getArticlesForThemesIdr(List<Integer> themeIds) {
        return articleRepository.findByThemeIdIn(themeIds);
    }

    public List<ArticleResponse> getArticlesForCurrentUser(Integer userId) {
        List<Integer> themeIds = subscriptionRepository.findThemeIdsByUserId(userId);

        if (themeIds.isEmpty()) {
            throw new NoSubscribedThemesException("User is not subscribed to any themes");
        }

        List<Article> articles = articleRepository.findByThemeIdIn(themeIds);
        if (articles.isEmpty()) {
            throw new NoArticlesFoundException("No articles found for the subscribed themes");
        }

        return articles.stream()
                .map(ArticleResponse::new)
                .collect(Collectors.toList());
    }


    public void createArticle(ArticleRequest articleRequest, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        Theme theme = themeRepository.findById(articleRequest.getThemeId())
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with ID: " + articleRequest.getThemeId()));

        Article article = new Article();
        article.setUserId(user.getId());
        article.setThemeId(theme.getId());
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setCreatedAt(LocalDateTime.now());

        Article savedArticle = articleRepository.save(article);

        new ArticleResponse(savedArticle);
    }

    public ArticleResponse getArticleById(Integer articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + articleId));

        return new ArticleResponse(article);
    }

}
