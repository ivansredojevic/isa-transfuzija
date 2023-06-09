import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})

export class ApplicationUserService {

    private resourceUrl = `${environment.API_URL}`;

    constructor(private http: HttpClient) { }



    loadUser(username: string): Observable<any> {
        const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + localStorage.getItem('token') });
        
        return this.http.get<any>(
            this.resourceUrl + '/users/load-user/' + username,
            {
                headers: headers
            });
    }

}