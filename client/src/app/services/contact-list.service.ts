import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {HttpService} from "./http.service";

const CONTACTS_URL = "http://localhost:8080/api/contacts";

@Injectable({
  providedIn: 'root'
})
export class ContactListService {

  constructor(private http: HttpClient,
              private tokenStorageService: TokenStorageService,
              private httpService: HttpService) { }

  public getContacts(): Observable<any> {

    let options = {
      headers: this.httpService.createAuthHeader(this.tokenStorageService.getToken())
    };

    return this.http.get(CONTACTS_URL, options)
  }
}
