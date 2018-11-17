import { Injectable } from '@angular/core';

const AUTH_KEY = "AuthKey";

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(AUTH_KEY)
    window.sessionStorage.setItem(AUTH_KEY, token)
  }

  public removeToken() {
    window.sessionStorage.removeItem(AUTH_KEY)
  }

  public getToken(): string {
    return window.sessionStorage.getItem(AUTH_KEY)
  }
}
