import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MenuItem, MessageService} from "primeng/api";
import {LoginComponent} from "../login/login.component";
import {UserService} from "../../../service/user.service";

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  providers: [LoginComponent, MessageService],
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  @Output() logoutEvent = new EventEmitter();

  items: MenuItem[] | undefined;
  wasLogged: boolean = false;
  isAdmin: boolean = false;
  isVisible: boolean = true;
  userName: string | null = "";

  constructor(private loginComponent: LoginComponent,
              private userService: UserService,
              private messageService: MessageService) {
    this.userService.userRoles().subscribe({
      next: (resp: String[]) => {
        if (resp !== undefined) {
          this.items = [];
          this.isAdmin = resp.some((role) => role === 'ROLE_ADMIN');
          this.fillMenuItemsList();
        }
      },
      error: _ => {
        this.showError();
      },
      complete: () => {
        this.isVisible = true;
      }
    });
  }

  ngOnInit(): void {
    this.loggedUserLoad();
  }

  loggedUserLoad(): void {
    this.wasLogged = localStorage.getItem('token') !== null;
    const name = localStorage.getItem('userName');
    this.userName = name ? name : "Visitante";
  }

  logout(): void {
    this.loginComponent.logout();
    this.ngOnInit();
  }

  private fillMenuItemsList() {
    this.items = [
      {
        label: 'Home',
        routerLink: '/home',
        icon: 'pi pi-fw pi-home'
      },
      {
        label: 'Requisitar uso do Laboratório',
        routerLink: '/requisitar-uso-laboratorio',
        icon: 'pi pi-fw pi-pencil'
      }
    ];

    if (this.wasLogged) {
      this.items?.push(
        {
          label: 'Revisões',
          routerLink: '/revisoes',
          icon: 'pi pi-fw pi-pencil',
          id: 'reviews',
        },
        {
          label: 'Usuários',
          routerLink: '/user',
          icon: 'pi pi-fw pi-users',
          visible: this.isAdmin
        },
        {
          label: 'Nova Postagem',
          routerLink: '/postagens/nova',
          icon: 'pi pi-fw pi-plus'
        },
        {
          label: 'Logout',
          icon: 'pi pi-fw pi-power-off',
          command: () => this.logout()
        });
    }
  }

  showError(): void {
    this.messageService.add({ severity: 'error', summary: 'Um erro inesperado ocorreu!', detail: 'Erro ao carregar as informações do usuário logado' });
  }

}
