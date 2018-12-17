import {Component, Input, OnInit} from '@angular/core';
import {MessageModel} from "../model/message.model";
import {WebsocketService} from "../services/websocket.service";
import {UserModel} from "../model/user.model";
import {ContactListService} from "../services/contact-list.service";

@Component({
  selector: 'app-message-editor',
  templateUrl: './message-editor.component.html',
  styleUrls: ['./message-editor.component.css']
})
export class MessageEditorComponent implements OnInit {

  messageBody: " ";
  message: MessageModel;
  receiver: UserModel;

  constructor(private websocketService: WebsocketService,
              private contactListService: ContactListService) {
  }

  ngOnInit() {
    this.getReciever();
  }

  getReciever() {
    this.contactListService.getReceiverAsObservable().subscribe(data => {
      this.receiver = new UserModel(data.id, data.username, data.createdAt)
    });
  }

  sendMessage() {
    this.websocketService.sendMessage(this.messageBody);
  }

  isReceiverChoosed(): boolean {
    if (this.receiver) {
      return true;
    } return false;
  }
}
