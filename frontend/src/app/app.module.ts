import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {AlertModule} from "ngx-bootstrap/alert";
import { HeaderComponent } from './components/header/header.component';
import { UserTableComponent } from './components/user-table/user-table.component';
import { RatingTableComponent } from './components/rating-table/rating-table.component';
import { CreateUserModalComponent } from './components/modal-components/create-user-modal/create-user-modal.component';
import { CreateTaskModalComponent } from './components/modal-components/create-task-modal/create-task-modal.component';
import { TaskTableComponent } from './components/task-table/task-table.component';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import {CarouselModule} from "ngx-bootstrap/carousel";
import { UserPageComponent } from './components/user-page/user-page.component';
import {ModalModule} from "ngx-bootstrap/modal";
import {PaginationModule} from "ngx-bootstrap/pagination";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    UserTableComponent,
    RatingTableComponent,
    CreateUserModalComponent,
    CreateTaskModalComponent,
    TaskTableComponent,
    WelcomePageComponent,
    UserPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AlertModule.forRoot(),
    CarouselModule.forRoot(),
    ModalModule.forRoot(),
    PaginationModule.forRoot(),
    HttpClientModule,
  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
