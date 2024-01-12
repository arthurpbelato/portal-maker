import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {UserProfileDTO} from "../model/UserProfileDTO";
import {BasicUserDTO} from "../model/BasicUserDTO";
import {LoggedUserDTO} from "../model/LoggedUserDTO";

const responseType = 'json';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private router: Router, private httpClient: HttpClient) { }

  hello(): Observable<String> {
    return this.httpClient.get<String>(`api/public/hello`, {responseType: responseType});
  }

  helloAuth(): Observable<String> {
    return this.httpClient.get<String>(`api/internal/oauth`, {responseType: responseType});
  }

  login(user: BasicUserDTO): Observable<LoggedUserDTO> {
    return this.httpClient.post<LoggedUserDTO>(`api/user/public/login`, user, {responseType: responseType});
  }

  validateToken(): Observable<String> {
    return this.httpClient.get<String>(`api/user/internal/validate/token`, {responseType: responseType, headers: {SkipInterceptor: ''}});
  }

  listUsers(): Observable<UserProfileDTO[]> {
    return this.httpClient.get<UserProfileDTO[]>(`api/user/internal/list`, {responseType: responseType});
  }

}
