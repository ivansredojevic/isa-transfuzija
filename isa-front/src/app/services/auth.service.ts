import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ApplicationUserDTO } from '../model/dto/applicationUser.dto';

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

  login(user: any): Observable<any> {
    return this.http.post(this.resourceUrl + "/auth/generate-token", user);
  }

  getUsername() {
    let jwt = localStorage.getItem('token');
    if (jwt) {
      let jwtData = jwt.split('.')[1];
      let decodedJwtJsonData = window.atob(jwtData);
      return JSON.parse(decodedJwtJsonData).sub;
    }
  }

  logout() {
    sessionStorage.removeItem('canDonate');
    localStorage.removeItem('token');
  }

  getUserDetails(username: string) {
    return this.http.get<any>(this.resourceUrl + '/users/get/' + username, this.getAuthHeader());
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
