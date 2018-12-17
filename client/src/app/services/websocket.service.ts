import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import {ConversrationService} from "./conversration.service";

const HANDSHAKE_URL = "ws://localhost:8080/socket";

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private _stomp;
  private _messages: string[] = [];
  private _errors: string[] = [];
  private  conversationsName: string;


  constructor(private conversationService: ConversrationService) {

  }

  public connect(receiversName: string) {

    let i = 0;
    console.log("runs" + i);
    i++;

    this.conversationService.getConversationsName(receiversName).subscribe(data => {
      this.conversationsName = data.message;
      let ws = new WebSocket(HANDSHAKE_URL);
      this._stomp = Stomp.over(ws);

      this._stomp.connect({}, (frame) => {
        this._stomp.subscribe("/queue/" + this.conversationsName, (message) => {
          this._messages.push(message.body);
        });
        this._stomp.subscribe("/errors", (error) => {
          this._errors.push(error.body);
        })
      }, (error) => {
        alert("Error " + error)
      });
    });
  }

  public sendMessage(message: string) {
    console.log("Conversation's name" + this.conversationsName);
    this._stomp.send("/app/" + this.conversationsName, {}, message);
  }

  get messages(): string[] {
    return this._messages;
  }

  get errors(): string[] {
    return this._errors;
  }
}
