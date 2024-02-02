/**
 * Defines the structure for a request to create a new article.
 */
export interface newArticleRequest {
  themeId: number;
  title: string;
  content: string;
}
