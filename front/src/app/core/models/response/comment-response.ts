/**
 * Represents the structure of a comment response received from the backend.
 */
export interface CommentResponse {
  id: number;
  author: string;
  content: string;
  createdAt: Date;
}
