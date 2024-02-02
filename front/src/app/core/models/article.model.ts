/**
 * Represents the structure of an article.
 */
export interface Article {
  id: number;
  author: string;
  themeId: number;
  title: string;
  content: string;
  createdAt: Date;
  updatedAt?: Date;
}
