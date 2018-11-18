import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {Observable} from "rxjs";

const AUTH_URL = "http://localhost:8080/api/auth/";
const USER_URL = "http://localhost:8080/api/user";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
              private tokenStorage: TokenStorageService) { }

  public login(username: string, password: string): Observable<any>{

    const cridentials = {username: username, password: password};

    return this.http.post(AUTH_URL+"signin", cridentials)
  }

  public logout() {
    this.tokenStorage.removeToken();
  }

  public register() {

  }

  public getUserDetails(): Observable<any> {
    return this.http.get(USER_URL);
  }
}
