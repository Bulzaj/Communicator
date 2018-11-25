import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {MessageService} from "../services/message.service";
import {UserModel} from "../model/user.model";
import {SendedMessageModel} from "../model/sended-message.model";
import {ConversationHistoryService} from "../services/conversation-history.service";
import {MessageModel} from "../model/message.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  message: SendedMessageModel;
  receiver: UserModel;
  receiversName: string;
  messagesList: MessageModel[];

  constructor(private authService: AuthService,
              private messageService: MessageService,
              private conversationHistoryService: ConversationHistoryService) {

    this.message = new SendedMessageModel()
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  ngOnInit() {
  }

  send(): void {
    this.message.receiversName = this.receiversName;
    console.log(this.message);
    this.messageService.sendMessage(this.message).subscribe(data => {
      console.log(data)
    })
  }

  getMessages(): void {
    this.conversationHistoryService.getHistory(this.receiversName).subscribe(data => {
      this.messagesList = data.messages;
    })
  }

  receiveMessage($event) {
    this.receiver = $event;
    this.receiversName = this.receiver.username;
    this.getMessages()
  }
}
