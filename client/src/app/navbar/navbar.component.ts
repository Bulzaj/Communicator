import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../services/token-storage.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username: string;

  constructor(private tokenStorageService: TokenStorageService,
              private authService: AuthService) { }

  isLoggedIn(): boolean {
    if (this.tokenStorageService.getToken() != null) {
      this.authService.getUserDetails().subscribe(data => {
        this.username = data.username;
      });
      return true;
    } else {
      return false;
    }
  }

  logout(): void {
    this.authService.logout()
  }

  ngOnInit() {
  }

}
