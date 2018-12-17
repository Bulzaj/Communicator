export class MessageModel {

  sendersName: string;
  messageBody: string;
  conversationsUniqueName: string;
  createdAt: string;


  constructor(sendersName: string, messageBody: string, conversationsUniqueName: string, createdAt: string) {
    this.sendersName = sendersName;
    this.messageBody = messageBody;
    this.conversationsUniqueName = conversationsUniqueName;
    this.createdAt = createdAt;
  }
}
