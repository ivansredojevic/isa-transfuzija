import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class AppointmentService {

    private resourceUrl = `${environment.API_URL}`;

    constructor(private http: HttpClient) { }

    getFreePageable(sort = 'id,asc', page: number, size: number): Observable<any> {
        const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + localStorage.getItem('token') });
        
        console.log(headers);
        return this.http.get<any>(
            this.resourceUrl + '/appointment/free-pageable',
            {
                params: {
                    sort: sort,
                    page: page.toString(),
                    size: size.toString()
                },
                headers: headers
            });
    }

}
