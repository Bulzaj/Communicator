import { Component, OnInit } from '@angular/core';
import {ContactListService} from "../services/contact-list.service";

@Component({
  selector: 'app-contacts-list',
  templateUrl: './contacts-list.component.html',
  styleUrls: ['./contacts-list.component.css']
})
export class ContactsListComponent implements OnInit {

  contactList = [];

  constructor(private contactListService: ContactListService) { }

  private getContacts() {
    this.contactListService.getContacts().subscribe(data => {
      this.contactList = data.contacts;
    })
  }
  ngOnInit() {
    this.getContacts();
  }

}
