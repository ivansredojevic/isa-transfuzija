import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse, HttpEvent } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable, tap } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {


    constructor(private authService: AuthService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<unknown>> {

        let token = localStorage.getItem('token');

        if (token && !request.headers.get('Authorization')) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${token}`
                }
            });
        }
        
        return next.handle(request).pipe(
            tap(
              (event: HttpEvent<any>) => { },
              (error: any) => {
                if (error instanceof HttpErrorResponse) {
                  if (error.status === 401 || error.status === 403) {
                    this.authService.sessionExpired();
                  }
                }
              }
            )
          );

    }

}