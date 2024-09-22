import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {UserProfileDTO} from "../model/UserProfileDTO";
import {BasicUserDTO} from "../model/BasicUserDTO";
import {LoggedUserDTO} from "../model/LoggedUserDTO";
import {RoleDTO} from "../model/RoleDTO";
import {UserProfileDetailsDTO} from "../model/UserProfileDetailsDTO";
import {ChangePasswordDTO} from "../model/ChangePasswordDTO";

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

  save(user: UserProfileDTO): Observable<UserProfileDTO> {
    return this.httpClient.post<UserProfileDTO>(`api/user/internal/save`, user, options)
  }

  get(id: string): Observable<UserProfileDTO> {
    return this.httpClient.get<UserProfileDTO>(`api/user/internal/${id}`, options);
  }

  getUserDetails(): Observable<UserProfileDetailsDTO> {
    return this.httpClient.get<UserProfileDetailsDTO>(`api/user/internal/profile/details`, options);
  }

  changePassword(passwordDTO: ChangePasswordDTO): Observable<string> {
    return this.httpClient.post<string>(`api/user/internal/password/update`, passwordDTO, options)
  }

}
