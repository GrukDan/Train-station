import {Component, OnDestroy, OnInit} from '@angular/core';
import {TaskService} from "../../services/task-details/task.service";
import {Subscription} from "rxjs";
import {PageChangedEvent} from "ngx-bootstrap/pagination";
import {User} from "../../models/user-details/user";
import {Task} from "../../models/task-details/task";
import {TaskViewModel} from "../../models/view-models/task-view-model";

@Component({
  selector: 'app-task-table',
  templateUrl: './task-table.component.html',
  styleUrls: ['./task-table.component.css']
})
export class TaskTableComponent implements OnInit,OnDestroy {

  taskViewModels:TaskViewModel[] = [];

  taskViewModel:TaskViewModel;

  parameters:string[] = [];

  size:number = 7;
  totalElements:number = 0;
  direction:boolean = false;
  parameter:string;

  subscriptions:Subscription[] = [];

  constructor(private taskService:TaskService) { }

  ngOnInit() {
    this.loadParameters();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription=> subscription.unsubscribe())
  }

  loadParameters() {
    this.subscriptions.push(this.taskService.getParameters().subscribe(parameters=>{
      this.parameters = parameters as string[];
      this.loadPage(0,this.size,this.direction,this.parameters[0]);
    }))
  }

  loadPage(page:number, size:number, direction:boolean, parameter:string){
    this.subscriptions.push(this.taskService.getPage(page,size,direction,parameter).subscribe(taskPage=>{
      this.taskViewModels = taskPage.taskViewModels as TaskViewModel[];
      this.totalElements = taskPage.totalElements / this.size * 10 ;
    }))
  }

  pageChanged($event: PageChangedEvent) {
    this.loadPage($event.page - 1,this.size,this.direction,this.parameters[0]);
  }

  changeDirection(){
    this.direction = !this.direction;
  }

  sort(direction: boolean,parameter:string) {
    this.changeDirection();
    this.loadPage(0,this.size,this.direction,parameter);
  }

  showTask(idTask: number) {
    this.loadTaskById(idTask);
  }

  private loadTaskById(idTask: number) {
    this.subscriptions.push(
      this.taskService
        .getTaskViewModelById(idTask)
        .subscribe(taskViewModel=>{
          this.taskViewModel = taskViewModel as TaskViewModel;
    }))
  }

}
