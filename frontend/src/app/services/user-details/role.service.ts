import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Role} from "../../models/user-details/role";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private url:string = '/api/roles'

  constructor(private http:HttpClient) { }

  public getRoles():Observable<Role[]>{
    return this.http.get<Role[]>(this.url + '/get-all')
  }
}
