import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CenterModel } from '../model/center.model';

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  private resourceUrl = `${environment.API_URL}`;
  
  constructor(private http: HttpClient) { }

  getAllPageable(sort = 'id,asc', page: number, size: number): Observable<any> {
    // const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization':  `Bearer ` + localStorage.getItem('token') });
    const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    console.log(headers);
    return this.http.get<any>(
      this.resourceUrl + '/center/all-pageable', 
      { params: { 
        sort: sort,
        page: page.toString(), 
        size: size.toString() }, 
      headers: headers });
  }

}
