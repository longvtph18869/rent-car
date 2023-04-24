import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup, Validators } from '@angular/forms';
import { HeaderComponent } from '../core/header/header.component';
import { UserService } from 'src/app/service/user.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern(/^\d{10}$|^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/)]],
      fullName: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      password_confirm: ['', [Validators.required]]
    }, 
{
      validator: this.passwordMatchValidator
    });
  }

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private headerComponent: HeaderComponent
  ) {}

  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')?.value;
    const confirmPassword = formGroup.get('password_confirm')?.value;
    if (password !== confirmPassword) {
      formGroup.get('password_confirm')?.setErrors({ mismatch: true });
    } else {
      formGroup.get('password_confirm')?.setErrors(null);
    }
  }

  onSubmit() {
    if(this.registerForm.valid) {
      const user = {
        username: this.registerForm.value.username,
        fullName: this.registerForm.value.fullName,
        password: this.registerForm.value.password
      };

      this.userService.checkUserNameWasUsed(user).subscribe({
        next: (res) => {
          if(res === true) {
            this.messageService.add({
              severity: 'error',
              summary: 'Số điện thoại hoặc email đã tồn tại',
              detail: 'Vui lòng nhập lại thông tin số điện thoại hoặc email',
            });
            console.log(res === true);
            console.log(res);
          } else {
            this.userService.resisterUser(user).subscribe({
              next: () => {
                this.messageService.add({
                  severity: 'success',
                  summary: 'Đăng ký tài khoản thành công',
                  detail: 'Bạn có thể đăng nhập ngay bây giờ',
                });
              },
              error: (err) => {
                console.log(err);
              }
            });
          }
        },
        error: (err) => {
          console.log(err);
        }
      });
    } 
  }
}
