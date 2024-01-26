import { Component, Input } from '@angular/core';
import { Article } from '../core/models/article.model';
import { Router } from '@angular/router';
import { truncContent } from '../utils/truncContent';

@Component({
  selector: 'app-articleCard',
  templateUrl: './articleCard.component.html',
  styleUrls: ['./articleCard.component.scss'],
})
export class ArticleCardComponent {
  @Input() article?: Article;

  constructor(private router: Router) {}

  openArticle(articleId: number) {
    this.router.navigate(['/article', articleId]);
  }

  truncateContent(content: string, maxWords: number) {
    return truncContent(content, maxWords);
  }
}
