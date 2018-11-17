import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {TokenStorageService} from "./services/token-storage.service";

const TOKEN_HEADER_KEY = 'Authorization'

export class Interceptor implements HttpInterceptor{

  constructor(private router: Router, private tokenStorage: TokenStorageService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let authReq = req

    if (this.tokenStorage.getToken() != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, this.tokenStorage.getToken())})
      console.log("interceptor working")
    }

    return next.handle(authReq)
  }
}
