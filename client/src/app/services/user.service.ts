import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {HttpService} from "./http.service";
import {UserModel} from "../model/user.model";

const USER_URL = "http://localhost:8080/api/user/";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpService: HttpService,
              private tokenStorageService: TokenStorageService,
              private httpClient: HttpClient) { }

  public getCurrentUser(): Observable<UserModel> {
    let options = {
      headers: this.httpService.createAuthHeader(this.tokenStorageService.getToken())
    };

    return this.httpClient.get<UserModel>(USER_URL, options);
  }
}
