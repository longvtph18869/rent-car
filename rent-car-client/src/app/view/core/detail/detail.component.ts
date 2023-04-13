import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarService } from 'src/app/service/car.service';
@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css'],
})
export class DetailComponent implements OnInit {
  car: any = {};
  servicePrice: number = 0.1;
  pickupDate: Date = new Date();
  minpickupDate: Date = new Date();
  returnDate: Date = new Date();
  minreturnDate: Date = new Date();
  diffDays: number = 0;
  sum: number = 0;
  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private carService: CarService,
    private router: Router
  ) {}
  ngOnInit() {
    this.returnDate.setDate(this.returnDate.getDate() + 1);
    this.minreturnDate.setDate(this.minreturnDate.getDate() + 1);
    const carId = this.route.snapshot.paramMap.get('id');
    if (carId != null) {
      this.carService.findCar(carId).subscribe({
        next: (res) => {
          this.car = res;
          this.onDateSelect(null);
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }
  onDateSelect(event: any) {
    const timeDiff = Math.abs(
      this.pickupDate.getTime() - this.returnDate.getTime()
    );
    this.diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24 + 1));
    this.sum = this.diffDays * this.car.rentalPrice;
  }
  rent() {
    const rental = {
      car: this.car,
      rentalDate: this.pickupDate,
      returnDate: this.returnDate,
      rentalPrice: this.sum,
    };
    this.router.navigate(['/rent'], {
      queryParams: { rental: JSON.stringify(rental) },
    });
  }
}
