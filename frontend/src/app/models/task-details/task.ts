import {User} from "../user-details/user";

export class Task {
  idTask:number;
  taskCreator:number;
  dateOfCreation:Date;
  taskName:string;
  taskCode:string;
  description:string;
  status:number;
  users:User[];

  constructor() {
    this.users = [];
  }
}
