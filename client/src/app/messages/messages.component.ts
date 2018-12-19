import {Component, OnInit} from '@angular/core';
import {UserModel} from "../model/user.model";
import {WebsocketService} from "../services/websocket.service";
import {ContactListService} from "../services/contact-list.service";
import {ConversationModel} from "../model/conversation.model";
import {ConversrationService} from "../services/conversration.service";
import {MessageModel} from "../model/message.model";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  receiver: UserModel;
  sender: UserModel;
  conversation: ConversationModel;
  messages: string[] = [];

  constructor(private websocketService: WebsocketService,
              private contactListService: ContactListService,
              private conversationService: ConversrationService,
              private userService: UserService) {

  }

  private getReceiver() {
    this.contactListService.getReceiverAsObservable().subscribe(data => {
      this.receiver = new UserModel(data.id, data.username, data.createdAt);
      this.conversationService.init(data.username)
    });
  }

  private getConversation() {
    this.conversationService.getConversationAsObservable().subscribe(data => {
      this.conversation = new ConversationModel(data.conversationsName, data.messages);
      this.subscribe()
    })
  }

  private getSender() {
    this.userService.getCurrentUser().subscribe(data => {
        this.sender = new UserModel(data.id, data.username, data.createdAt);
      }
    )
  }

  private subscribe() {
    this.websocketService.subscribe(this.conversation.conversationsName, (message) => {
      message = new MessageModel(this.sender.username, message.messageBody, "just now");
      this.conversation.messages.push(message)
      this.conversationService.updataSubject(this.conversation)
    })
  }

  ngOnInit() {
    this.getReceiver();
    this.getConversation();
    this.getSender();
  }

  showReceiversName() {
    if (this.receiver) {
      return this.receiver.username;
    }
  }

  showMessages(): MessageModel[] {
    if (this.conversation) {
      return this.conversation.messages;
    }
  }

}
