import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ApplicationUserDTO } from '../model/dto/applicationUser.dto';

@Injectable({
    providedIn: 'root'
})

export class ApplicationUserService {

    private resourceUrl = `${environment.API_URL}`;
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    constructor(private http: HttpClient) { }

    loadUser(username: string): Observable<any> {
         return this.http.get<any>(
            this.resourceUrl + '/users/load-user/' + username,
            {
                headers: this.headers
            });
    }

    register(appUser: ApplicationUserDTO): Observable<any> {
         return this.http.post(this.resourceUrl + "/users/register", appUser,
            {
                headers: this.headers
            });
    }

    conditionsEvaluate(username: string): Observable<any> {
        return this.http.get<any>(
            this.resourceUrl + '/users/evaluate-conditions/' + username,
            {
                headers: this.headers
            });
    }

    activate(token: string): Observable<any> {
        return this.http.get(this.resourceUrl + "/users/activate/" + token,
            {
                headers: this.headers
            });
    }
}