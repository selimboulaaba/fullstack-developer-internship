import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TaskRoutingModule } from './task-routing.module';
import { RouterOutlet } from '@angular/router';
import { TaskComponent } from './task.component';


@NgModule({
  declarations: [
    TaskComponent
  ],
  imports: [
    CommonModule,
    TaskRoutingModule,
    RouterOutlet
  ]
})
export class TaskModule { }
