import {Component, HostListener, OnInit} from '@angular/core';
import {User} from "../../models/user-details/user";
import {Subscription} from "rxjs";
import {UserService} from "../../services/user-details/user.service";
import {Role} from "../../models/user-details/role";
import {PageChangedEvent} from "ngx-bootstrap/pagination";

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {

  users:User[] = [];
  parameters:string[] = [];

  size:number = 7;
  totalElements:number = 0;
  direction:boolean = false;
  parameter:string;

  subscriptions:Subscription[] = [];

  constructor(private userService:UserService) {}

  ngOnInit() {
    this.loadParameters();
  }

  @HostListener('window:beforeunload')
  ngOnDestroy(): void {
    this.dispose();
  }

  dispose() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  loadParameters(){
    this.subscriptions.push(this.userService.getParameters().subscribe(parameters=>{
      this.parameters = parameters as string[];
      this.parameter = parameters[0];
      this.loadPage(0,this.size,this.direction,this.parameter);
    }))
  }

  loadPage(page:number, size:number, direction:boolean, parameter:string){
    this.subscriptions.push(this.userService.getPage(page,size,direction,parameter).subscribe(userPage=>{
      this.users = userPage.users as User[];
      this.totalElements = userPage.totalElements / this.size * 10 ;
    }))
  }

  getRoles(roles: Role[]):string {
    return roles.map(role=>role.role).join(',');
  }

  changeDirection(){
    this.direction = !this.direction;
  }

  sort(direction: boolean,parameter:string) {
    this.changeDirection();
    this.loadPage(0,this.size,this.direction,parameter);
  }

  pageChanged($event: PageChangedEvent) {
    this.loadPage($event.page - 1,this.size,this.direction,this.parameters[0]);
  }
}
