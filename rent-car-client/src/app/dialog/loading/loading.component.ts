import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AnimationOptions } from 'ngx-lottie';
@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css'],
})
export class DialogLoadingComponent {
  options: AnimationOptions = {
    path: '/assets/car.json',
  };
  constructor(public dialogRef: MatDialogRef<DialogLoadingComponent>) {}
}
