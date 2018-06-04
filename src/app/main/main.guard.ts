import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthService} from "../auth/auth.service";

@Injectable({
  providedIn: 'root'
})
export class MainGuard implements CanActivate {

  constructor(
    private router: Router,
    private service: AuthService
  ){}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if(this.service.isAuthenticated()){
      return true;
    }

    this.router.navigate(['/auth/login']);
    return false;

  }
}
