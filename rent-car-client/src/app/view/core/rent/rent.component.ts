import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { DialogLoadingComponent } from 'src/app/dialog/loading/loading.component';

@Component({
  selector: 'app-rent',
  templateUrl: './rent.component.html',
  styleUrls: ['./rent.component.css'],
})
export class RentComponent implements OnInit {
  rental: any;
  images: any[] = [];
  responsiveOptions: any[] = [];
  dialogLoading: MatDialogRef<any> | undefined;
  selectedOption: string = 'payOnPickup';
  selectedMethod!: string | null;
  constructor(private route: ActivatedRoute, private dialog: MatDialog) {}

  ngOnInit() {
    this.dialogLoading = this.dialog.open(DialogLoadingComponent, {
      disableClose: true,
    });
    setTimeout(() => {
      this.dialogLoading!.close();
    }, 1000);
    this.route.queryParams.subscribe((params) => {
      this.rental = JSON.parse(params['rental']);
      this.images = this.rental.car.carImages;
    });
  }
  onPaymentOptionChange() {
    this.selectedMethod = null;
  }
  loadImage() {
    this.responsiveOptions = [
      {
        breakpoint: '1024px',
        numVisible: 5,
      },
      {
        breakpoint: '768px',
        numVisible: 3,
      },
      {
        breakpoint: '560px',
        numVisible: 1,
      },
    ];
  }
}
