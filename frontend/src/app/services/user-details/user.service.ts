import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {User} from "../../models/user-details/user";
import {Observable, throwError} from "rxjs";
import {catchError, retry} from "rxjs/operators";
import {UserPage} from "../../models/page-models/user-page";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string = '/api/users';

  constructor(private http: HttpClient) {
  }

  public getPage(page:number = 0,
                 size:number = 5,
                 direction:boolean = false,
                 parameter:string = null):Observable<UserPage>{
    return this.http.get<UserPage>(
      this.url + '/get-page',
      {params:new HttpParams()
          .set('page',page.toString())
          .set('size',size.toString())
          .set('direction',String(direction))
          .set('parameter',parameter)
      });
  }

  public save(user: User): Observable<User> {
    return this.http.put<User>(this.url + '/save', user)
    //   .pipe(
    //     retry(1),
    //     catchError(this.handleError)
    //   );
    // ;
  }

  // handleError(error) {
  //   let errorMessage = '';
  //   if (error.error instanceof ErrorEvent) {
  //     // client-side error
  //     errorMessage = `Error: ${error.error.message}`;
  //   } else {
  //     // server-side error
  //     errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
  //   }
  //   window.alert(errorMessage);
  //   return throwError(errorMessage);
  // }


  getParameters():Observable<string[]> {
    return this.http.get<string[]>(this.url + '/get-parameters');
  }

  getExperts():Observable<User[]> {
    return this.http.get<User[]>(this.url + '/get-experts');
  }
}
