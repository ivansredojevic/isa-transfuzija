import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Center } from '../model/center';

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  apiHost: string = 'http://localhost:8089/api/center/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }




//   addCenter(center: any): Observable<any>{
//     return  this.http.post<any>(this.apiHost + 'add', center, { headers: this.headers });
//   }

  getAllPageable(page: number, size: number): Observable<any>{
    return  this.http.get<any>(this.apiHost + 'allPageable', { params: { page: (--page).toString(), size: size.toString() }});
  }



}
