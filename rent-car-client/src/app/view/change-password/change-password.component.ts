import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  changePasswordForm!: FormGroup;

  ngOnInit(): void {
    this.changePasswordForm = this.formBuilder.group({
      currentPassword: ['', [Validators.required, , Validators.minLength(6)]],
      newPassword: ['', [Validators.required, Validators.minLength(6)]],
      newPasswordConfirm: ['', [Validators.required]]
    }, 
    {   
      validator: this.passwordMatchValidator
    });
  }

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private messageService: MessageService
  ) {}

  passwordMatchValidator(formGroup: FormGroup) {
    const newPassword = formGroup.get('newPassword')?.value;
    const newPasswordConfirm = formGroup.get('newPasswordConfirm')?.value;
    if (newPassword !== newPasswordConfirm) {
      formGroup.get('newPasswordConfirm')?.setErrors({ mismatch: true });
    } else {
      formGroup.get('newPasswordConfirm')?.setErrors(null);
    }
  }

  onSubmit() {
    if(this.changePasswordForm.valid) {
      const changePasswordRequest = {
        currentPassword: this.changePasswordForm.value.currentPassword,
        newPassword: this.changePasswordForm.value.newPassword
      };

      const user = localStorage.getItem('user');
      const parseUser = JSON.parse(user!);

      this.userService.changePassword(parseUser.username, changePasswordRequest).subscribe({
        next: (res) => {
          this.messageService.add({
            severity: 'success',
            summary: 'Đổi mật khẩu thành công',
          });
        },
        error: (err) => {
          console.log(err);
          this.messageService.add({
            severity: 'error',
            summary: 'Mật khẩu hiện tại không đúng',
            detail: 'Vui lòng kiểm tra lại thông tin mật khẩu hiện tại!'
          });
        }
      });
    } 
  }
}
