import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {UserService} from "../../../service/user.service";
import {Router} from "@angular/router";

export const InterceptorSkipHeader = 'SkipInterceptor';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private userService: UserService, private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> | any {
    if (!request.headers.has(InterceptorSkipHeader) ) {
      if (request.url.includes("internal")) {
        let token = localStorage.getItem('token');
        if (token != null) {
          this.validateToken();
          request =  request.clone({ setHeaders: { Authorization: 'Bearer ' + token } });
        } else {
          this.router.navigate(["login"]);
        }
      }
      return next.handle(request);
    } else {
      return next.handle(request.clone({ setHeaders: { Authorization: 'Bearer ' + localStorage.getItem('token') } }));
    }
  }

  validateToken() {
    this.userService.validateToken().subscribe(
      resp => {},
      error => {
        if (error.status != 200) {
          this.router.navigate(["login"]) //TODO Change to refresh??
        }
      }
    );
  }

}
