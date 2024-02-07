import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../../service/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SelectItem} from "primeng/api";
import {RoleDTO} from "../../../../model/RoleDTO";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-user-from',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  form! : FormGroup;

  roles? : RoleDTO[];

  ngOnInit(): void {
    this.getRoles();
    this.buildForm();
  }

  buildForm() {
    this.form = this.fb.group({
      name: ['',[Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      cpf: ['', [Validators.required, Validators.minLength(11)]],
      roles: ['', [Validators.required]]
    });
  }

  getRoles() {
    this.userService.lisRoles().subscribe(
      resp => {
        this.roles = resp;
      },
      error => {
        //TODO create error toggle alerts
      }
    )
  }

  save() {
    if (this.form.valid) {
      this.userService.save(this.form.value).subscribe(
        resp => {
          console.log("resp")
          this.router.navigate(['/user']);
        },
        error => {
          //TODO create error toggle alerts
        }
      );
    } else {
      //TODO create error toggle alerts
    }
  }

  resetForm() {

  }

}
