import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
import { MessageService } from 'primeng/api';
import { DialogLoadingComponent } from 'src/app/dialog/loading/loading.component';
import { DialogSuccessComponent } from 'src/app/dialog/success/success.component';
import { AuthService } from 'src/app/service/auth.service';
import { CarService } from 'src/app/service/car.service';
import { CloudinaryService } from 'src/app/service/cloudinary.service';
declare const mapboxgl: any;
@Component({
  selector: 'app-register-car',
  templateUrl: './register-car.component.html',
  styleUrls: ['./register-car.component.css'],
})
export class RegisterCarComponent implements OnInit, AfterViewInit {
  map: any;
  enums: any = {};
  manufacturers: any = [];
  latitude: any;
  longitude: any;
  location: any;
  dialogLoading: MatDialogRef<any> | undefined;
  dialogSucces: MatDialogRef<any> | undefined;
  @ViewChild('mapContainer', { static: false }) mapContainer: any;
  yearList: number[] = [];
  form = this._formBuilder.group({
    licensePlates: [
      '',
      [
        Validators.required,
        Validators.pattern(/^[0-9]{2}[A-Z]-[0-9]{3,4}\.[0-9]{2}$/i),
      ],
    ],
    name: ['', Validators.required],
    manufacturerId: ['', Validators.required],
    yearOfManufacture: ['', Validators.required],
    type: ['', Validators.required],
    color: ['', Validators.required],
    description: ['', Validators.required],
  });
  formSecond = this._formBuilder.group({
    rentalPrice: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
    location: ['', Validators.required],
  });
  formThird = this._formBuilder.group({});
  constructor(
    private _formBuilder: FormBuilder,
    private http: HttpClient,
    private CarService: CarService,
    private cloudinaryService: CloudinaryService,
    private authService: AuthService,
    private dialog: MatDialog,
    private router: Router,
    private messageService: MessageService
  ) {
    const currentYear = new Date().getFullYear();
    for (let year = 1980; year <= currentYear; year++) {
      this.yearList.push(year);
    }
  }
  ngOnInit(): void {
    this.CarService.enums().subscribe({
      next: (res) => {
        this.enums = res;
        console.log(this.enums);
      },
      error: (err) => {
        console.log(err);
      },
    });
    this.CarService.getAllManufacturers().subscribe({
      next: (res) => {
        this.manufacturers = res;
        console.log(this.manufacturers);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  ngAfterViewInit(): void {
    this.map = new mapboxgl.Map({
      accessToken:
        'pk.eyJ1IjoibG9uZ3BoMTg4NjkiLCJhIjoiY2xnZHFwdzR4MWxlYjNocGNza2IybXlzbyJ9.ZZj7I092TSffFAFTbu7y5w',
      container: this.mapContainer.nativeElement,
      style: 'mapbox://styles/mapbox/streets-v12',
      center: [105.81610684403171, 21.018853843837093],
      zoom: 15,
    });
    const geocoder = new MapboxGeocoder({
      accessToken:
        'pk.eyJ1IjoibG9uZ3BoMTg4NjkiLCJhIjoiY2xnZHFwdzR4MWxlYjNocGNza2IybXlzbyJ9.ZZj7I092TSffFAFTbu7y5w',
      mapboxgl: mapboxgl,
      marker: false,
      bbox: [102.17, 8.54, 109.3, 23.38],
      proximity: { longitude: 105, latitude: 21 },
      placeholder:
        'Food House 85 Thái Hà, 85 Thái Hà, Hà Nội, 116700, Việt Nam',
    });
    let marker = new mapboxgl.Marker();
    geocoder.on('result', (event) => {
      const result = event.result;
      const lng = result.center[0];
      const lat = result.center[1];
      marker.remove();
      marker.setLngLat([lng, lat]).addTo(this.map);
      const input = document.getElementById('location') as HTMLInputElement;
      input!.value = result.place_name;
      this.formSecond.get('location')?.setValue(input.value);
      this.location = result.place_name;
      this.latitude = lat;
      this.longitude = lng;
    });
    this.map.addControl(geocoder);
  }

  images: any[] = [];
  imagesCar: File[] = [];
  onFileSelected(event: any) {
    const files: File[] = event.target.files;
    for (const file of files) {
      this.imagesCar.push(file);
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const image = { url: e.target.result };
        this.images.push(image);
      };
      reader.readAsDataURL(file);
    }
  }

  removeImage(image: any) {
    const index = this.images.indexOf(image);
    if (index >= 0) {
      this.images.splice(index, 1);
    }
  }
  async onSubmit() {
    if (this.imagesCar.length < 3) {
      this.messageService.add({
        severity: 'error',
        summary: 'Lỗi',
        detail: 'Vui lòng upload tối thiểu 3 ảnh',
      });
    } else {
      this.dialogLoading = this.dialog.open(DialogLoadingComponent, {
        disableClose: true,
      });
      try {
        this.authService.decodeToken();
        const carImages = await this.cloudinaryService.uploadImages(
          this.imagesCar
        );
        const carData = {
          licensePlates: this.form.value.licensePlates,
          name: this.form.value.name,
          yearOfManufacture: this.form.value.yearOfManufacture,
          color: this.form.value.color,
          type: this.form.value.type,
          rentalPrice: this.formSecond.value.rentalPrice,
          description: this.form.value.description,
          manufacturerId: this.form.value.manufacturerId,
          location: this.location,
          latitude: this.latitude,
          longitude: this.longitude,
          carImages: carImages,
          owner: this.authService.getUserId(),
        };
        console.log(carData);
        this.CarService.resisterCar(carData).subscribe({
          next: (res) => {
            this.dialogSucces = this.dialog.open(DialogSuccessComponent, {
              data: { message: 'Thêm xe thành công' },
            });
            this.dialogLoading?.close();
            this.dialogSucces.afterClosed().subscribe(() => {
              this.router.navigate(['mycars']);
            });
          },
          error: (err) => {
            console.log(err);
          },
        });
      } catch (error) {
        console.log(error);
      }
    }
  }
}
