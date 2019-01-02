import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import {MessageModel} from "../model/message.model";

const HANDSHAKE_URL = "ws://localhost:8080/socket";

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private _stomp;
  private _errors: string[] = [];
  private _messages;


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
      this._messages = this._stomp.subscribe("/queue/" + conversationsName, onMessage) ;
    }
  }

  public sendMessage(message, conversationsName: string) {
    this._stomp.send("/app/" + conversationsName, {}, message);
  }

  public unsubscribe() {
    if (this._messages) {
      this._messages.unsubscribe();
      this._messages = null;
    }
  }

  get errors(): string[] {
    return this._errors;
  }
}
