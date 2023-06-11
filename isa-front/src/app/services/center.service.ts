import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  private resourceUrl = `${environment.API_URL}`;
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getAllPageable(sort = 'id,asc', page: number, size: number): Observable<any> {
    return this.http.get<any>(
      this.resourceUrl + '/center/all-pageable',
      {
        params: {
          sort: sort,
          page: page.toString(),
          size: size.toString()
        },
        headers: this.headers
      });
  }

}
