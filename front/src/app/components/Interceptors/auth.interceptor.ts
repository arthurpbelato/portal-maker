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

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private userService: UserService, private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let token = localStorage.getItem('token');

    if (request.url.includes("internal")) {
      if (token != null) {
        request =  request.clone({
          setHeaders: {
            Authorization: 'Bearer ' + token
          },
        })
      } else {
        this.router.navigate(["login"]);
      }
    }

    return next.handle(request);
  }
}
