package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exceptions.AccountException;
import com.openclassrooms.mddapi.exceptions.NoArticlesFoundException;
import com.openclassrooms.mddapi.exceptions.NoSubscribedThemesException;
import com.openclassrooms.mddapi.exceptions.UserException;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.dto.request.ArticleRequest;
import com.openclassrooms.mddapi.dto.response.ArticleResponse;
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
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing articles.
 * This class handles operations related to articles, such as creating articles, fetching articles
 * by themes, and retrieving articles for the current user.
 */
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

    /**
     * Retrieves articles based on a list of theme IDs.
     *
     * @param themeIds List of theme IDs.
     * @return List of articles associated with the given theme IDs.
     */
    public List<Article> getArticlesForThemesIdr(List<Integer> themeIds) {
        return articleRepository.findByThemeIdIn(themeIds);
    }

    /**
     * Retrieves articles subscribed to by the current user.
     *
     * @param userEmail Email of the current user.
     * @return List of ArticleResponse objects for the subscribed themes.
     * @throws UsernameNotFoundException   if the user is not found by email.
     * @throws NoSubscribedThemesException if the user has no subscribed themes.
     * @throws NoArticlesFoundException    if no articles are found for the subscribed themes.
     */
    public List<ArticleResponse> getArticlesForCurrentUser(String userEmail) {

        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if(userOptional.isEmpty()) {
            throw new AccountException("User email is not founded");
        }
        User user = userOptional.get();

        List<Integer> themeIds = subscriptionRepository.findThemeIdsByUserId(user.getId());

        if (themeIds.isEmpty()) {
            throw new NoSubscribedThemesException("User is not subscribed to any themes");
        }

        List<Article> articles = articleRepository.findByThemeIdIn(themeIds);
        if (articles.isEmpty()) {
            throw new NoArticlesFoundException("No articles found for the subscribed themes");
        }

        return articles.stream()
                .map(article -> {
                    String author = userRepository.findUsernameById(article.getUserId());
                    String theme = themeRepository.findNameById(article.getThemeId());
                    return new ArticleResponse(article, author, theme);
                })
                .collect(Collectors.toList());
    }

    /**
     * Creates a new article based on the provided request and user's email.
     *
     * @param articleRequest The request containing article data.
     * @param userEmail      Email of the user creating the article.
     * @throws UsernameNotFoundException if the user is not found.
     * @throws EntityNotFoundException   if the theme is not found.
     */
    public void createArticle(ArticleRequest articleRequest, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email"));

        Theme theme = themeRepository.findById(articleRequest.getThemeId())
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with ID"));

        Article article = new Article();
        article.setUserId(user.getId());
        article.setThemeId(theme.getId());
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setCreatedAt(LocalDateTime.now());

        articleRepository.save(article);
    }

    /**
     * Retrieves a single article by its ID.
     *
     * @param articleId The ID of the article to retrieve.
     * @return An ArticleResponse object representing the article.
     * @throws EntityNotFoundException if the article is not found.
     */
    public ArticleResponse getArticleById(Integer articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID"));
        String author = userRepository.findUsernameById(article.getUserId());
        String theme = themeRepository.findNameById(article.getThemeId());
        return new ArticleResponse(article, author, theme);
    }

}
