import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {TokenStorageService} from "../services/token-storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

   username = new String();
   password = new String();
   errors : string;

  constructor(private router: Router,
              private authService: AuthService,
              private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
  }

  login(): void {

    this.authService.login(this.username.toString(), this.password.toString()).subscribe(data => {
      let token;
      token = data.tokenType + " " + data.accessToken;
      this.tokenStorageService.saveToken(token)
      this.router.navigate(['/home'])
    }, err => {
      this.errors = err.error.message
    });
  }

  cridentialsLenghtValidator(): boolean {

    if (this.username.length >= 3 && this.username.length <=20 &&
        this.password.length >= 5) {
      return true;
    } else {
      return false;
    }
  }
}
