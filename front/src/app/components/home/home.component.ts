import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../service/user.service";
import {PostService} from "../../../service/post.service";
import {PostListDTO} from "../../../model/PostListDTO";
import {ToastEmitterService} from "../../../service/toast-emitter.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private userService: UserService,
    private postService: PostService,
    private toastService: ToastEmitterService
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

    this.postService.list().subscribe({
      next: (resp: PostListDTO[]) => {
        this.postList = resp
        this.loadPostImages();
      },
      error: _ => {
        this.showListErrorDialog();
      }
    })

    if (localStorage.getItem('token') !== null) {
      this.postService.getReviewCount().subscribe(value => {
        this.pendingReviews = value;
      });
    }

  }

  loadPostImages() {
    this.postList.forEach(post => {
      this.postService.loadImages(post.id).subscribe(
        resp => {
          post.images = resp;
        },
        error => {
          this.toastService.showError('Erro!','Erro carregar as imagens dos posts, tente novamente.');
        }
      );
    })
  }

  showReviewToast(): void {
    this.toastService.showInfoBc('Revisões pendentes','Você possui revisões pendentes. Acesse a aba "Revisões" para ver mais detalhes' );
  }

  filterSubject(subject: number) {
    this.postService.listBySubject(subject).subscribe({
      next: (resp: PostListDTO[]) => {
        this.postList = resp;
        if (this.postList.length === 0) {
         this.toastService.showInfo("Posts não encontrados", "Nenhum post foi encontrado com o filtro especificado, por favor tente outro.");
        }
        this.loadPostImages();
      },
      error: _ => {
        this.showListErrorDialog();
      }
    });
  }

  private showListErrorDialog(): void {
    this.toastService.showError('Erro!','Erro ao listar as postagens, tente novamente.');
  }
}
