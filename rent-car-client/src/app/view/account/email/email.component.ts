import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { AuthService } from 'src/app/service/auth.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements OnInit {
  emailForm!: FormGroup;
  user: any;

  ngOnInit(): void {
    this.emailForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.pattern(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/)]]
    });
    this.user = this.authService.getUserValue();
  }

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private authService: AuthService
  ) {}

  onSubmit() {
    if(this.emailForm.valid) {
      if(this.user) {
        this.user.email = this.emailForm.value.email;
        this.userService.saveUser(this.user!.id, this.user).subscribe({
          next: (res) => {
            this.messageService.add({
              severity: 'success',
              summary: 'Cập nhật email thành công',
            });
          },
          error: (err) => {
            console.log(err);
          },
        });
      }
    } 
  }
}
