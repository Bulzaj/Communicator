import {MessageModel} from "./message.model";

export class ConversationModel {

  private _conversationsName: string;
  private _messages: MessageModel[];


  constructor(conversationsName: string, messages: MessageModel[]) {
    this._conversationsName = conversationsName;
    this._messages = messages;
  }

  get conversationsName(): string {
    return this._conversationsName;
  }

  set conversationsName(value: string) {
    this._conversationsName = value;
  }

  get messages(): MessageModel[] {
    return this._messages;
  }

  set messages(value: MessageModel[]) {
    this._messages = value;
  }
}
