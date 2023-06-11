import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { InsertComplaintDTO } from '../model/dto/insert.complaint.dto';

@Injectable({
    providedIn: 'root'
})

export class ComplaintService {
    
    private resourceUrl = `${environment.API_URL}`;
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    constructor(private http: HttpClient) { }

    getMyComplaintsPageable(username: string, sort = 'id,asc', page: number, size: number): Observable<any> {
        return this.http.get<any>(
            this.resourceUrl + '/complaint/user-pageable/' + username,
            {
                params: {
                    sort: sort,
                    page: page.toString(),
                    size: size.toString()
                },
                headers: this.headers
            });
    }

    addComplaint(complaint: InsertComplaintDTO): Observable<any> {
        return this.http.post<any>(
            this.resourceUrl + '/complaint/add',
            complaint,
            {
                headers: this.headers
            });
    }

}
