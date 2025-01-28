import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';
import { Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { ToastService } from '../../../services/toast.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [
    CardModule,
    RouterModule,
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  userForm: FormGroup = new FormGroup({});

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private toastService: ToastService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    console.log(this.userForm.value)
    if (this.userForm.valid) {
      const userData = this.userForm.value;
      this.authService.signIn(userData).subscribe(
        (response) => {
          this.toastService.showSuccess('Signed In Successfully!');
          console.log(response);
          // this.router.navigate(['/admin', response.id]);
        },
        (error) => {
          this.toastService.showError('There has been an Error!');
          console.error(error);
        }
      );
    }
  }
}