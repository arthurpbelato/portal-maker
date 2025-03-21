import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../../service/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RoleDTO} from "../../../../model/RoleDTO";
import {UserProfileDTO} from "../../../../model/UserProfileDTO";
import {ActivatedRoute, Params, Route, Router} from "@angular/router";
import {ToastEmitterService} from "../../../../service/toast-emitter.service";

@Component({
  selector: 'app-user-from',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private toastService: ToastEmitterService
  ) {}

  form! : FormGroup;

  roles? : RoleDTO[];
  title? : string = "Novo Usuário";
  isEdit? : boolean = false;

  ngOnInit(): void {
    this.getRoles();
    this.buildForm();
  }

  buildForm() {
    this.form = this.fb.group({
      id: [''],
      name: ['',[Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      cpf: ['', [Validators.required, Validators.minLength(11)]],
      roles: ['', [Validators.required]]
    });

    this.route.params.subscribe((params: Params) => {
      if (params['id']!) {
        this.title = "Editar Usuário";
        this.isEdit = true;
        this.userService.get(params["id"]).subscribe(
          response => {
            this.fillForm(response);
          },
          (error: any) => {
            this.toastService.showError("Algo deu errado!", "Erro ao carregar informações do usuário!");
          }
        );
      }
    });
  }

  fillForm(user: UserProfileDTO) {
    this.form.controls['id'].setValue(user.id);
    this.form.controls['name'].setValue(user.name);
    this.form.controls['email'].setValue(user.email);
    this.form.controls['cpf'].setValue(user.cpf);
    this.form.controls['roles'].setValue(user.roles);
  }

  getRoles() {
    this.userService.lisRoles().subscribe(
      resp => {
        this.roles = resp;
      },
      error => {
        this.toastService.showError("Algo deu errado!", "Erro ao listar as permissões!");
      }
    )
  }

  save() {
    if (this.form.valid) {
      this.userService.save(this.form.value).subscribe(
        resp => {
          this.toastService.showSuccess("Sucesso!", "Usuário criado com sucesso!");
          this.router.navigate(['/user']);
        },
        error => {
          this.toastService.showError("Algo deu errado!", "Erro ao cadastrar usuário!");
        }
      );
    } else {
      this.toastService.showError("Dados inválidos!", "Por favor preencha todos os campos corretamente!");
    }
  }

  resetForm() {

  }

}
