import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MenuItem} from "primeng/api";
import {LoginComponent} from "../login/login.component";

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
  userName: string | null = "";

  constructor(private loginComponent: LoginComponent) {}

  ngOnInit(): void {
    this.items = [
      {
        label: 'Home',
        routerLink: '/home',
        icon: 'pi pi-fw pi-home'
      },
      {
        label: 'Nova Postagem',
        routerLink: '/postagens/nova',
        icon: 'pi pi-fw pi-plus'
      },
      {
        label: 'Usuários',
        routerLink: '/user',
        icon: 'pi pi-fw pi-users'
      },
      {
        label: 'Events',
        icon: 'pi pi-fw pi-calendar',
        items: [
          {
            label: 'Edit',
            icon: 'pi pi-fw pi-pencil',
            items: [
              {
                label: 'Save',
                icon: 'pi pi-fw pi-calendar-plus'
              },
              {
                label: 'Delete',
                icon: 'pi pi-fw pi-calendar-minus'
              }
            ]
          },
          {
            label: 'Archieve',
            icon: 'pi pi-fw pi-calendar-times',
            items: [
              {
                label: 'Remove',
                icon: 'pi pi-fw pi-calendar-minus'
              }
            ]
          }
        ]
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
      this.items?.push({
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
