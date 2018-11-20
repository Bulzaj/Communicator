import {CanActivate, Router} from "@angular/router";
import {AuthService} from "./services/auth.service";
import {Injectable, Input} from "@angular/core";

@Injectable()
export class IsAuthenticatedGuard implements CanActivate{

  private message: string

  constructor(private authService: AuthService, private router: Router) {
  }

  canActivate() {

    console.log("IsAuthenticatedGuard is watching");

    if (this.authService.isAuthenticated()) {
      return true;
    } else {
      this.message = "You have to login first";
      this.router.navigate(['/login']);
      return false;
    }
  }

  public getMessage(): string {
    return this.message
  }
}
