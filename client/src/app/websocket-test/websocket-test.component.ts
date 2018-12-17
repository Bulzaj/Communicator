import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-websocket-test',
  templateUrl: './websocket-test.component.html',
  styleUrls: ['./websocket-test.component.css']
})
export class WebsocketTestComponent implements OnInit {

  ws: any;
  showConversation: boolean = false;
  greetings: string[] = [];
  disabled: boolean;
  name: string;

  constructor() { }

  connect() {
    let socket = new WebSocket("ws://localhost:8080/socket")
    this.ws = Stomp.over(socket);
    let that = this;
    this.ws.connect({}, (frame) => {
      this.ws.subscribe("/errors", (message) => {
        alert("Error " + message.body)
      });
      this.ws.subscribe("/conversation/reply", (message) => {
        console.log(message);
        this.showGreeting(message.body)
      });
      this.disabled = true;
    },
      (error) => {
      alert("STOMP error " + error)
      });
  }

  disconnect() {
    if (this.ws != null) {
      this.ws.close();
    }
    this.setConnected(false);
    console.log("Disconnected");
  }

  sendName() {
    let data = JSON.stringify({
      'name': this.name
    });
    this.ws.send("/app/message", {}, data)
  }

  showGreeting(message) {
    this.showConversation = true;
    this.greetings.push(message);
  }

  setConnected(connected) {
    this.disabled = connected;
    this.showConversation = connected;
    this.greetings = [];
  }

  ngOnInit() {
  }

}
