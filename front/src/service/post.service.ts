import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {PostDTO} from "../model/PostDTO";

const ResponseType = 'text' as 'json';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private router: Router, private httpClient: HttpClient) { }


  // @ts-ignore
  savePost(postDTO: PostDTO): Observable<String> {
    return this.httpClient.post<String>(`api/post/interal/save`, postDTO, {responseType: ResponseType});
  }
}
