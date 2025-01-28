import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Task } from '../models/task.model';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private baseUrl = 'http://localhost:8081/task'

  constructor(private http: HttpClient, private authService: AuthService) {  }

  private getAuthHeaders() {
    const token = this.authService.getToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }

  getUserTasks(): Observable<Task[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<Task[]>(`${this.baseUrl}`, { headers });
  }

  getTaskById(taskId: number): Observable<Task> {
    const headers = this.getAuthHeaders();
    return this.http.get<Task>(`${this.baseUrl}/${taskId}`, { headers });
  }

  addTask(task: Task): Observable<Task> {
    const headers = this.getAuthHeaders();
    return this.http.post<Task>(`${this.baseUrl}`, task, { headers });
  }

  updateTask(taskId: number, task: Task): Observable<Task> {
    const headers = this.getAuthHeaders();
    return this.http.put<Task>(`${this.baseUrl}/${taskId}`, task, { headers });
  }

  deleteTaskById(taskId: number): Observable<Task> {
    const headers = this.getAuthHeaders();
    return this.http.delete<Task>(`${this.baseUrl}/${taskId}`, { headers });
  }

}
