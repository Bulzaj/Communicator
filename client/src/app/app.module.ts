import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AppRoutingModule} from "./app.routing.module";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {HomeComponent} from "./home/home.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {HttpModule} from "@angular/http";
import {IsAuthenticatedGuard} from "./is-authenticated-guard.service";
import {IsUnauthenticatedGuard} from "./is-unauthenticated-guard";
import {ContactsListComponent} from "./contacts-list/contacts-list.component";
import {MessagesComponent} from "./messages/messages.component";
import {MessageEditorComponent} from "./message-editor/message-editor.component";
import {WebsocketTestComponent} from "./websocket-test/websocket-test.component";



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    NavbarComponent,
    ContactsListComponent,
    MessagesComponent,
    MessageEditorComponent,
    HomeComponent,
    WebsocketTestComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    HttpModule
  ],
  providers: [IsAuthenticatedGuard, IsUnauthenticatedGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
