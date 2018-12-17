import {Component, OnChanges, OnInit} from '@angular/core';
import {TokenStorageService} from "../services/token-storage.service";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
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
              private userService: UserService,
              private router: Router) { }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  logout(): void {
    this.authService.logout()
    this.router.navigate(["/"]);
  }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(data => {
      this.username = data.username;
    })
  }

}
