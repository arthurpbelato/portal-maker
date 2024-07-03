import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {UserService} from "../../../service/user.service";

export const authGuard: CanActivateFn =  async (route, state) => {

  let userRoles: String[] | undefined = await inject(UserService).userRoles().toPromise();
  let acceptedRoles: String[] = route.data['roles'];
  let hasRole: boolean = false;

  acceptedRoles.forEach(role => {
    if (userRoles && userRoles.some((userRole) => userRole === role)) {
      hasRole = true
    } else {
      //FIXME toast de acesso negado
    }
  });

  return hasRole;
};

