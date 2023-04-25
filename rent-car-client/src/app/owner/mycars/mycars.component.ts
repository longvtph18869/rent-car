import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { CarService } from 'src/app/service/car.service';
import { OwnerService } from 'src/app/service/owner.service';

@Component({
  selector: 'app-mycars',
  templateUrl: './mycars.component.html',
  styleUrls: ['./mycars.component.css'],
})
export class MycarsComponent implements OnInit {
  mycars: any[] = [];
  manufacturers: any[] = [];
  ngOnInit(): void {
    const id = this.authService.getUserId();
    this.ownerService.myCars(id).subscribe({
      next: (res) => {
        this.mycars = res;
        console.log(this.mycars);
      },
      error: (err) => {
        console.log(err);
      },
    });
    this.carService.getAllManufacturers().subscribe({
      next: (res) => {
        this.manufacturers = res;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  constructor(
    private ownerService: OwnerService,
    private authService: AuthService,
    private carService: CarService
  ) {}
  getManufacturerName(manufacturerId: number): string {
    const manufacturer = this.manufacturers.find(
      (m) => m.id === manufacturerId
    );
    return manufacturer ? manufacturer.name : 'unknown';
  }
}
