/**
 * Represents the structure of an article response received from the backend.
 */
export interface ArticleResponse {
  id: number;
  userId: number;
  themeId: number;
  author: string;
  theme: String;
  title: string;
  content: string;
  createdAt: Date;
  updatedAt: Date;
}
