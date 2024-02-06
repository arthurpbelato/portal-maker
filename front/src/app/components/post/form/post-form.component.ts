import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PostService} from "../../../../service/post.service";
import {PostDTO} from "../../../../model/PostDTO";
import {Message} from "primeng/api";
import {FileRemoveEvent, FileSelectEvent, FileSendEvent} from "primeng/fileupload";
import {DocumentSaveDTO} from "../../../../model/DocumentSaveDTO";
import {Observable, ReplaySubject} from "rxjs";
import {SubjectEnum} from "../../../../enums/SubjectEnum";
import {PostStatusEnum} from "../../../../enums/PostStatusEnum";

@Component({
  selector: 'app-post',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.css']
})
export class PostFormComponent {

  constructor(
    private postService: PostService,
    private router: Router,
    private fb: FormBuilder
  ) {
  }

  messages?: Message[];
  form!: FormGroup;
  post: PostDTO = new PostDTO();
  models: DocumentSaveDTO[] = [];
  images: DocumentSaveDTO[] = [];
  subjects: SubjectEnum[] = SubjectEnum.values();
  selectedSubject?: SubjectEnum;
  //TODO: verificar comportamento para edicao de post
  currentStatus: PostStatusEnum = PostStatusEnum.WAITING_REVIEW;

// initialize Tagify on the above input node reference


  ngOnInit(): void {
    this.form = this.fb.group({
      title: ['', [Validators.required]],
      description: ['', [Validators.required]],
      externalReference: [''],
      subject: ['', Validators.required],
      status: [{value: this.currentStatus.label, disabled: true}],
      tags: ['']
    });
  }

  publish(): void {
    this.post = this.form.value as PostDTO
    this.post.models = this.models;
    this.post.images = this.images;
    this.post.status = this.currentStatus.value; //necessario por conta do disabled: true
    console.log(this.post);

    this.postService.savePost(this.post).subscribe(response => {
      // localStorage.setItem('token', response.toString());
      // this.router.navigate(['/home']);
    }),
      (error: any) => {
        if (error.status === 401) {
          // this.messages = [{severity: 'error', detail: "Nome de usuário ou senha inválidos"}];
          // this.form.setErrors({'incorrect': true});
          // this.form.controls['name'].setErrors({'incorrect': true});
          // this.form.controls['password'].setErrors({'incorrect': true});
        }
      }
  }

  onSelectModels(event: FileSelectEvent): void {
    for (let file of event.files) {
      this.convertFile(file).subscribe(base64 => {
        let document = this.getDocumentDtoFromFile(base64, file);
        this.models.push(document);
      })
    }
  }

  onSelectImages(event: FileSelectEvent): void {
    for (let file of event.files) {
      this.convertFile(file).subscribe(base64 => {
        let document = this.getDocumentDtoFromFile(base64, file);
        this.images.push(document);
      })
    }
  }

  onModelRemove(event: FileRemoveEvent): void {
    this.removeDocumentByName(this.models, event.file.name);
  }

  onImageRemove(event: FileRemoveEvent): void {
    this.removeDocumentByName(this.images, event.file.name);
  }

  private convertFile(file: File): Observable<string> {
    const result = new ReplaySubject<string>(1);
    const reader = new FileReader();
    reader.readAsBinaryString(file);
    reader.onload = (event) => result.next(btoa(event.target!.result!.toString()));
    return result;
  }

  private removeDocumentByName(arr: DocumentSaveDTO[], name: string): DocumentSaveDTO[] {
    return arr.filter(doc => doc.title !== name);
  }

  private getDocumentDtoFromFile(base64: string, file: File): DocumentSaveDTO {
    let document = new DocumentSaveDTO();
    document.base64 = base64;
    document.size = file.size;
    document.title = file.name;
    let splitedName = file.name.split(".");
    document.extension = splitedName[splitedName.length - 1];
    return document;
  }
}


