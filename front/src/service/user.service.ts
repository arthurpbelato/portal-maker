import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {UserProfileDTO} from "../model/UserProfileDTO";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private router: Router, private httpClient: HttpClient) { }

  hello(): Observable<String> {
    return this.httpClient.get<String>(`api/public/hello`, {responseType: 'text' as 'json'});
  }


  listUsers(): Observable<UserProfileDTO[]> {
    let headers = new HttpHeaders().set('Authorization', 'Basic QWxpc3NvbjptaW5oYVNlbmhhMTIz');
    return this.httpClient.get<UserProfileDTO[]>(`api/user/internal/list`, {headers: headers});
  }

}
