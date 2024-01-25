import { LOCALE_ID, NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { MatIconModule, MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatListModule } from '@angular/material/list';
import { ArticleCardComponent } from './articleCard/articleCard.component';
import { MatCardModule } from '@angular/material/card';
import * as fr from '@angular/common/locales/fr';
import { registerLocaleData } from '@angular/common';
import { NewArticleComponent } from './new-article/new-article.component';
import { MatSelectModule } from '@angular/material/select';
import { ArticleComponent } from './article/article.component';
import { CommentComponent } from './comment/comment.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { ThemeCardComponent } from './theme-card/theme-card.component';
import { ProfileComponent } from './pages/profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    DashboardComponent,
    ArticleCardComponent,
    NewArticleComponent,
    ArticleComponent,
    CommentComponent,
    ThemesComponent,
    ThemeCardComponent,
    ProfileComponent,
  ],
  providers: [
    {
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS,
      useValue: { appearance: 'outline' },
    },
    {
      provide: LOCALE_ID,
      useValue: 'fr-FR',
    },
  ],
  bootstrap: [AppComponent],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    FormsModule,
    MatMenuModule,
    MatListModule,
    MatCardModule,
    HeaderComponent,
    MatSelectModule,
  ],
})
export class AppModule {
  constructor(
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer
  ) {
    this.matIconRegistry.addSvgIcon(
      'arrow',
      this.domSanitizer.bypassSecurityTrustResourceUrl('../assets/arrow.svg')
    );
    this.matIconRegistry.addSvgIcon(
      'user',
      this.domSanitizer.bypassSecurityTrustResourceUrl('../assets/user.svg')
    );
    this.matIconRegistry.addSvgIcon(
      'burger',
      this.domSanitizer.bypassSecurityTrustResourceUrl('../assets/burger.svg')
    );
    this.matIconRegistry.addSvgIcon(
      'send',
      this.domSanitizer.bypassSecurityTrustResourceUrl('../assets/send.svg')
    );
    registerLocaleData(fr.default);
  }
}
