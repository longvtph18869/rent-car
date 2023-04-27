import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MenuItem } from 'primeng/api';
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
  funtions!: MenuItem[];
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
    this.funtions = [
      {
        label: 'Sửa',
        icon: 'pi pi-pencil',
        command: () => {
          // this.update();
        },
      },
      {
        label: 'Xóa',
        icon: 'pi pi-times',
        command: () => {
          // this.delete();
        },
      },
    ];
  }
  constructor(
    private ownerService: OwnerService,
    private authService: AuthService,
    private carService: CarService,
    private dialog: MatDialog
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
}
