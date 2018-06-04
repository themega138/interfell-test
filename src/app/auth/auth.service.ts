import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from "rxjs/Rx";
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private _http: HttpClient
  ) { }

  login(user: any): Observable<any>{
    return this._http.post(environment.auth.login, user).pipe(
      map( (r: any) => {
        console.log(r);
        localStorage.setItem('token',r.token);
        return r;
    }));
  }

  isAuthenticated(): boolean {
    if(localStorage.getItem('token')){
      return true;
    } else {
      return false;
    }
  }

  logout() {
    localStorage.removeItem('token');
  }
}
