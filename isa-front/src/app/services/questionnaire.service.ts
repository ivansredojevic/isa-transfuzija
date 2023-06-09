import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { QuestionnaireModel } from '../model/questionnaire.model';

@Injectable({
    providedIn: 'root'
})

export class QuestionnaireService {

    private resourceUrl = `${environment.API_URL}`;

    constructor(private http: HttpClient) { }

    getOne(username: string): Observable<any> {
        const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + localStorage.getItem('token') });
        
        return this.http.get<any>(
            this.resourceUrl + '/questionnaire/get/' + username,
            {
                headers: headers
            });
    }

    fillQuestionnaire(questionnaire: QuestionnaireModel): Observable<any> {
        const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + localStorage.getItem('token') });
        return this.http.post<any>(
            this.resourceUrl + '/questionnaire/fill-questionnaire', 
            questionnaire,
            {
                headers: headers
            });
    }
}