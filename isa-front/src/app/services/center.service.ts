import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  private resourceUrl = `${environment.API_URL}`;
  
  
  constructor(private http: HttpClient) { }

  //   addCenter(center: any): Observable<any>{
  //     return  this.http.post<any>(this.apiHost + 'add', center, { headers: this.headers });
  //   }

  getAllPageable(page: number, size: number): Observable<any> {
    const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization':  `Bearer ` + localStorage.getItem('token') });
    console.log(headers);
    return this.http.get<any>(this.resourceUrl + '/center/all-pageable', { params: { page: page.toString(), size: size.toString() }, headers: headers });
  }

}
