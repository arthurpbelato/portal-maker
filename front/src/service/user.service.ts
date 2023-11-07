import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {UserProfileDTO} from "../model/UserProfileDTO";

const responseType = 'text' as 'json';

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

  // @ts-ignore
  login(user): Observable<String> {
    return this.httpClient.post<String>(`api/user/public/login`, user, {responseType: responseType});
  }

  listUsers(): Observable<UserProfileDTO[]> {
    return this.httpClient.get<UserProfileDTO[]>(`api/user/internal/list`, {responseType: responseType});
  }

}
