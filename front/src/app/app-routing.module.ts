import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

/**
 * Defines the routes for the application.
 * The array of `Routes` outlines the route configurations for the application.
 * Each `Route` maps a URL path to a component.
 *
 * @type {Routes}
 */
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
