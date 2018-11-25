import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ContactListService} from "../services/contact-list.service";
import {stringify} from "querystring";
import {UserModel} from "../model/user.model";

@Component({
  selector: 'app-contacts-list',
  templateUrl: './contacts-list.component.html',
  styleUrls: ['./contacts-list.component.css']
})
export class ContactsListComponent implements OnInit {

  contactList: Array<UserModel>;
  contact: UserModel;

  @Output()
  messageEvent = new EventEmitter<UserModel>();

  constructor(private contactListService: ContactListService) { }

  private getContacts() {
    this.contactListService.getContacts().subscribe(data => {
      this.contactList = data.contacts;
      console.log(this.contactList)
    })
  }

  ngOnInit() {
    this.getContacts();
  }

  getContact(i: number) {
    this.contact = this.contactList[i];
    this.messageEvent.emit(this.contact)
  }

}
