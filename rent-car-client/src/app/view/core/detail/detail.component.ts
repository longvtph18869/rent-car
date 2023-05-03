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
  loading = true;
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
          this.loading = false;
          this.onDateSelect(null);
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }
  onDateSelect(event: any) {
    if (this.returnDate <= this.pickupDate) {
      this.returnDate = new Date(this.pickupDate.getTime() + 86400000);
    }
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
      diffDays: this.diffDays,
      servicePrice: this.servicePrice,
    };
    this.router.navigate(['/rent'], {
      queryParams: { rental: JSON.stringify(rental) },
    });
  }
  save(url: string) {
    localStorage.setItem('redirectUrl', url);
  }
}
