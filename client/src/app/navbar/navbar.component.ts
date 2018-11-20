import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../services/token-storage.service";
import {AuthService} from "../services/auth.service";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username: string;

  constructor(private tokenStorageService: TokenStorageService,
              private authService: AuthService,
              private userService: UserService) { }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  logout(): void {
    this.authService.logout()
  }

  getUsername(): void {
    if (this.isAuthenticated()) {
      this.userService.getUserDetails().subscribe(data => {
        console.log(data.username);
        this.username =  data.username.toUpperCase();
      })
    }
  }

  ngOnInit() {
    this.getUsername();
  }

}
