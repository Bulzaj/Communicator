import {UserModel} from "./user.model";

export class ContactListModel {

  contacts: UserModel[] = [];

  addContact(contact: UserModel) {
    this.contacts.push(contact)
  }

  getContact(i: number) {
    return this.contacts[i]
  }

}
