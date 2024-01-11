import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../../service/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SelectItem} from "primeng/api";
import {RoleDTO} from "../../../../model/RoleDTO";

@Component({
  selector: 'app-user-from',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  constructor(
    private userService: UserService,
    private fb: FormBuilder
  ) {}

  // @ts-ignore
  form : FormGroup;

  roles : RoleDTO[] = [{name: "PUBLIC_USER", id: 3}, {name: "ROLE_USER", id: 2}, {name: "ROLE_ADMIN", id: 1}];

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      cpf: ['', [Validators.required, Validators.minLength(11)]],
      selectedRoles: ['', [Validators.required]]
    });
  }

  save() {

  }

  resetForm() {

  }

}
