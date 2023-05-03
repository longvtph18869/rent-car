import { Component, ViewChild, ElementRef, OnInit } from '@angular/core';
import { CloudinaryService } from 'src/app/service/cloudinary.service';
import { UserService } from 'src/app/service/user.service';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  @ViewChild('fileInput') fileInput!: ElementRef;
  avatarDialogVisible: boolean = true;
  phoneVisible: boolean = false;
  emailVisible: boolean = false;
  infoVisible: boolean = false;
  avatarUrl: string = '';
  user: any;
  avatarFile!: File;

  constructor(
    private cloudinaryService: CloudinaryService,
    private userService: UserService,
    private authService: AuthService,
    ) {}

  ngOnInit(): void {
    this.authService.getUser().subscribe({
      next: (res) => {
        this.user = res;
        this.avatarUrl = this.user?.avatar ?? '';
      },
      error: (err) => {
        console.log(err);
      },
    })
  }

  showUpdatePhoneDialog() {
    this.phoneVisible = true;
  }

  showUpdateEmailDialog() {
    this.emailVisible = true;
  }

  showUpdateInfoDialog() {
    this.infoVisible = true;
  }

  selectAvatar() {
    this.fileInput.nativeElement.click();
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    this.avatarFile = file;
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.avatarUrl = reader.result as string;
    };
  }

  async saveAvatar() {
    try {
      const avatars = await this.cloudinaryService.uploadImage(this.avatarFile);
      if(this.user) {
        this.user.avatar = avatars;
        this.userService.saveUser(this.user!.id, this.user).subscribe({
          next: (res) => {
          },
          error: (err) => {
            console.log(err);
          },
        });
      }
      
    } catch (error) {
      console.log(error);
    }
  }
}
