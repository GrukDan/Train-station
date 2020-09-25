import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {JwtRequest} from "../../models/auth/jwt-request";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  LOCAL_STORAGE_NAME = 'authenticatedUser'
  private url: string = "/api/auth"

  constructor(private http: HttpClient) {

  }

  public isAuthenticated(): boolean {
    return localStorage.getItem(this.LOCAL_STORAGE_NAME) != null;
  }

  public logIn(jwtRequest:JwtRequest):Observable<string>{
    return this.http.post<string>(this.url + '/auth',jwtRequest);
  }

  public logOut() {
    localStorage.removeItem(this.LOCAL_STORAGE_NAME);
  }

  public getToken(): string {
    const token = localStorage.getItem(this.LOCAL_STORAGE_NAME);
    return  token == null ? '' : token;
  }
}
