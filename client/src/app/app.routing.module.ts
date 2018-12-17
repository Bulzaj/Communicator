import {RouterModule, Routes} from "@angular/router";
import {RegistrationComponent} from "./registration/registration.component";
import {LoginComponent} from "./login/login.component";
import {NgModule} from "@angular/core";
import {HomeComponent} from "./home/home.component";
import {IsAuthenticatedGuard} from "./is-authenticated-guard.service";
import {IsUnauthenticatedGuard} from "./is-unauthenticated-guard";
import {WebsocketTestComponent} from "./websocket-test/websocket-test.component";

const routes : Routes = [
  {path: 'register', component: RegistrationComponent},
  {path: 'login', component: LoginComponent, canActivate: [IsUnauthenticatedGuard]},
  {path: 'home', component: HomeComponent, canActivate: [IsAuthenticatedGuard]},
  {path: 'ws-test', component: WebsocketTestComponent}
];

@NgModule({
  imports : [
    RouterModule.forRoot(routes)
  ],
  exports : [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule {

}
