import { Component, OnInit } from '@angular/core';
import { Task } from '../../../models/task.model';
import { TaskService } from '../../../services/task.service';
import { ToastService } from '../../../services/toast.service';

@Component({
  selector: 'app-tasks',
  imports: [],
  templateUrl: './tasks.component.html',
  styleUrl: './tasks.component.css'
})
export class TasksComponent implements OnInit{
  tasks : Task[] = []
  loading: boolean = true

  constructor(
      private taskService: TaskService,
      private toastService: ToastService,
    ) { }

  ngOnInit(): void {
    this.taskService.getUserTasks().subscribe(
      (response) => {
        console.log(response)
        this.tasks = response
      },
      (error) => {
        this.toastService.showError('There has been an Error!');
      },
      () => {
        this.loading = false;
      }
    );
    }

}
