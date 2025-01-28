import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ToastService {
  constructor(private toastr: ToastrService) {}

  showSuccess(message: string) {
    this.toastr.success('Success!', message);
  }

  showError(message: string) {
    this.toastr.error('SucErrorcess!', message);
  }

  showInfo(message: string) {
    this.toastr.info('Info!', message);
  }

  showWarn(message: string) {
    this.toastr.warning('Warning!', message);
  }
}
