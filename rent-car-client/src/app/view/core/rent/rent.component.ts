import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { DialogLoadingComponent } from 'src/app/dialog/loading/loading.component';
import { DialogSuccessComponent } from 'src/app/dialog/success/success.component';
import { AuthService } from 'src/app/service/auth.service';
import { PaymentService } from 'src/app/service/payment.service';
import { RentService } from 'src/app/service/rent.service';

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
  activeButton: string = 'VNPAY';
  dialogSucces: MatDialogRef<any> | undefined;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog,
    private rentService: RentService,
    private authService: AuthService
  ) {}

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
  booking() {
    this.dialogLoading = this.dialog.open(DialogLoadingComponent, {
      disableClose: true,
    });
    setTimeout(() => {
      this.dialogLoading!.close();
    }, 1000);
    this.authService.getUser().subscribe((user) => {
      const rentCarData = {
        userId: user.id,
        ownerId: this.rental.car.owner,
        carId: this.rental.car.id,
        rentalDate: new Date(),
        returnDate: this.rental.returnDate,
        pickupDate: this.rental.rentalDate,
        pickupLocation: this.rental.car.location,
        returnLocation: this.rental.car.location,
        rentalPrice: this.rental.rentalPrice,
        rentalStatus: 0,
        paymentStatus: 0,
        car: {
          id: this.rental.car.id,
        },
      };
      this.rentService.saveRentCar(rentCarData).subscribe({
        next: (res) => {
          this.dialogSucces = this.dialog.open(DialogSuccessComponent, {
            data: { message: 'Đặt thuê xe thành công' },
          });
          this.dialogSucces.afterClosed().subscribe(() => {
            this.router.navigate(['/mytrips/rented']);
          });
          // const payment = {
          //   orderCode: res.id,
          //   amount: res.rentalPrice,
          //   bankCode: this.activeButton,
          // };
          // this.paymentService.payment(payment).subscribe((url) => {
          //   window.location.href = url.paymentUrl;
          // });
        },
        error: (err) => {
          console.log(err);
        },
      });
    });
  }
}
