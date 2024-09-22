import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../../service/user.service";
import {UserProfileDetailsDTO} from "../../../../model/UserProfileDetailsDTO";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private userService: UserService) {
  }

  user: UserProfileDetailsDTO = new UserProfileDetailsDTO;

  ngOnInit(): void {
    this.loadUser();
  }

  loadUser() {
    this.userService.getUserDetails().subscribe(
      resp => {
        this.user = resp;
      },
      error => {
      }
    );
  }

}
