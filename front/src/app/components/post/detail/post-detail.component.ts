import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PostService} from "../../../../service/post.service";
import {PostDTO} from "../../../../model/PostDTO";
import {MessageService} from "primeng/api";
import {SubjectEnum} from "../../../../enums/SubjectEnum";
import {PostReviewDTO} from "../../../../model/PostReviewDTO";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {saveAs} from "file-saver";
import {style} from "@angular/animations";
import {UserService} from "../../../../service/user.service";
import {ToastEmitterService} from "../../../../service/toast-emitter.service";

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css'],
  providers: [MessageService]
})
export class PostDetailComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute,
              private service: PostService,
              private userService: UserService,
              private messageService: MessageService,
              private router: Router,
              private fb: FormBuilder,
              private toastService: ToastEmitterService) {}

  id: string | undefined;
  name: string | undefined;
  post: PostDTO = new PostDTO();
  responsiveOptions: any[] | undefined;
  form!: FormGroup;
  postReview: PostReviewDTO = new PostReviewDTO();
  isReviewer: boolean = false;
  protected readonly SubjectEnum = SubjectEnum;


  //FIXME mover para date utils ---------------------------------------------
  options = {
    weekday: "long",
    year: "2-digit",
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
  };
  // @ts-ignore
  brDateTime = new Intl.DateTimeFormat("pt-BR", this.options).format;
  // ------------------------------------------------------------------------

  writeReviewNoteDialogVisible: boolean = false;
  showReviewNoteDialogVisible: boolean = false;

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

    this.userService.userRoles().subscribe({
      next: (resp: String[]) => {
        if (resp !== undefined) {
          this.isReviewer = resp.some((role) => role === 'ROLE_ADMIN' || role == "ROLE_REVIEWER");
        }
      },
      error: _ => {
        this.messageService.add({ severity: 'error', summary: 'Um erro inesperado ocorreu!', detail: 'Erro ao carregar as informações do usuário logado' });
      }
    });

    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
    });

    if (this.id !== undefined) {
      this.getPost(this.id);
    }

    this.form = this.fb.group({
      reviewNote: ['', [Validators.required]],
    });
  }

  getPost(id: string) {
    this.service.get(id).subscribe(
      resp => {
        this.post = resp;
        this.loadPostImages();
        this.loadPostModels();
        this.name = this.post.user?.name;
        this.post.postDate = this.brDateTime(new Date(this.post.postDate));

      }
    );
  }

  loadPostImages() {
    this.service.loadImages(this.id).subscribe(
      resp => {
        this.post.images = resp;
      },
      error => {
        console.log("Erro ao carregar imagens")
      }
    );
  }

  loadPostModels() {
    this.service.lazyLoadModels(this.id!).subscribe(resp => {
      this.post.models = resp;
    })
  }

  approve(): void {
    this.service.approve(this.id!).subscribe(
      value =>  {
        this.toastService.showSuccess("Salvo!", "Postagem aprovada! Agora ela será exibida para todos");
        this.router.navigate(['/home']);
      }
    )
  }


  showWriteReviewNoteDialog(): void {
    this.writeReviewNoteDialogVisible = true;
  }

  askReview(): void {
    this.postReview = this.form.value as PostReviewDTO
    this.service.askReview(this.id!, this.postReview).subscribe(value => {
      this.toastService.showSuccess("Enviado!", "Postagem enviada para edição!");
      this.router.navigate(['/home']);
    });
  }

  showReviewNotes(): void {
    this.showReviewNoteDialogVisible = true;
  }

  edit(): void {
    this.router.navigate([`/postagens/editar/${this.id!}`]);
  }

  download(lazyModel: any) {
    this.service.downloadModel(lazyModel.id).subscribe(model => {
      const blob = this.base64ToBlob(model.base64!, model.extension);
      saveAs(blob, model.title);
    })

  }

  showReviewBar() {
    const name = localStorage.getItem('userName');
    return this.post.status === 0 && this.isReviewer && this.post.user?.name !== name;
  }

  base64ToBlob(base64String: string, contentType = '') {
    const byteCharacters = atob(base64String);
    const byteArrays = [];

    for (let i = 0; i < byteCharacters.length; i++) {
      byteArrays.push(byteCharacters.charCodeAt(i));
    }

    const byteArray = new Uint8Array(byteArrays);
    return new Blob([byteArray], {type: contentType});
  }

  protected readonly style = style;
}
