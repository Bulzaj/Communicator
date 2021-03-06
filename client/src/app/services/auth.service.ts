import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {Observable} from "rxjs";
import {HttpService} from "./http.service";

const AUTH_URL = "http://localhost:8080/api/auth/";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient,
              private tokenStorage: TokenStorageService) { }

  public login(username: string, password: string): Observable<any>{

    const cridentials = {username: username, password: password};

    return this.httpClient.post(AUTH_URL+"signin", cridentials)
  }

  public logout() {
    this.tokenStorage.removeToken();
  }

  public register(username: string, password: string, passwordAgain: string) {

    let cridentials = {
      username: username,
      password: password,
      passwordAgain: passwordAgain
    }

    return this.httpClient.post(AUTH_URL+"signup", cridentials)
  }

  public isAuthenticated(): boolean {
    if (this.tokenStorage.getToken() != null) {
      return true
    } else {
      return false
    }
  }
}
