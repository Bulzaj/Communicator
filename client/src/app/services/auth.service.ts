import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";

const AUTH_URL = "http://localhost:8080/api/auth/";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private token: string;

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) { }

  public login(username: string, password: string) {
    const cridentials = {username: username, password: password};

    this.http.post(AUTH_URL+"signin", cridentials).subscribe(data => {
      this.token = data.tokenType + " " + data.accessToken
      this.tokenStorage.saveToken(this.token)
    })
  }

  public logout() {
    this.tokenStorage.removeToken();
  }

  public register() {

  }
}
