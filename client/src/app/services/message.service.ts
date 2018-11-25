import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpService} from "./http.service";
import {TokenStorageService} from "./token-storage.service";
import {SendedMessageModel} from "../model/sended-message.model";

const MESSAGE_URL = "http://localhost:8080/api/conversation";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient,
              private httpService: HttpService,
              private tokenStorageService: TokenStorageService) { }

  public sendMessage(message: SendedMessageModel) {

    console.log("Send message works");

    let options = {
      headers: this.httpService.createAuthHeader(this.tokenStorageService.getToken()),
    };

    let body = {
      messageBody: message.messageBody,
      receiversName: message.receiversName
    };

    return this.http.post(MESSAGE_URL, body, options);
  }
}
