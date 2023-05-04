import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { CarService } from 'src/app/service/car.service';
import { PaymentService } from 'src/app/service/payment.service';
import { RentService } from 'src/app/service/rent.service';

@Component({
  selector: 'app-rented',
  templateUrl: './rented.component.html',
  styleUrls: ['./rented.component.css'],
})
export class RentedComponent implements OnInit {
  rentCars: any;
  constructor(
    private rentService: RentService,
    private authService: AuthService,
    private carService: CarService,
    private paymentService: PaymentService
  ) {}
  ngOnInit(): void {
    const user = this.authService.getUserId();
    this.rentService.getRentalsForUser(user).subscribe({
      next: (res) => {
        this.rentCars = res;
        console.log(res);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  getSeverity(status: number): any {
    switch (status) {
      case 0:
        return { value: 'Chờ duyệt', color: 'danger' };
      case 1:
        return { value: 'Đã duyệt', color: 'warning' };
      case 2:
        return { value: 'Đã thanh toán', color: 'success' };
      case 3:
        return { value: 'Đang thuê', color: 'teal-200' };
      case 4:
        return { value: 'Đã thuê', color: 'bluegray-200' };
      case 5:
        return { value: 'Đã hủy', color: 'bluegray-200' };
    }
  }
  payment(rent: any) {
    const payment = {
      orderCode: rent.id,
      amount: rent.rentalPrice,
      bankCode: 'VNPAY',
    };
    this.paymentService.payment(payment).subscribe((url) => {
      window.location.href = url.paymentUrl;
    });
  }
}
