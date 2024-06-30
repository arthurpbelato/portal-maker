import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PostService} from "../../../service/post.service";
import {Router} from "@angular/router";
import {DocumentSaveDTO} from "../../../model/DocumentSaveDTO";
import {FileRemoveEvent, FileSelectEvent} from "primeng/fileupload";
import {Observable, ReplaySubject} from "rxjs";
import {PostDTO} from "../../../model/PostDTO";
import {RequestLabDTO} from "../../../model/RequestLabDTO";
import {RequestLabService} from "../../../service/request-lab.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-request-lab',
  templateUrl: './request-lab.component.html',
  styleUrls: ['./request-lab.component.css'],
  providers: [MessageService]
})
export class RequestLabComponent {
  form!: FormGroup;
  files: DocumentSaveDTO[] = [];
  requestLabData?: RequestLabDTO;
  blockedDocument: boolean = false;

  constructor(
    private fb: FormBuilder,
    private service: RequestLabService,
    private messageService: MessageService
  ) {
  }

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Email enviado com sucesso' });
  }

  showFailure() {
    this.messageService.add({ severity: 'error', summary: 'Algo deu errado', detail: 'Parece que seu formulário não foi enviado corretamente. Tente novamente' });
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', [Validators.required]],
      phone: ['', [Validators.required]],
      email: ['', [Validators.required]],
      cpf: ['', [Validators.required]],
      title: ['', [Validators.required]],
      description: ['', [Validators.required]],
      resources: ['', [Validators.required]],
    });
  }

  onSelectModels(event: FileSelectEvent): void {
    for (let file of event.files) {
      this.convertFile(file).subscribe(base64 => {
        let document = this.getDocumentDtoFromFile(base64, file);
        this.files.push(document);
      })
    }
  }

  private convertFile(file: File): Observable<string> {
    const result = new ReplaySubject<string>(1);
    const reader = new FileReader();
    reader.readAsBinaryString(file);
    reader.onload = (event) => result.next(btoa(event.target!.result!.toString()));
    return result;
  }

  onModelRemove(event: FileRemoveEvent): void {
    this.removeDocumentByName(this.files, event.file.name);
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

  publish(): void {
    this.blockedDocument = true;
    this.requestLabData = this.form.value as RequestLabDTO;
    this.requestLabData.files = this.files;

    this.service.send(this.requestLabData).subscribe(response => {
      this.showSuccess();
      this.form.reset();
      this.blockedDocument = false;
    }),
      (error: any) => {
         this.showFailure()
      }
  }
}
