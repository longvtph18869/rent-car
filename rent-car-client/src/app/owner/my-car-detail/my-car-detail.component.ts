import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
import { DialogLoadingComponent } from 'src/app/dialog/loading/loading.component';
import { DialogSuccessComponent } from 'src/app/dialog/success/success.component';
import { AuthService } from 'src/app/service/auth.service';
import { CarService } from 'src/app/service/car.service';
import { CloudinaryService } from 'src/app/service/cloudinary.service';
import { OwnerService } from 'src/app/service/owner.service';
import { MycarsComponent } from '../mycars/mycars.component';
import { MessageService } from 'primeng/api';
declare const mapboxgl: any;
@Component({
  selector: 'app-my-car-detail',
  templateUrl: './my-car-detail.component.html',
  styleUrls: ['./my-car-detail.component.css'],
})
export class MyCarDetailComponent implements OnInit {
  car: any = [];
  dialogLoading: MatDialogRef<any> | undefined;
  dialogSucces: MatDialogRef<any> | undefined;
  manufacturers: any = [];
  selectedManufacturerIndex: any;
  yearList: number[] = [];
  enums: any = {};
  map: any;
  latitude: any;
  longitude: any;
  location: any;
  showDialog: boolean = true;
  images: any[] = [];
  @ViewChild('mapContainer', { static: false }) mapContainer: any;
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
    rentalPrice: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
    location: ['', Validators.required],
  });
  constructor(
    private _formBuilder: FormBuilder,
    private carService: CarService,
    private dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private cloudinaryService: CloudinaryService,
    private ownerService: OwnerService,
    private mycarsComponent: MycarsComponent,
    private messageService: MessageService
  ) {
    const currentYear = new Date().getFullYear();
    for (let year = 1980; year <= currentYear; year++) {
      this.yearList.push(year);
    }
  }
  ngOnInit(): void {
    this.dialogLoading = this.dialog.open(DialogLoadingComponent, {
      disableClose: true,
    });
    this.carService.enums().subscribe({
      next: (res) => {
        this.enums = res;
        console.log(this.enums);
      },
      error: (err) => {
        console.log(err);
      },
    });
    const carId = this.route.snapshot.paramMap.get('id');
    if (carId != null) {
      this.carService.findCar(carId).subscribe({
        next: (res) => {
          this.car = res;
          this.images = this.car.carImages;
          this.latitude = this.car.latitude;
          this.longitude = this.car.longitude;
          this.location = this.car.location;
          console.log(this.car);
          this.form.patchValue({
            licensePlates: this.car.licensePlates,
            name: this.car.name,
            manufacturerId: this.car.manufacturerId,
            yearOfManufacture: this.car.yearOfManufacture,
            type: this.car.type,
            color: this.car.color,
            description: this.car.description,
            rentalPrice: this.car.rentalPrice,
            location: this.car.location,
          });
          this.form.get('licensePlates')!.disable();
          this.map = new mapboxgl.Map({
            accessToken:
              'pk.eyJ1IjoibG9uZ3BoMTg4NjkiLCJhIjoiY2xnZHFwdzR4MWxlYjNocGNza2IybXlzbyJ9.ZZj7I092TSffFAFTbu7y5w',
            container: this.mapContainer.nativeElement,
            style: 'mapbox://styles/mapbox/streets-v12',
            center: [this.longitude, this.latitude],
            zoom: 15,
          });
          console.log(this.longitude);
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
          marker.setLngLat([this.longitude, this.latitude]).addTo(this.map);
          geocoder.on('result', (event) => {
            const result = event.result;
            const lng = result.center[0];
            const lat = result.center[1];
            marker.remove();
            marker.setLngLat([lng, lat]).addTo(this.map);
            const input = document.getElementById(
              'location'
            ) as HTMLInputElement;
            input!.value = result.place_name;
            // this.formSecond.get('location')?.setValue(input.value);
            this.location = result.place_name;
            this.latitude = lat;
            this.longitude = lng;
          });
          this.map.addControl(geocoder);

          this.carService.getAllManufacturers().subscribe({
            next: (rest) => {
              this.manufacturers = rest;
              this.selectedManufacturerIndex = this.manufacturers.findIndex(
                (manufacturer: { id: any }) =>
                  manufacturer.id === this.car.manufacturerId
              );
            },
            error: (err) => {
              console.log(err);
            },
          });
          this.dialogLoading?.close();
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }
  closeComponent() {
    this.mycarsComponent.ngOnInit();
    this.router.navigate(['/owner/mycars']);
  }
  imagesCar: File[] = [];
  onFileSelected(event: any) {
    const files: File[] = event.target.files;
    for (const file of files) {
      this.imagesCar.push(file);
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const image = e.target.result;
        this.images.push(image);
      };
      reader.readAsDataURL(file);
    }
    console.log(this.imagesCar);
  }

  removeImage(image: any) {
    const index = this.images.indexOf(image);
    if (index >= 0) {
      this.images.splice(index, 1);
    }
    console.log(this.images);
  }
  async onSubmit() {
    if (this.images.length < 3) {
      this.messageService.add({
        severity: 'error',
        summary: 'Lỗi',
        detail: 'Vui lòng upload tối thiểu 3 ảnh',
      });
    } else {
      if (this.form.valid) {
        this.dialogLoading = this.dialog.open(DialogLoadingComponent, {
          disableClose: true,
        });
        try {
          this.authService.decodeToken();
          const carImages = await this.cloudinaryService.uploadImages(
            this.imagesCar
          );
          const images = this.images.filter((img: any) =>
            img.includes('http://res.cloudinary.com')
          );
          this.images = [...images, ...carImages];
          const carData = {
            id: this.car.id,
            licensePlates: this.car.licensePlates,
            name: this.form.value.name,
            yearOfManufacture: this.form.value.yearOfManufacture,
            color: this.form.value.color,
            type: this.form.value.type,
            rentalPrice: this.form.value.rentalPrice,
            description: this.form.value.description,
            manufacturerId: this.form.value.manufacturerId,
            location: this.location,
            latitude: this.latitude,
            longitude: this.longitude,
            carImages: this.images,
            owner: this.authService.getUserId(),
          };
          console.log(carData);
          this.ownerService.updateCar(carData).subscribe({
            next: (res) => {
              this.dialogSucces = this.dialog.open(DialogSuccessComponent, {
                data: { message: 'Lưu thành công' },
              });
              this.dialogLoading?.close();
              this.ngOnInit();
            },
            error: (err) => {
              console.log(err);
            },
          });
        } catch (error) {
          console.log(error);
        }
      } else {
        console.log('Vui lòng nhập đầy đủ thông tin');
      }
    }
  }
}
