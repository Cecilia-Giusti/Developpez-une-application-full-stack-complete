import { Theme } from '../theme.model';

/**
 * Represents the structure of a comment response received from the backend.
 */
export interface ThemeResponse {
  themes: Theme[];
}
