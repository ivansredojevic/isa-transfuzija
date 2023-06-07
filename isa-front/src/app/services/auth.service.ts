import { Injectable } from '@angular/core';
import { AuthUserModel } from "../model/auth.user.model";
import { environment } from '../../environments/environment';
import { Subject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ApplicationUserModel } from '../model/applicationUser.model';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private resourceUrl = `${environment.API_URL}`;

  constructor(private http: HttpClient) { }

  getAuthHeader(): any {
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token")
    }
    return {
      headers: headers
    };
  }

  register(appUser: ApplicationUserModel) {
    return this.http.post(this.resourceUrl + "/users/register", appUser);
  }

  login(user: any): Observable<any>  {
    return this.http.post(this.resourceUrl + "/auth/generate-token", user);
  }

  getUsername() {
    let jwt = localStorage.getItem('token');
    if (jwt) {
      let jwtData = jwt.split('.')[1];
      let decodedJwtJsonData = window.atob(jwtData);
      console.log(JSON.parse(decodedJwtJsonData).sub);
      return JSON.parse(decodedJwtJsonData).sub;
    }
  }

  logout() {
    localStorage.removeItem('token');
  }

  authenticated() {
    return localStorage.getItem('token') != null;
  }

  changePassword(user: any): any {
    return this.http.put(this.resourceUrl + "/users/update-password" + user.id, user, this.getAuthHeader());
  }

  updateUser(user: any): any {
    return this.http.put(this.resourceUrl + "/users/update" + user.id, user, this.getAuthHeader());
  }

  deleteUser(user: any): any {
    return this.http.delete(this.resourceUrl + "/users/delete" + user.id, this.getAuthHeader());
  }

}
