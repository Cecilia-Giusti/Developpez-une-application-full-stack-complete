package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * CommentRepository interface for handling data persistence operations for Comment entities.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * Retrieves all comments associated with a specific article.
     * This method finds and returns a list of comments where the 'articleId' matches the provided article ID.
     *
     * @param articleId The ID of the article for which comments are to be retrieved.
     * @return A list of comments linked to the specified article.
     */
    List<Comment> findByArticleId(Integer articleId);
}
