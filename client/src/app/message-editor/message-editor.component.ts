import {Component, Input, OnInit} from '@angular/core';
import {MessageModel} from "../model/message.model";
import {WebsocketService} from "../services/websocket.service";
import {UserModel} from "../model/user.model";
import {ContactListService} from "../services/contact-list.service";
import {ConversrationService} from "../services/conversration.service";
import {ConversationModel} from "../model/conversation.model";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-message-editor',
  templateUrl: './message-editor.component.html',
  styleUrls: ['./message-editor.component.css']
})
export class MessageEditorComponent implements OnInit {

  messageBody: " ";
  message: MessageModel;
  receiver: UserModel;
  currentUser: UserModel;
  conversation: ConversationModel;

  constructor(private websocketService: WebsocketService,
              private contactListService: ContactListService,
              private conversationService: ConversrationService,
              private userService: UserService) {
  }

  ngOnInit() {
    this.getReciever();
    this.getConversation();
    this.getCurrentUser();
  }

  getReciever() {
    this.contactListService.getReceiverAsObservable().subscribe(data => {
      this.receiver = new UserModel(data.id, data.username, data.createdAt)
    });
  }

  getConversation() {
    this.conversationService.getConversationAsObservable().subscribe(data => {
      this.conversation = new ConversationModel(data.conversationsName, data.messages)
    })
  }

  getCurrentUser() {
    this.userService.getCurrentUser().subscribe(data => {
      this.currentUser = new UserModel(data.id, data.username, data.createdAt);
    })
  }

  sendMessage() {
    this.conversationService.sendMessage(this.receiver.username, this.messageBody).subscribe(data => {
      this.message = new MessageModel(data.sendersName, data.messageBody, data.createdAt);
      let obj = {
        sendersName: this.message.sendersName,
        messageBody: this.message.messageBody,
        createdAt: this.message.createdAt
      };
      this.websocketService.sendMessage(JSON.stringify(obj), this.conversation.conversationsName);
    });

  }

  isReceiver(): boolean {
    if (this.receiver) {
      return true;
    } return false;
  }
}
