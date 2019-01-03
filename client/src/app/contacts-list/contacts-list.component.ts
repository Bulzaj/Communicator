import {Component, OnInit} from '@angular/core';
import {ContactListService} from "../services/contact-list.service";
import {UserModel} from "../model/user.model";
import {ContactListModel} from "../model/contact-list.model";


@Component({
  selector: 'app-contacts-list',
  templateUrl: './contacts-list.component.html',
  styleUrls: ['./contacts-list.component.css']
})
export class ContactsListComponent implements OnInit {

  contactList: ContactListModel;
  receiver: UserModel;
  formVisibility: boolean;
  input: string;
  btnSign;
  error: string;
  message: string;

  constructor(private contactListService: ContactListService) {
    this.formVisibility = false;
    this.btnSign = '(+)'
  }

  getContacts() {
    this.contactList = this.contactListService.getContacts();
  }

  getReceiver(contactsName: string) {
    this.contactListService.getReceiver(contactsName).subscribe((data: UserModel) => {
      this.receiver = new UserModel(data.id, data.username, data.createdAt);
      this.contactListService.updateReceiverSubject(this.receiver);
    });
  }

  changeVisibility() {
    if (this.formVisibility == false) {
      this.formVisibility = true;
      this.btnSign = 'Hide'
    } else {
      this.formVisibility = false;
      this.btnSign = '(+)';
    }
  }

  addContact() {
    this.contactListService.addNewContact(this.input).subscribe(data => {
      let newContact: UserModel = new UserModel(data.id, data.username, data.createdAt);
      this.contactListService.updateContactListSubject(newContact);
      this.error = null;
      this.message = "Contact successfilly added";
    }, err => {
      this.error = err.error;
    })
  }

  ngOnInit() {
    this.getContacts();
  }

}
