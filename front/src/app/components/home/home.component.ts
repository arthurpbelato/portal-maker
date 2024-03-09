import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../service/user.service";
import {PostService} from "../../../service/post.service";
import {PostListDTO} from "../../../model/PostListDTO";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private userService: UserService,
    private postService: PostService
  ) {}

  responsiveOptions: any[] | undefined;

  postList : PostListDTO[] = [];

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

    this.postService.list().subscribe(
      resp => {
        this.postList = resp
        this.loadPostImages();
      },
      error => {
        console.log("Erro ao listar posts")
      },
    );
  }

  loadPostImages() {
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

}
