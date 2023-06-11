import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private resourceUrl = `${environment.API_URL}`;

  constructor(private http: HttpClient, private router: Router) { }

  getAuthHeader(): any {
    const headers = {
      'Content-Type': 'application/json'
    }
    return {
      headers: headers
    };
  }

  login(user: any): Observable<any> {
    return this.http.post(this.resourceUrl + "/auth/generate-token", user);
  }

  sessionExpired(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/centers'],
      {
        queryParams: { sessionExpired: 'true' }
      });
  }

  getUsername() {
    let jwt = localStorage.getItem('token');
    if (jwt) {
      let jwtData = jwt.split('.')[1];
      let decodedJwtJsonData = window.atob(jwtData);
      return JSON.parse(decodedJwtJsonData).sub;
    }
  }

  getToken() {
    let jwt = localStorage.getItem('token');
    return jwt;
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login'])
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
