import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import {
  ConfirmComponent,
  ConfirmDialogModel,
} from 'src/app/dialog/confirm/confirm.component';
import { DialogLoadingComponent } from 'src/app/dialog/loading/loading.component';
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
  displayCars: any[] = [];
  manufacturers: any[] = [];
  selectedCar: any = [];
  dialogLoading: MatDialogRef<any> | undefined;
  ngOnInit(): void {
    this.dialogLoading = this.dialog.open(DialogLoadingComponent, {
      disableClose: true,
    });
    const id = this.authService.getUserId();
    this.ownerService.myCars(id).subscribe({
      next: (res) => {
        this.mycars = res;
        this.displayCars = this.mycars;
        console.log(this.mycars);
        this.dialogLoading?.close();
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
    private carService: CarService,
    private dialog: MatDialog,
    private router: Router
  ) {}
  getManufacturerName(manufacturerId: number): string {
    const manufacturer = this.manufacturers.find(
      (m) => m.id === manufacturerId
    );
    return manufacturer ? manufacturer.name : 'unknown';
  }
  filterCarsByStatus(event: Event) {
    const target = event.target as HTMLSelectElement;
    const selectedStatus = parseInt(target.value, 10);
    if (selectedStatus === 4) {
      this.displayCars = this.mycars;
    } else {
      this.displayCars = this.mycars.filter(
        (car) => car.status === selectedStatus
      );
    }
  }
  detail(car: any) {
    this.router.navigate(['/owner/mycars/', car.id]);
  }
  delete(car: any) {
    const message = `Bạn có chắc muốn ngừng hoạt động xe ` + car.name + `?`;
    const dialogData = new ConfirmDialogModel('Confirm Action', message);
    const dialogRef = this.dialog.open(ConfirmComponent, {
      maxWidth: '400px',
      data: dialogData,
    });

    dialogRef.afterClosed().subscribe((dialogResult) => {
      if (dialogResult) {
        this.dialogLoading = this.dialog.open(DialogLoadingComponent, {
          disableClose: true,
        });
        this.ownerService.updateStatus(car.id, 3).subscribe({
          next: (res) => {
            this.dialogLoading?.close();
            this.ngOnInit();
          },
          error: (err) => {
            console.log(err);
          },
        });
      }
    });
  }
  comeBack(car: any) {
    const message = `Bạn có chắc muốn hoạt động lại xe` + car.name + `?`;
    const dialogData = new ConfirmDialogModel('Confirm Action', message);
    const dialogRef = this.dialog.open(ConfirmComponent, {
      maxWidth: '400px',
      data: dialogData,
    });

    dialogRef.afterClosed().subscribe((dialogResult) => {
      if (dialogResult) {
        this.dialogLoading = this.dialog.open(DialogLoadingComponent, {
          disableClose: true,
        });
        this.ownerService.updateStatus(car.id, 0).subscribe({
          next: (res) => {
            this.dialogLoading?.close();
            this.ngOnInit();
          },
          error: (err) => {
            console.log(err);
          },
        });
      }
    });
  }
  toggleMenu(event: any) {
    var menu = event.target.parentElement.querySelector('.menu');
    menu.classList.toggle('menu-show');
  }
}
