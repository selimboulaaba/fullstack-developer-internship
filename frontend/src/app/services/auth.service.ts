import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8081/auth'

  constructor(private http: HttpClient) { }

  signIn(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, user);
  }

  signUp(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/signup`, user);
  }

  storeToken(token: string) {
    localStorage.setItem('token', token);
  }

  logout() {
    localStorage.removeItem('token')
  }

  getToken() {
    return localStorage.getItem('token')
  }
}
