import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {MenuItem} from "primeng/api";
import {LoginComponent} from "../login/login.component";
import {UserService} from "../../../service/user.service";

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  providers: [LoginComponent],
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  @Output() logoutEvent = new EventEmitter();

  items: MenuItem[] | undefined;
  wasLogged: boolean = false;
  isAdmin: boolean = false;
  isVisible: boolean = true;
  userName: string | null = "";

  constructor(private loginComponent: LoginComponent, private userService: UserService) {
    this.userService.userRoles().subscribe(
      resp => {
        if (resp !== undefined) {
          this.items = []
          this.isAdmin = resp.some((role) => role === 'ROLE_ADMIN')
        }
      },
      error => {
      },
    );
    this.isVisible = true
  }

  ngOnInit(): void {
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
    this.loggedUserLoad();
  }

  loggedUserLoad() {
    this.wasLogged = localStorage.getItem('token') !== null;
    let name = localStorage.getItem('userName');
    this.userName = name ? name : "Visitante";

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
        })
    }
  }

  logout() {
    this.loginComponent.logout();
    this.ngOnInit();
  }

}
