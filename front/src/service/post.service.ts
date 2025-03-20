import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {HttpClient, HttpParams} from "@angular/common/http";
import {PostDTO} from "../model/PostDTO";
import {PostListDTO} from "../model/PostListDTO";
import {PageDTO} from "../model/PageDTO";
import {PostReviewDTO} from "../model/PostReviewDTO";
import {DocumentSaveDTO} from "../model/DocumentSaveDTO";

const ResponseType = 'json';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private router: Router, private httpClient: HttpClient) { }


  // @ts-ignore
  savePost(postDTO: PostDTO): Observable<PostDTO> {
    return this.httpClient.post<PostDTO>(`api/post/internal/save`, postDTO, {responseType: ResponseType});
  }

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

  listReview(page: PageDTO): Observable<PostListDTO[]> {
    const params = new HttpParams({
      fromObject: {...page}
    });
    return this.httpClient.get<PostListDTO[]>(`api/post/internal/list/review`,  {responseType: ResponseType, params});
  }

  approve(id: string) {
    return this.httpClient.patch(`api/post/internal/approve/${id}`, {responseType: ResponseType});
  }

  askReview(id: string, postReview: PostReviewDTO) {
    return this.httpClient.post(`api/post/internal/review/${id}`, postReview, {responseType: ResponseType});
  }

  deleteDocument(id: string | undefined) {
    return this.httpClient.delete(`api/post/internal/document/${id}`);
  }

  getReviewCount(): Observable<number> {
    return this.httpClient.get<number>(`api/post/internal/list/review/count`);
  }

  listBySubject(id: number): Observable<PostListDTO[]> {
    return this.httpClient.get<PostListDTO[]>(`api/post/public/list/subject/${id}`, {responseType: ResponseType});
  }

  lazyLoadModels(postId: string): Observable<PostListDTO[]>  {
    return this.httpClient.get<PostListDTO[]>(`api/post/public/models/lazy/${postId}`, {responseType: ResponseType});
  }

  downloadModel(modelId: string): Observable<DocumentSaveDTO>  {
    return this.httpClient.get<DocumentSaveDTO>(`api/post/public/models/download/${modelId}`);
  }
}
