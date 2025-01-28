import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'https://blueblood-backend.up.railway.app/auth'

  constructor(private http: HttpClient) { }

  signIn(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/signin`, user);
  }

  signUp(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/signup`, user);
  }
}
