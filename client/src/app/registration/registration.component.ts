import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  username: string;
  password: string;
  passwordAgain: string;
  errors: string;

  public register(): void {
    this.authService.register(this.username, this.password, this.passwordAgain).subscribe(data => {
      this.router.navigate(['/login'])
    }, err => {
      this.errors = err.error.message;
    })
  }

  fieldsValidator(): boolean {
    if (this.username.length >= 3 &&
      this.username.length <= 20 &&
      this.password.length >= 5 &&
      this.passwordAgain.length >= 5) {
      return true;
    } else return false;
  }

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
  }

}
