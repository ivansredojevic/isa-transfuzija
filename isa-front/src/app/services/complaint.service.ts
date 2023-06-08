import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AppointmentModel } from '../model/appointment.model';
import { InsertComplaintDTO } from '../model/dto/insert.complaint.dto';

@Injectable({
    providedIn: 'root'
})

export class ComplaintService {
    private resourceUrl = `${environment.API_URL}`;

    constructor(private http: HttpClient) { }

    getMyComplaintsPageable(username: string, sort = 'id,asc', page: number, size: number): Observable<any> {
        const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + localStorage.getItem('token') });
        
        console.log(headers);
        return this.http.get<any>(
            this.resourceUrl + '/complaint/user-pageable/' + username,
            {
                params: {
                    sort: sort,
                    page: page.toString(),
                    size: size.toString()
                },
                headers: headers
            });
    }

    addComplaint(complaint: InsertComplaintDTO): Observable<any> {
        const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + localStorage.getItem('token') });
        return this.http.post<any>(
            this.resourceUrl + '/complaint/add', 
            complaint,
            {
                headers: headers
            });
    }

}
