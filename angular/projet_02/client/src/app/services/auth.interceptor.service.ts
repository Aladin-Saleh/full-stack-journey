import { HttpEvent, HttpHandlerFn, HttpHeaders, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';


// https://angular.fr/http/interceptor
export function authInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  const auth = inject(AuthService);
  const accessToken = auth.getAccessToken();

  if (!accessToken) {
    return next(req);
  }

  const headers = new HttpHeaders({
    Authorization: `Bearer ${accessToken}`
  });

  const newRequest = req.clone({
    headers
  });

  return next(newRequest);



}
