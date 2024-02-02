/**
 * Represents the structure of a user response received from the backend.
 */
export interface UserResponse {
  id: string;
  username: string;
  email: string;
  password: string;
  created_at: Date;
  updated_at: Date;
}
