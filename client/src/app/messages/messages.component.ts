import {Component, Input, OnInit} from '@angular/core';
import {UserModel} from "../model/user.model";
import {WebsocketService} from "../services/websocket.service";
import {ContactListService} from "../services/contact-list.service";

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  receiver: UserModel;
  messages: string[];

  constructor(private websocketService: WebsocketService,
              private contactListService: ContactListService) {
  }

  ngOnInit() {
    this.getReceiver()
  }

  getReceiver() {
    this.contactListService.getReceiverAsObservable().subscribe(data => {
      this.receiver = new UserModel(data.id, data.username, data.createdAt);
      this.clearMessagesArray();
      this.connect();
    })
  }

  showReceiversName() {
    if (this.receiver) {
      return this.receiver.username;
    }
  }

  clearMessagesArray() {
    this.messages = [];
  }

  connect() {
    if (this.receiver != null) {
      this.websocketService.connect(this.receiver.username);
      this.messages = this.websocketService.messages;
    }
  }

}
