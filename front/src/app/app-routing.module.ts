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
import {ProfileComponent} from "./components/user/profile/profile.component";
import {ChangePasswordComponent} from "./components/user/change-password/change-password.component";

const allRoles: String[] = ["ROLE_USER", "ROLE_ADMIN", "ROLE_REVIEWER"];

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home',title: "Portal Maker - Home", component: HomeComponent },
  { path: 'login', title: "Portal Maker - Login", component: LoginComponent },
  { path: 'user', title: "Portal Maker - Usuários", component: UserComponent, canActivate: [authGuard], data: {roles: ["ROLE_ADMIN"]}},
  { path: 'user/form', title: "Portal Maker - Usuários", component: UserFormComponent, canActivate: [authGuard], data: {roles: ["ROLE_ADMIN"]} },
  { path: 'usuario/editar/:id', title: "Portal Maker - Usuários", component: UserFormComponent, canActivate: [authGuard], data: {roles: ["ROLE_ADMIN"]} },
  { path: 'usuario/perfil', title: "Portal Maker - Perfil", component: ProfileComponent, canActivate: [authGuard], data: {roles: allRoles} },
  { path: 'usuario/change-password', title: "Portal Maker - Perfil", component: ChangePasswordComponent, canActivate: [authGuard], data: {roles: allRoles} },
  { path: 'postagens/nova', component: PostFormComponent, title: "Portal Maker - Nova Postagem", canActivate: [authGuard], data: {roles: allRoles} },
  { path: 'postagens/editar/:id', component: PostFormComponent, title: "Portal Maker - Editar Postagem", canActivate: [authGuard], data: {roles: allRoles} },
  { path: 'requisitar-uso-laboratorio', component: RequestLabComponent, title: "Portal Maker - Requisitar Uso do Laboratório" },
  { path: 'revisoes', component: ReviewPageComponent, title: "Portal Maker - Revisões",  canActivate: [authGuard], data: {roles: allRoles} },
  { path: 'post/detail/:id', title: "Portal Maker - Postagem", component: PostDetailComponent },
  { path: '**', redirectTo: 'home' , title: "Portal Maker - Home"},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
