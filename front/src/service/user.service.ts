import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {UserProfileDTO} from "../model/UserProfileDTO";
import {BasicUserDTO} from "../model/BasicUserDTO";
import {LoggedUserDTO} from "../model/LoggedUserDTO";
import {RoleDTO} from "../model/RoleDTO";
import {UserRegisterDTO} from "../model/UserRegisterDTO";

const responseType = 'json';
const options : object = { responseType: responseType };

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private router: Router, private httpClient: HttpClient) { }

  hello(): Observable<String> {
    return this.httpClient.get<String>(`api/public/hello`, options);
  }

  helloAuth(): Observable<String> {
    return this.httpClient.get<String>(`api/internal/oauth`, options);
  }

  login(user: BasicUserDTO): Observable<LoggedUserDTO> {
    return this.httpClient.post<LoggedUserDTO>(`api/user/public/login`, user, options);
  }

  validateToken(): Observable<String> {
    return this.httpClient.get<String>(`api/user/internal/validate/token`, {responseType: responseType, headers: {SkipInterceptor: ''}});
  }

  userRoles(): Observable<String[]> {
    return this.httpClient.get<String[]>(`api/user/internal/logged/role`, options);
  }

  listUsers(): Observable<UserProfileDTO[]> {
    return this.httpClient.get<UserProfileDTO[]>(`api/user/internal/list`, options);
  }

  lisRoles(): Observable<RoleDTO[]> {
    return this.httpClient.get<RoleDTO[]>(`api/user/internal/role`, options);
  }

  save(user: UserRegisterDTO): Observable<UserProfileDTO> {
    return this.httpClient.post<UserRegisterDTO>(`api/user/internal/register`, user, options)
  }

}
