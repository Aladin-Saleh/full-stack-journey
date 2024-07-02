import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly API_URL = 'http://localhost:8080';


  constructor(private http: HttpClient) { }


  register(userCredentials: { username: string, email: string, password: string }): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.post<any>(`${this.API_URL}/register`, userCredentials, { headers });
  }


  login(userCredentials: { username: string, password: string }): Observable<any> {

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post<any>(`${this.API_URL}/login`, userCredentials, { headers }).pipe(
      tap(
        (response) => {
          if (this.isBrowser()) {
            localStorage.setItem("accessToken", response.bearer);
            localStorage.setItem("refreshToken", response.refreshToken);
          }
        }
      )
    );
  }

  refreshToken(): Observable<any> {
    const refreshToken = this.getRefreshToken();
    if (refreshToken) {
      return this.http.post<any>(`${this.API_URL}/refresh`, { refreshToken: refreshToken }).pipe(
        tap(response => {
          if (this.isBrowser()) {
            localStorage.setItem('accessToken', response.bearer);
            localStorage.setItem('refreshToken', response.refreshToken);

          }
        })
      );
    }
    return new Observable(observer => observer.error('No refresh token available'));
  }


  logout() {
    if (this.isBrowser()) {
      localStorage.removeItem('accessToken');
      localStorage.removeItem('refreshToken');
      window.location.reload();
    }
  }

  getAccessToken(): string | null {
    return this.isBrowser() ? localStorage.getItem('accessToken') : null;
  }

  getRefreshToken(): string | null {
    return this.isBrowser() ? localStorage.getItem('refreshToken') : null;
  }

  isAuthenticated(): boolean {
    return !!this.getAccessToken();
  }


  private isBrowser(): boolean {
    return typeof window !== 'undefined' && typeof localStorage !== 'undefined';
  }
}
