import { Component } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { CarService } from 'src/app/service/car.service';
import { RentService } from 'src/app/service/rent.service';

@Component({
  selector: 'app-lease',
  templateUrl: './lease.component.html',
  styleUrls: ['./lease.component.css'],
})
export class LeaseComponent {
  lease: any;
  constructor(
    private rentService: RentService,
    private authService: AuthService,
    private carService: CarService
  ) {}
  ngOnInit(): void {
    const user = this.authService.getUserId();
    this.rentService.getRentalsForOwner(user).subscribe({
      next: (res) => {
        this.lease = res;
        console.log(res);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  getSeverity(status: number): any {
    switch (status) {
      case 1:
        return { value: 'Đã đồng ý', color: 'warning' };
      case 2:
        return { value: 'Đã thanh toán', color: 'success' };
      case 3:
        return { value: 'Đang thuê', color: 'teal-200' };
      case 4:
        return { value: 'Đã thuê', color: 'bluegray-200' };
      case 5:
        return { value: 'Đã hủy', color: 'gray' };
    }
  }
  accept(rentCarId: any) {
    this.rentService.updateRentalStatus(rentCarId, 1).subscribe(
      () => {
        this.ngOnInit();
      },
      (error) => {
        console.error(error);
      }
    );
  }
  cancel(rentCarId: any) {
    this.rentService.updateRentalStatus(rentCarId, 5).subscribe(
      () => {
        this.ngOnInit();
      },
      (error) => {
        console.error(error);
      }
    );
  }
}
