import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {HttpService} from "./http.service";
import {UserModel} from "../model/user.model";
import {ContactListModel} from "../model/contact-list.model";

const CONTACTS_URL = "http://localhost:8080/api/contacts";

@Injectable({
  providedIn: 'root'
})
export class ContactListService {

  private contactList: ContactListModel;
  private receiverSubject: Subject<UserModel>;
  private contactListSubject: Subject<ContactListModel>;
  private receiverObservable: Observable<UserModel>;

  constructor(private http: HttpClient,
              private tokenStorageService: TokenStorageService,
              private httpService: HttpService) {

    this.contactList = new ContactListModel();
    this.contactListSubject = new Subject<ContactListModel>();
    this.receiverSubject = new Subject<UserModel>();
    this.receiverObservable = this.receiverSubject.asObservable();
    this.init()
  }

  private init() {
    let options = {
      headers: this.httpService.createAuthHeader(this.tokenStorageService.getToken())
    };

    this.http.get<ContactListModel>(CONTACTS_URL, options).subscribe((data: ContactListModel) => {
      data.contacts.forEach((receiver) => {
        this.contactList.addContact(new UserModel(receiver.id, receiver.username, receiver.createdAt));
      })
    });
  }

  public addNewContact(input: string): Observable<UserModel> {
    let options = {
      headers: this.httpService.createAuthHeader(this.tokenStorageService.getToken())
    };

    return this.http.post<UserModel>(CONTACTS_URL, input, options)
  }

  public updateReceiverSubject(newReceiver: UserModel) {
    this.receiverSubject.next(newReceiver)
  }

  public updateContactListSubject(newContact: UserModel) {
    this.contactList.addContact(newContact);
    this.contactListSubject.next(this.contactList);
  }

  public getReceiver(contactsName: string): Observable<UserModel> {
    let options = {
      headers: this.httpService.createAuthHeader(this.tokenStorageService.getToken())
    };

    return this.http.get<UserModel>(CONTACTS_URL + "/" + contactsName, options)
  }

  public getContacts(): ContactListModel{
    return this.contactList
  }

  public getReceiverAsObservable(): Observable<UserModel> {
    return this.receiverObservable;
  }

  public getContactListAsObservable(): Observable<ContactListModel> {
    return this.contactListSubject.asObservable();
  }
}
