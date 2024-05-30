import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PostService} from "../../../../service/post.service";
import {PostDTO} from "../../../../model/PostDTO";

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private service: PostService) {}

  id: string | undefined;
  name: string | undefined;
  post: PostDTO = new PostDTO();
  responsiveOptions: any[] | undefined;

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

    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
    });

    if (this.id !== undefined) {
      this.getPost(this.id);
    }

  }

  getPost(id: string) {
    this.service.get(id).subscribe(
      resp => {
        this.post = resp;
        this.loadPostImages();
        this.name = this.post.user?.name;
        this.post.postDate = this.brDateTime(new Date(this.post.postDate));
      },
      error => {
        console.log("Erro ao listar posts")
      },
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

}
