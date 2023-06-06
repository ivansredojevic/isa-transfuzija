import { Injectable } from '@angular/core';
import { AuthUserModel } from "../model/auth.user.model";
import { environment } from '../../environments/environment';
import { Subject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class AuthService {

  private resourceUrl = `${environment.API_URL}`;

  constructor(private http: HttpClient) { }

  getAuthHeader() : any {

    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token")
    }

    return {
      headers: headers
    };
  }
  
  register(user: any) {
    return this.http.post(this.resourceUrl + "/users/register", user);
  }

  login(user: any) : any {
    return this.http.post(this.resourceUrl + "/auth/generate-token", user);
  }

  changePassword(user: any) : any {
    return this.http.put(this.resourceUrl + "/users/update-password" + user.id, user, this.getAuthHeader());
  }

  updateUser(user: any) : any {
    return this.http.put(this.resourceUrl + "/users/update" + user.id, user, this.getAuthHeader());
  }

  deleteUser(user: any) : any {
    return this.http.delete(this.resourceUrl + "/users/delete" + user.id, this.getAuthHeader());
  }
}
