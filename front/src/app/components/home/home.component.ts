import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../service/user.service";
import {PostService} from "../../../service/post.service";
import {PostListDTO} from "../../../model/PostListDTO";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [MessageService]
})
export class HomeComponent implements OnInit {

  constructor(
    private userService: UserService,
    private postService: PostService,
    private messageService: MessageService
  ) {}

  responsiveOptions: any[] | undefined;
  pendingReviews: number = 0;
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

    this.postService.getReviewCount().subscribe(value => {
      console.log(value)
      this.pendingReviews = value;
    })
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

  showReviewToast(): void {
    this.messageService.add({ key: 'bc', severity: 'info', summary: 'Revisões pendentes', detail: 'Você possui revisões pendentes. Acesse a aba "Revisões" para ver mais detalhes' });
  }
}
