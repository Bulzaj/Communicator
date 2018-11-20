import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HttpService} from "./http.service";
import {TokenStorageService} from "./token-storage.service";

const USER_URL = "http://localhost:8080/api/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {



  constructor(private http: HttpClient,
              private httpService: HttpService,
              private tokenStorageService: TokenStorageService) { }

  public getUserDetails(): Observable<any> {
    let options = {
      headers: this.httpService.createAuthHeader(this.tokenStorageService.getToken())
    };

    return this.http.get(USER_URL, options);
  }
}
