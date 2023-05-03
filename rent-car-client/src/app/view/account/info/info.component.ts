import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { AuthService } from 'src/app/service/auth.service';
import { MessageService } from 'primeng/api';
import { LocalDateTime } from '@js-joda/core';
// import '@js-joda/locale_en-us'; 

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {
  infoForm!: FormGroup;
  user: any;
  dateOfBirth: string = '';
  gender: string = '';

  ngOnInit(): void {
    this.infoForm = this.formBuilder.group({
      fullName: ['', [Validators.required]],
      gender: ['', [Validators.required]],
      dateOfBirth: ['', [Validators.required]]
    }, 
    {   
      validator: this.dateOfBirthValidator
    });
    this.user = this.authService.getUserValue();
    const date = new Date(this.user.dateOfBirth);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    const formattedDate = `${year}-${month}-${day}`;
    this.dateOfBirth = formattedDate;
    this.gender = this.user.gender === true ? '1' : '0';
  }

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private authService: AuthService
  ) {}

  dateOfBirthValidator(formGroup: FormGroup) {
    const dateOfBirth = formGroup.get('dateOfBirth')?.value;
    const convertDob = new Date(dateOfBirth);
    const currentDate = new Date();

    if (convertDob > currentDate) {
      formGroup.get('dateOfBirth')?.setErrors({ mismatch: true });
    } else {
      formGroup.get('dateOfBirth')?.setErrors(null);
    }
  }

  onSubmit() {
    if(this.infoForm.valid) {
      if(this.user) {
        this.user.fullName = this.infoForm.value.fullName;
        this.user.dateOfBirth = LocalDateTime.parse(this.infoForm.value.dateOfBirth + 'T00:00:00');
        this.user.gender = this.infoForm.value.gender === '1' ? true : false;
        this.userService.saveUser(this.user!.id, this.user).subscribe({
          next: (res) => {
            this.messageService.add({
              severity: 'success',
              summary: 'Cập nhật thông tin thành công',
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
