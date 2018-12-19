import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {WebsocketService} from "../services/websocket.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private authService: AuthService,
              private websocketService: WebsocketService) {
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  ngOnInit() {
    this.websocketService.connect()
  }
}
