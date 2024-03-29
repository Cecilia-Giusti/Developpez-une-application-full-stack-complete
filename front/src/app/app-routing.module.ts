import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { NewArticleComponent } from './new-article/new-article.component';
import { ArticleComponent } from './pages/article/article.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { ProfileComponent } from './pages/profile/profile.component';

/**
 * Defines the routes for the application.
 *
 * @type {Routes}
 */
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'article', component: NewArticleComponent },
  { path: 'article/:id', component: ArticleComponent },
  { path: 'themes', component: ThemesComponent },
  { path: 'profile', component: ProfileComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
