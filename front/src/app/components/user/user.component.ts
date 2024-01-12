import {Component, OnInit} from '@angular/core';
import {UserProfileDTO} from "../../../model/UserProfileDTO";
import {UserService} from "../../../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  users: UserProfileDTO[] = [];

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit() {
    this.userService.listUsers().subscribe(
      resp => {
        this.users = resp;
      },
      error => {
          console.log("Erro ao listar usuários")
      }
    );
  }

  new() {
    this.router.navigate(['user/form']);
  }

}
