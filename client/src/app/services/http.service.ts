import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {TokenStorageService} from "./token-storage.service";
import {HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: Http, private tokenStorageService: TokenStorageService) { }

  createAuthHeader(token: String): HttpHeaders {
    let headers: HttpHeaders;

    if (this.tokenStorageService.getToken() !=null) {
      headers = new HttpHeaders({
        'Content-Type': 'Application/json',
        'Authorization' : this.tokenStorageService.getToken()
      })
    }

    return headers;
  }
}
