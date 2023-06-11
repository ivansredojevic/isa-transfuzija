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
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    constructor(private http: HttpClient) { }

    getOne(username: string): Observable<any> {
        return this.http.get<any>(
            this.resourceUrl + '/questionnaire/get/' + username,
            {
                headers: this.headers
            });
    }

    fillQuestionnaire(questionnaire: QuestionnaireModel): Observable<any> {
        return this.http.post<any>(
            this.resourceUrl + '/questionnaire/fill-questionnaire',
            questionnaire,
            {
                headers: this.headers
            });
    }
}