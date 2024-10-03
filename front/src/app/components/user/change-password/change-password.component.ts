import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../../service/user.service";
import {Router} from "@angular/router";
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {Message} from "primeng/api";
import {ChangePasswordDTO} from "../../../../model/ChangePasswordDTO";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
})
export class ChangePasswordComponent implements OnInit{

  constructor(
    private userService: UserService,
    private router: Router,
    private fb: FormBuilder
  ) {
  }

  messages!: Message[];

  form! : FormGroup;

  ngOnInit(): void {
    this.buildForm();
  }

  buildForm() {
    this.form = this.fb.group({
      oldPassword: ['', [Validators.required]],
      newPassword: ['', [Validators.required]],
      confirmPassword: ['', [Validators.required, this.passwordEquals()]]
    })
    this.form.controls['newPassword'].valueChanges.subscribe(() => {
      this.form.controls['confirmPassword'].updateValueAndValidity();
    });
  }

  change() {
    this.formValidation();

    if (this.form.valid) {
      let changePasswordDTO = this.form.value as ChangePasswordDTO;
      this.userService.changePassword(changePasswordDTO).subscribe(
        resp => {
          this.router.navigate(['/usuario/perfil'])
        },
        error => {
          this.messages = [{ severity: 'error', detail: error.error.message }];
        }
      );
    }

  }

  formValidation() {
    if (!this.form.valid) {
      this.form.controls['oldPassword'].markAsDirty();
      this.form.controls['newPassword'].markAsDirty();
      this.form.controls['confirmPassword'].markAsDirty();

      this.messages = [{ severity: 'error', detail: "Todos os campos são obrigatórios" }];

      let controlErrors: ValidationErrors | null = this.form.controls['confirmPassword'].errors;
      if (controlErrors != null) {
        Object.keys(controlErrors).forEach(keyError => {
          if (keyError === 'passwordConfirm') {
            this.messages = [{ severity: 'error', detail: "As senhas devem ser iguais" }];
          }
        });
      }
    }
  }

  passwordEquals(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      let isEqual = control.value === control.parent?.get('newPassword')?.value;
      return !isEqual? { passwordConfirm: true } : null;
    };
  }

}
