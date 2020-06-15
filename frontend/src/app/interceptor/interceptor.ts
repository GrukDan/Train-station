// import {Injectable} from "@angular/core";
// import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
// import {Observable} from "rxjs";
// import {finalize, tap} from "rxjs/operators";
//
// @Injectable()
// export class LoadingIndicatorInterceptor implements HttpInterceptor {
//
//   constructor(public loadingIndicatorService: LoadingIndicatorService) {}
//
//   intercept (req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
//     // emit onStarted event before request execution
//     this.loadingIndicatorService.onStarted(req);
//
//     return next
//       .handle(req)
//       // emit onFinished event after request execution
//       .pipe(
//         finalize(() => this.loadingIndicatorService.onFinished(req)), tap( r => {
//         })
//       );
//   }
// }
//
// export const LoadingInterceptor = {
//   provide: HTTP_INTERCEPTORS,
//   useClass: LoadingIndicatorInterceptor,
//   multi: true,
// };
