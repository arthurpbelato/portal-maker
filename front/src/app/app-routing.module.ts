import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from "./components/login/login.component";
import {UserComponent} from "./components/user/user.component";
import {UserFormComponent} from "./components/user/form/user-form.component";
import {PostFormComponent} from "./components/post/form/post-form.component";
import {authGuard} from "./components/authGuard/auth.guard";
import {RequestLabComponent} from "./components/request-lab/request-lab.component";
import {PostDetailComponent} from "./components/post/detail/post-detail.component";
import {ReviewPageComponent} from "./components/review-page/review-page.component";

const allRoles: String[] = ["ROLE_USER", "ROLE_ADMIN", "ROLE_REVIEWER"];

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'user', component: UserComponent, canActivate: [authGuard], data: {roles: ["ROLE_ADMIN"]}},
  { path: 'user/form', component: UserFormComponent, canActivate: [authGuard], data: {roles: ["ROLE_ADMIN"]} },
  { path: 'usuario/editar/:id', component: UserFormComponent, canActivate: [authGuard], data: {roles: ["ROLE_ADMIN"]} },
  { path: 'postagens/nova', component: PostFormComponent, title: "Portal Maker - Nova Postagem", canActivate: [authGuard], data: {roles: allRoles} },
  { path: 'postagens/editar/:id', component: PostFormComponent, title: "Portal Maker - Editar Postagem", canActivate: [authGuard], data: {roles: allRoles} },
  { path: 'requisitar-uso-laboratorio', component: RequestLabComponent, title: "Requisitar Uso do Laboratório" },
  { path: 'revisoes', component: ReviewPageComponent, title: "Revisões",  canActivate: [authGuard], data: {roles: allRoles} },
  { path: 'post/detail/:id', component: PostDetailComponent },
  { path: '**', redirectTo: 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
