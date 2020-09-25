import {Injectable} from "@angular/core";
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import {AuthService} from "../services/auth/auth.service";


@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.authService.isAuthenticated()) {
      const authReq = req.clone({
        headers: new HttpHeaders({
          'Authorization': `Bearer ${window.btoa(this.authService.getToken())}`
        })
      });
      return next.handle(authReq);
    } else {
      return next.handle(req);
    }
  }
}
