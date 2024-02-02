import { Component, Input } from '@angular/core';
import { Article } from '../core/models/article.model';
import { Router } from '@angular/router';
import { truncContent } from '../utils/truncContent';

/**
 * @component
 * @description
 * Component for displaying a summary of an article in a card format. It provides functionality
 * to navigate to the detailed article view and to truncate the content of the article to a specified
 * number of words.
 *  * @selector app-article-card
 */
@Component({
  selector: 'app-articleCard',
  templateUrl: './articleCard.component.html',
  styleUrls: ['./articleCard.component.scss'],
})
export class ArticleCardComponent {
  /**
   * The article data to display. This input is expected to be provided by the parent component.
   */
  @Input() article?: Article;

  /**
   * @constructor
   * @param {Router} router - The Angular Router service for navigation.
   */
  constructor(private router: Router) {}

  /**
   * Navigates to the detailed view of the article.
   * @param {number} articleId - The unique identifier of the article to navigate to.
   */
  openArticle(articleId: number) {
    this.router.navigate(['/article', articleId]);
  }

  /**
   * Truncates the article content to a specified maximum number of words.
   * @param {string} content - The content to be truncated.
   * @param {number} maxWords - The maximum number of words to include in the truncated content.
   * @returns {string} The truncated content.
   */
  truncateContent(content: string, maxWords: number): string {
    return truncContent(content, maxWords);
  }
}
