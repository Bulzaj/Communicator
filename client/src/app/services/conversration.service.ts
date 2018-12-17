import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpService} from "./http.service";
import {TokenStorageService} from "./token-storage.service";
import {HttpClient} from "@angular/common/http";

const CONVERSATIONS_NAME_URL = "http://localhost:8080/api/conversation/get-name/";

@Injectable({
  providedIn: 'root'
})
export class ConversrationService {

  constructor(private httpService: HttpService,
              private tokenStorageService: TokenStorageService,
              private httpClient: HttpClient) { }

  public getConversationsName(receiversName: string): Observable<any> {

    let options = {
      headers: this.httpService.createAuthHeader(this.tokenStorageService.getToken())
    };

    return this.httpClient.get(CONVERSATIONS_NAME_URL + receiversName, options)
  }
}
