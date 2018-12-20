import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import {MessageModel} from "../model/message.model";
import {ConversationModel} from "../model/conversation.model";

const HANDSHAKE_URL = "ws://localhost:8080/socket";

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private _stomp;
  private _errors: string[] = [];


  constructor() {
  }

  public connect() {
    if (!this._stomp) {
      let ws = new WebSocket(HANDSHAKE_URL);
      this._stomp = Stomp.over(ws);
      this._stomp.connect({}, (frame) => {
        console.log(frame);
      })
    }
  }

  public subscribe(conversationsName: string, onMessage: (message) => void) {
    if (this._stomp != null) {
      this._stomp.subscribe("/queue/" + conversationsName, onMessage);
    }
  }

  public sendMessage(message: string, conversationsName: string) {
    this._stomp.send("/app/" + conversationsName, {}, message);
  }

  get errors(): string[] {
    return this._errors;
  }
}
