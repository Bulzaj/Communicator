export class MessageModel {

  sendersName: string;
  messageBody: string;
  createdAt: string;


  constructor(sendersName: string, messageBody: string,  createdAt: string) {
    this.sendersName = sendersName;
    this.messageBody = messageBody;
    this.createdAt = createdAt;
  }
}
