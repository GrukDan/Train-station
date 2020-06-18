import {Role} from "./role";
import {Task} from "../task-details/task";

export class User {
  idUser:number;
  name:string;
  surname:string;
  email:string;
  roles:Role[];
  tasks:Task[]

  constructor() {
    this.roles = []
    this.tasks = []
  }
}
