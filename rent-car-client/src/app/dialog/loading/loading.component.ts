import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css'],
})
export class DialogLoadingComponent {
  constructor(public dialogRef: MatDialogRef<DialogLoadingComponent>) {}
}
