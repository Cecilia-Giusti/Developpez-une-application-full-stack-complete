package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * ArticleRepository interface for handling data persistence operations for Article entities.
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    /**
     * Retrieves a list of articles based on a collection of theme IDs.
     * This method finds all articles where their theme ID matches any of the IDs in the provided list.
     *
     * @param themeIds A list of theme IDs to filter articles.
     * @return A list of articles that belong to any of the specified themes.
     */
    List<Article> findByThemeIdIn(List<Integer> themeIds);
}
