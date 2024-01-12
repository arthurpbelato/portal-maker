import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../service/user.service";
import {BasicUserDTO} from "../../../model/BasicUserDTO";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Message} from "primeng/api";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private userService: UserService,
    private router: Router,
    private fb: FormBuilder
  ) {
  }

  // @ts-ignore
  messages: Message[];

  // @ts-ignore
  form : FormGroup;

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  message: String = "";

  user: BasicUserDTO = new BasicUserDTO();

  login() {
    this.user = this.form.value as BasicUserDTO;
    this.userService.login(this.user).subscribe(
      resp => {
        localStorage.setItem('token', resp.toString());
        this.router.navigate(['/home']);
      },
      error => {
        if (error.status === 401) {
          this.messages = [{ severity: 'error', detail: "Nome de usuário ou senha inválidos" }];
          this.form.setErrors({'incorrect': true});
          this.form.controls['name'].setErrors({'incorrect': true});
          this.form.controls['password'].setErrors({'incorrect': true});
        }
      }
    );
  }
}
