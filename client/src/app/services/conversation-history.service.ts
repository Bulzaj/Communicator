import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {HttpService} from "./http.service";
import {TokenStorageService} from "./token-storage.service";

const CONVERSATION_HISTORY_URL = "http://localhost:8080/api/conversationSubject/history";

@Injectable({
  providedIn: 'root'
})
export class ConversationHistoryService {

  constructor(private http: HttpClient,
              private httpService: HttpService,
              private tokenStorageService: TokenStorageService) { }

  getHistory(receiversName: string): Observable<any> {

    let options = {
      headers: this.httpService.createAuthHeader(this.tokenStorageService.getToken())
    };

    return this.http.get(CONVERSATION_HISTORY_URL + "/" + receiversName, options)
  }
}
