import {Component, EventEmitter, Output} from '@angular/core';
import {SubjectEnum} from "../../../../enums/SubjectEnum";

@Component({
  selector: 'app-subject-filter-button',
  templateUrl: './subject-filter-button.component.html',
  styleUrls: ['./subject-filter-button.component.css']
})
export class SubjectFilterButtonComponent {

  constructor() {}
  @Output() subjectIdEmmiter = new EventEmitter<number>();
  items: any[] = [];


  ngOnInit(): void {
    this.items = SubjectEnum.values().map((subject: SubjectEnum) =>{
      return {
        label: subject.label,
        command: () => this.subjectIdEmmiter.emit(subject.value)
      }
    });
  }

}
