import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
import { MessageService } from 'primeng/api';
import { DialogLoadingComponent } from 'src/app/dialog/loading/loading.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  latitude: any;
  longitude: any;
  dialogLoading: MatDialogRef<any> | undefined;
  pickupDate: Date = new Date();
  minpickupDate: Date = new Date();
  returnDate: Date = new Date();
  minreturnDate: Date = new Date();
  geocoder: any;
  constructor(
    private dialog: MatDialog,
    private router: Router,
    private messageService: MessageService
  ) {}
  ngOnInit(): void {
    this.dialogLoading = this.dialog.open(DialogLoadingComponent, {
      disableClose: true,
    });
    setTimeout(() => {
      this.dialogLoading!.close();
    }, 2000);
    this.returnDate.setDate(this.returnDate.getDate() + 1);
    this.minreturnDate.setDate(this.minreturnDate.getDate() + 1);
    this.geocoder = new MapboxGeocoder({
      accessToken:
        'pk.eyJ1IjoibG9uZ3BoMTg4NjkiLCJhIjoiY2xnZHFwdzR4MWxlYjNocGNza2IybXlzbyJ9.ZZj7I092TSffFAFTbu7y5w',
      marker: false,
      bbox: [102.17, 8.54, 109.3, 23.38],
      proximity: { longitude: 105, latitude: 21 },
      placeholder: 'Nhập địa chỉ bạn muốn tìm kiếm',
    });
    this.geocoder.on('result', (ev: { result: { center: any[] } }) => {
      this.latitude = ev.result.center[1];
      this.longitude = ev.result.center[0];
    });
    this.geocoder.addTo(document.getElementById('geocoderhome')!);
  }
  filterCars() {
    if (this.returnDate <= this.pickupDate) {
      this.returnDate = new Date(this.pickupDate.getTime() + 86400000);
    }
  }
  search() {
    if (this.latitude == undefined && this.longitude == undefined) {
      this.messageService.add({
        severity: 'error',
        summary: 'Lỗi',
        detail: 'Vui lòng nhập địa điểm',
      });
    } else {
      const queryParams = {
        latitude: this.latitude,
        longitude: this.longitude,
        pickupDate: this.pickupDate,
        returnDate: this.returnDate,
      };
      this.router.navigate(['/find'], { queryParams });
    }
  }
}
