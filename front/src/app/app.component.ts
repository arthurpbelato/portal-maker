import {Component, OnInit} from '@angular/core';
import {UserService} from "../service/user.service";
import {Observable} from "rxjs";
import {UserProfileDTO} from "../model/UserProfileDTO";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'front';

  constructor(
    private userService: UserService
  ) {}

  message: String = "";

  users: UserProfileDTO[] = [];

  hello() {
    this.userService.hello().subscribe(
      resp => {
        this.message = resp
      }
    );
  }

  ngOnInit(): void {
    this.userService.listUsers().subscribe(
      resp => {
        this.users = resp;
      }
    );
  }

}
