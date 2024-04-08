import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {PostDTO} from "../model/PostDTO";
import {PostListDTO} from "../model/PostListDTO";

const ResponseType = 'json';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private router: Router, private httpClient: HttpClient) { }


  // @ts-ignore
  savePost(postDTO: PostDTO): Observable<String> {
    return this.httpClient.post<String>(`api/post/internal/save`, postDTO, {responseType: ResponseType});
  }

  // @ts-ignore
  list(): Observable<PostListDTO[]> {
    return this.httpClient.get<PostListDTO[]>(`api/post/public/list`, {responseType: ResponseType});
  }

  // @ts-ignore
  get(id: string): Observable<PostDTO> {
    return this.httpClient.get<PostDTO>(`api/post/public/${id}`, {responseType: ResponseType});
  }

  // @ts-ignore
  loadImages(postId): Observable<PostListDTO[]> {
    return this.httpClient.get<PostListDTO[]>(`api/post/public/images/${postId}`, {responseType: ResponseType});
  }

}
