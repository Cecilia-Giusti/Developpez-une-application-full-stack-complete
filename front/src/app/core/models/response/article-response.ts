export interface ArticleResponse {
  id: number;
  userId: number;
  author: string;
  themeId: number;
  title: string;
  content: string;
  createdAt: Date;
  updatedAt: Date;
}
