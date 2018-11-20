import {Injectable} from "@angular/core";
import {CanActivate, Router} from "@angular/router";
import {AuthService} from "./services/auth.service";

@Injectable()
export class IsUnauthenticatedGuard implements CanActivate{

  constructor(private authService: AuthService,
              private router: Router) {

  }

  canActivate() {

    if (!this.authService.isAuthenticated()) {
      return true;
    } else {
      this.router.navigate(["/home"])
      return false;
    }
  }
}
