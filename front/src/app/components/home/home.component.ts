import { Component } from '@angular/core';
import {UserService} from "../../../service/user.service";
import {UserProfileDTO} from "../../../model/UserProfileDTO";
import {BasicUserDTO} from "../../../model/BasicUserDTO";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(
    private userService: UserService
  ) {}

  message: String = "";
  message2: String = "";

  hello() {
    this.userService.hello().subscribe(
      resp => {
        this.message = resp
      }
    );
  }

  helloAuth() {
    this.userService.helloAuth().subscribe(
      resp => {
        this.message2 = resp
    });
  }

}
