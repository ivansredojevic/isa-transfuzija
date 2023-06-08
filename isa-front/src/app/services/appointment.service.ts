import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AppointmentDTO } from '../model/dto/appointment.dto';

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

    getOne(id: number): Observable<any> {
        const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + localStorage.getItem('token') });
        
        console.log(headers);
        return this.http.get<any>(
            this.resourceUrl + '/appointment/get/' + id,
            {
                headers: headers
            });
    }

    reserveAppointment(appointmentDto: AppointmentDTO): Observable<any> {
        const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + localStorage.getItem('token') });
        return this.http.post<any>(
            this.resourceUrl + '/appointment/reserve', 
            appointmentDto,
            {
                headers: headers
            });
    }

    getMyAppointmentsPageable(username: string, sort = 'id,asc', page: number, size: number): Observable<any> {
        const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + localStorage.getItem('token') });
        
        console.log(headers);
        return this.http.get<any>(
            this.resourceUrl + '/appointment/upcoming-by-user-pageable/' + username,
            {
                params: {
                    sort: sort,
                    page: page.toString(),
                    size: size.toString()
                },
                headers: headers
            });
    }

    getMyHistoryPageable(username: string, sort = 'id,asc', page: number, size: number): Observable<any> {
        const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + localStorage.getItem('token') });
        
        console.log(headers);
        return this.http.get<any>(
            this.resourceUrl + '/appointment/history-by-user-pageable/' + username,
            {
                params: {
                    sort: sort,
                    page: page.toString(),
                    size: size.toString()
                },
                headers: headers
            });
    }

    cancelAppointment(appointmentDto: AppointmentDTO): Observable<any> {
        const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + localStorage.getItem('token') });
        return this.http.post<any>(
            this.resourceUrl + '/appointment/cancel', 
            appointmentDto,
            {
                headers: headers
            });
    }

}
