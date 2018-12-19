import { Injectable } from '@angular/core';
import {HttpService} from "./http.service";
import {TokenStorageService} from "./token-storage.service";
import {HttpClient} from "@angular/common/http";
import {ConversationModel} from "../model/conversation.model";
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {MessageModel} from "../model/message.model";

const CONVERSATION_URL = "http://localhost:8080/api/conversation/";

@Injectable({
  providedIn: 'root'
})
export class ConversrationService {

  private conversationSubject: Subject<ConversationModel>;


  constructor(private httpClient: HttpClient,
              private httpService: HttpService,
              private tokenStorageService: TokenStorageService) {
    this.conversationSubject = new Subject<ConversationModel>();
  }

  private getConversation(receiversName: string): Observable<ConversationModel> {

    let options = {
      headers: this.httpService.createAuthHeader(this.tokenStorageService.getToken())
    };

    return this.httpClient.get<ConversationModel>(CONVERSATION_URL+receiversName, options)
  }

  public init(conversationsName: string) {
    console.log("init runs");
    this.getConversation(conversationsName).subscribe(data => {
      let conversation: ConversationModel = new ConversationModel(data.conversationsName, data.messages);
      this.updataSubject(conversation);
    })
  }

  public updataSubject(conversation: ConversationModel) {
    this.conversationSubject.next(conversation);
  }

  public getConversationAsObservable(): Observable<ConversationModel> {
    return this.conversationSubject.asObservable();
  }

  public sendMessage(receiversName: string, messageBody: string): Observable<MessageModel> {

    let options = {
      headers: this.httpService.createAuthHeader(this.tokenStorageService.getToken())
    };

    let body = {
      messageBody: messageBody
    };

    return this.httpClient.post<MessageModel>(CONVERSATION_URL+"send-to/"+receiversName, body, options);
  }
}
