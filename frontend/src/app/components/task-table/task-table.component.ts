import {Component, OnDestroy, OnInit} from '@angular/core';
import {TaskService} from "../../services/task-details/task.service";
import {Subscription} from "rxjs";
import {PageChangedEvent} from "ngx-bootstrap/pagination";
import {User} from "../../models/user-details/user";
import {Task} from "../../models/task-details/task";
import {TaskViewModel} from "../../models/view-models/task-view-model";
import {UserService} from "../../services/user-details/user.service";

@Component({
  selector: 'app-task-table',
  templateUrl: './task-table.component.html',
  styleUrls: ['./task-table.component.css']
})
export class TaskTableComponent implements OnInit,OnDestroy {

  taskViewModels:TaskViewModel[] = [];
  taskViewModel:TaskViewModel;
  taskExecutors:User[] = [];
  parameters:string[] = [];

  page:number = 0;
  size:number = 7;
  totalElements:number = 0;
  direction:boolean = false;
  parameter:string;

  subscriptions:Subscription[] = [];

  constructor(private taskService:TaskService,
              private userService:UserService) { }

  ngOnInit() {
    this.loadParameters();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription=> subscription.unsubscribe())
  }

  loadParameters() {
    this.subscriptions.push(this.taskService.getParameters().subscribe(parameters=>{
      this.parameters = parameters as string[];
      this.loadPage(this.page,this.size,this.direction,this.parameters[0]);
    }))
  }

  loadPage(page:number, size:number, direction:boolean, parameter:string){
    this.subscriptions.push(this.taskService.getPage(page,size,direction,parameter).subscribe(taskPage=>{
      this.taskViewModels = taskPage.taskViewModels as TaskViewModel[];
      this.totalElements = taskPage.totalElements / this.size * 10 ;
    }))
  }

  pageChanged($event: PageChangedEvent) {
    this.page = $event.page - 1;
    this.loadPage(this.page,this.size,this.direction,this.parameters[0]);
  }

  changeDirection(){
    this.direction = !this.direction;
  }

  sort(direction: boolean,parameter:string) {
    this.parameter = parameter;
    this.changeDirection();
    this.loadPage(this.page,this.size,this.direction,this.parameter);
  }

  showTask(idTask: number) {
    this.loadTaskById(idTask);
    this.loadTaskExecutors(idTask);
  }

  private loadTaskById(idTask: number) {
    this.subscriptions.push(
      this.taskService
        .getTaskViewModelById(idTask)
        .subscribe(taskViewModel=>{
          this.taskViewModel = taskViewModel as TaskViewModel;
    }))
  }

  private loadTaskExecutors(idTask:number){
    this.subscriptions.push(this.userService
      .getAllByTaskId(idTask)
      .subscribe(users=>{
        this.taskExecutors = users as User[];
    }))
  }

  deleteTask(idTask: number) {
    this.subscriptions.push(this.taskService
      .delete(idTask)
      .subscribe(()=>{
        this.taskViewModel = null;
        this.loadPage(this.page,this.size,this.direction,this.parameter);
      }))
  }
}
