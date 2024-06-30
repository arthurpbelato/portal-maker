import { Component } from '@angular/core';
import {UserService} from "../../../service/user.service";
import {PostService} from "../../../service/post.service";
import {PostListDTO} from "../../../model/PostListDTO";
import {PageDTO} from "../../../model/PageDTO";
import {PostStatusEnum} from "../../../enums/PostStatusEnum";

@Component({
  selector: 'app-review-page',
  templateUrl: './review-page.component.html',
  styleUrls: ['./review-page.component.css']
})
export class ReviewPageComponent {

  constructor(
    private userService: UserService,
    private postService: PostService
  ) {}

  postList : PostListDTO[] = [];

  responsiveOptions: any[] | undefined;

  ngOnInit(): void {
    this.responsiveOptions = [
      {
        breakpoint: '1024px',
        numVisible: 5
      },
      {
        breakpoint: '768px',
        numVisible: 3
      },
      {
        breakpoint: '560px',
        numVisible: 1
      }
    ];

    this.postService.listReview(PageDTO.default()).subscribe(
      {
        next: (resp: PostListDTO[]): void => {
          this.postList = resp
          this.loadPostImages();
        },
        error: (error): void => {
          console.log("Erro ao listar postagens")
        },
      }
    );
  }

  loadPostImages(): void {
    this.postList.forEach(post => {
      this.postService.loadImages(post.id).subscribe(
        resp => {
          post.images = resp;
        },
        error => {
          console.log("Erro ao carregar imagens")
        }
      );
    })
  }


  protected readonly PostStatusEnum = PostStatusEnum;
}
