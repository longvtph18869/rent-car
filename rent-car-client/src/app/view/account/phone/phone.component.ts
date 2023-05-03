import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { AuthService } from 'src/app/service/auth.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-phone',
  templateUrl: './phone.component.html',
  styleUrls: ['./phone.component.css']
})
export class PhoneComponent implements OnInit {
  phoneForm!: FormGroup;
  user: any;

  ngOnInit(): void {
    this.phoneForm = this.formBuilder.group({
      phoneNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
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
    if(this.phoneForm.valid) {
      if(this.user) {
        this.user.phoneNumber = this.phoneForm.value.phoneNumber;
        this.userService.saveUser(this.user!.id, this.user).subscribe({
          next: (res) => {
            this.messageService.add({
              severity: 'success',
              summary: 'Cập nhật số điện thoại thành công',
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
