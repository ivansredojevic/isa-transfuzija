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
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    constructor(private http: HttpClient) { }

    getFreePageable(username: string, sort = 'id,asc', page: number, size: number): Observable<any> {
        return this.http.get<any>(
            this.resourceUrl + '/appointment/free-pageable/' + username,
            {
                params: {
                    sort: sort,
                    page: page.toString(),
                    size: size.toString()
                },
                headers: this.headers
            });
    }

    getOne(id: number): Observable<any> {
        return this.http.get<any>(
            this.resourceUrl + '/appointment/get/' + id,
            {
                headers: this.headers
            });
    }

    reserveAppointment(appointmentDto: AppointmentDTO): Observable<any> {
        return this.http.post<any>(
            this.resourceUrl + '/appointment/reserve',
            appointmentDto,
            {
                headers: this.headers
            });
    }

    getMyAppointmentsPageable(username: string, sort = 'id,asc', page: number, size: number): Observable<any> {
        return this.http.get<any>(
            this.resourceUrl + '/appointment/upcoming-by-user-pageable/' + username,
            {
                params: {
                    sort: sort,
                    page: page.toString(),
                    size: size.toString()
                },
                headers: this.headers
            });
    }

    getMyHistoryPageable(username: string, sort = 'id,asc', page: number, size: number): Observable<any> {
        return this.http.get<any>(
            this.resourceUrl + '/appointment/history-by-user-pageable/' + username,
            {
                params: {
                    sort: sort,
                    page: page.toString(),
                    size: size.toString()
                },
                headers: this.headers
            });
    }

    cancelAppointment(appointmentDto: AppointmentDTO): Observable<any> {
        return this.http.post<any>(
            this.resourceUrl + '/appointment/cancel',
            appointmentDto,
            {
                headers: this.headers
            });
    }

}
