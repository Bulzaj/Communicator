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

  constructor(private contactListService: ContactListService) {
  }

  getContacts() {
    this.contactList = this.contactListService.getContacts();
  }

  getReceiver(contactsName: string) {
    this.contactListService.getReceiver(contactsName).subscribe((data: UserModel) => {
      this.receiver = new UserModel(data.id, data.username, data.createdAt);
      this.contactListService.updateSubject(this.receiver);
    });
  }

  ngOnInit() {
    this.getContacts();
  }

}
