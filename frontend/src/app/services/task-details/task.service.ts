import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {User} from "../../models/user-details/user";
import {Observable} from "rxjs";
import {Task} from "../../models/task-details/task";
import {UserPage} from "../../models/page-models/user-page";
import {TaskPage} from "../../models/page-models/task-page";
import {logger} from "codelyzer/util/logger";
import {TaskViewModel} from "../../models/view-models/task-view-model";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private url: string = '/api/tasks';

  constructor(private http: HttpClient) {
  }

  public save(task: Task): Observable<Task> {
    logger.info("" + task);
    return this.http.put<Task>(this.url + '/save', task)
  }

  public getParameters(): Observable<string[]> {
    return this.http.get<string[]>(this.url + '/get-parameters');
  }

  public getTaskViewModelById(idTask: number): Observable<TaskViewModel> {
    return this.http.get<TaskViewModel>(this.url + `/${idTask}`);
  }

  public getPage(page: number = 0,
                 size: number = 5,
                 direction: boolean = false,
                 parameter: string = "id_task"): Observable<TaskPage> {
    return this.http.get<TaskPage>(
      this.url + '/get-page',
      {
        params: new HttpParams()
          .set('page', page.toString())
          .set('size', size.toString())
          .set('direction', String(direction))
          .set('parameter', parameter)
      });
  }

  public delete(idTask: number): Observable<{}> {
    return this.http.delete(this.url + `/${idTask}`)
  }
}
