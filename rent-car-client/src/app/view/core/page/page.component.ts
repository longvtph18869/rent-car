import { Component, OnInit } from '@angular/core';
import { CarService } from 'src/app/service/car.service';
import { MoneyPipePipe } from 'src/app/money-pipe.pipe';
import MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DialogLoadingComponent } from 'src/app/dialog/loading/loading.component';
import { ActivatedRoute } from '@angular/router';
declare const mapboxgl: any;
@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.css'],
})
export class PageComponent implements OnInit {
  cars: any[] = [];
  filteredCars: any[] = [];
  pickupDate: Date = new Date();
  minpickupDate: Date = new Date();
  returnDate: Date = new Date();
  minreturnDate: Date = new Date();
  sorts: any[] = [];
  prices: number[] = [0, 100];
  show: boolean = true;
  map: any;
  latitude: any = 21.018853843837093;
  longitude: any = 105.81610684403171;
  loading = true;
  markers: mapboxgl.Marker[] = [];
  selectedSort: any;
  selectedTwoSeater = false;
  selectedFourSeater = false;
  selectedFiveSeater = false;
  selectedSixSeater = false;
  selectedSevenSeater = false;
  selectedSixteenSeater = false;
  vehicles = [
    {
      id: 'vt_3',
      name: 'vt_3',
      seats: 2,
      imageUrl: 'https://n1-cstg.mioto.vn/m/vehicle-types/mf-4-mini.png',
    },
    {
      id: 'vt_1',
      name: 'vt_1',
      seats: 4,
      imageUrl: 'https://n1-cstg.mioto.vn/m/vehicle-types/mf-4-sedan.png',
    },
    {
      id: 'vt_4',
      name: 'vt_4',
      seats: 5,
      imageUrl: 'https://n1-cstg.mioto.vn/m/vehicle-types/mf-4-hatchback.png',
    },
    {
      id: 'vt_5',
      name: 'vt_5',
      seats: 6,
      imageUrl: 'https://n1-cstg.mioto.vn/m/vehicle-types/mf-5-suv.png',
    },
    {
      id: 'vt_2',
      name: 'vt_2',
      seats: 7,
      imageUrl: 'https://n1-cstg.mioto.vn/m/vehicle-types/mf-7-suv.png',
    },
    {
      id: 'vt_6',
      name: 'vt_6',
      seats: 16,
      imageUrl: 'https://n1-cstg.mioto.vn/m/vehicle-types/mf-7-mpv.png',
    },
  ];

  constructor(
    private carService: CarService,
    private moneyPipe: MoneyPipePipe,
    private dialog: MatDialog,
    private route: ActivatedRoute
  ) {
    this.sorts = [
      { name: 'Khoảng cách gần nhất', value: 1 },
      { name: 'Giá thấp nhất', value: 2 },
      { name: 'Giá cao nhất', value: 3 },
      { name: 'Đánh giá tốt nhất', value: 4 },
    ];
  }
  dialogLoading: MatDialogRef<any> | undefined;
  ngOnInit(): void {
    this.dialogLoading = this.dialog.open(DialogLoadingComponent, {
      disableClose: true,
    });
    setTimeout(() => {
      this.dialogLoading!.close();
    }, 2000);
    this.returnDate.setDate(this.returnDate.getDate() + 1);
    this.minreturnDate.setDate(this.minreturnDate.getDate() + 1);
    // this.carService.getAllCars().subscribe((data) => {
    //   this.cars = data;
    // });
    this.route.queryParams.subscribe((params) => {
      const latitudeParam = params['latitude'];
      if (latitudeParam) {
        this.latitude = Number(latitudeParam);
      }
      const longitudeParam = params['longitude'];
      if (longitudeParam) {
        this.longitude = Number(longitudeParam);
      }
      const pickupDateParam = params['pickupDate'];
      if (pickupDateParam) {
        this.pickupDate = new Date(pickupDateParam);
      }
      const returnDateParam = params['returnDate'];
      if (returnDateParam) {
        this.returnDate = new Date(returnDateParam);
      }
    });
    this.filterCars();
    this.map = new mapboxgl.Map({
      accessToken:
        'pk.eyJ1IjoibG9uZ3BoMTg4NjkiLCJhIjoiY2xnZHFwdzR4MWxlYjNocGNza2IybXlzbyJ9.ZZj7I092TSffFAFTbu7y5w',
      container: 'map',
      style: 'mapbox://styles/mapbox/streets-v12',
      center: [this.longitude, this.latitude],
      zoom: 15,
    });
    const geocoder = new MapboxGeocoder({
      accessToken:
        'pk.eyJ1IjoibG9uZ3BoMTg4NjkiLCJhIjoiY2xnZHFwdzR4MWxlYjNocGNza2IybXlzbyJ9.ZZj7I092TSffFAFTbu7y5w',
      mapboxgl: mapboxgl,
      marker: false,
      bbox: [102.17, 8.54, 109.3, 23.38],
      proximity: { longitude: 105, latitude: 21 },
      placeholder: 'Nhập địa chỉ bạn muốn tìm kiếm',
    });
    geocoder.on('result', (ev) => {
      this.latitude = ev.result.center[1];
      this.longitude = ev.result.center[0];
      this.loading = true;
      this.carService
        .filter(this.latitude, this.longitude, this.pickupDate, this.returnDate)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.filteredCars = res;
            this.addMarkersToMap(this.map);
            this.loading = false;
          },
          error: (err) => {
            console.log(err);
          },
        });
    });
    // geocoder.on('clear', () => {
    //   this.filteredCars = this.cars;
    // });
    document.getElementById('geocoder')!.appendChild(geocoder.onAdd(this.map));
  }
  addMarkersToMap(map: mapboxgl.Map): void {
    // Remove all existing markers from the map
    this.markers.forEach((marker) => marker.remove());
    this.markers = [];

    this.filteredCars.forEach((car) => {
      const el = document.createElement('div');
      el.style.backgroundImage =
        'url(https://cdn-icons-png.flaticon.com/64/4611/4611919.png)';
      el.style.width = '45px';
      el.style.height = '45px';
      el.style.backgroundSize = 'cover';
      const text = document.createElement('span');
      text.innerHTML = this.moneyPipe.transform(car.rentalPrice);
      text.style.color = 'white';
      text.style.fontSize = '12px';
      text.style.fontWeight = 'bold';
      text.style.textAlign = 'center';
      text.style.width = '100%';
      text.style.position = 'absolute';
      text.style.bottom = '14px';

      el.appendChild(text);
      const popup = new mapboxgl.Popup({ offset: 25 }).setHTML(
        `<a  href ="find/car/${car.id}" style="color: #000;  cursor: pointer;">
        <div style="display: grid;
        grid-template-columns: 1fr 1fr;">
          <div class="mark-image">
            <img
              width="100px"
              height="100%"
              src="${car.carImages[0]}"
              alt=""
            />
          </div>
          <div style="font-weight: bold;
          line-height: 1.5;">
            <label>${car.name}</label
            ><br />
            <span>5.0</span>
            <span style="margin: 0 3px; color: orange"" class="fa fa-star"></span>
            <span>- 3 chuyến</span><br />
            <div style="margin-top: 20px">
              <label><span style="color: #00a550; font-size: 18px">${this.moneyPipe.transform(
                car.rentalPrice
              )}</span>
                <span style="color: #b0b0b0">/ngày</span></label>
            </div>
          </div>
        </div>
      </a>`
      );

      const marker = new mapboxgl.Marker(el)
        .setLngLat([car.longitude, car.latitude])
        .setPopup(popup)
        .addTo(map);

      this.markers.push(marker);
    });
  }
  showClick() {
    this.show = !this.show;
  }
  visible: boolean = false;

  showDialog(car: any) {
    this.visible = true;
  }

  onSort() {
    if (this.selectedSort != null) {
      switch (this.selectedSort.value) {
        case 1:
          break;
        case 2:
          this.filteredCars.sort((a, b) => a.rentalPrice - b.rentalPrice);
          break;
        case 3:
          this.filteredCars.sort((a, b) => b.rentalPrice - a.rentalPrice);
          break;
        case 4:
          break;
        default:
          break;
      }
    }
  }
  onClearSort() {
    this.selectedSort = null;
    this.onSort();
    this.addMarkersToMap(this.map);
  }
  onPriceChange() {
    const minPrice = this.prices[0] * 30000;
    const maxPrice = this.prices[1] * 30000;
    this.filteredCars = this.cars.filter(
      (car) => car.rentalPrice >= minPrice && car.rentalPrice <= maxPrice
    );
    this.addMarkersToMap(this.map);
  }
  filterVehicles() {
    const selectedVehicleTypes: number[] = [];
    if (this.selectedTwoSeater) {
      selectedVehicleTypes.push(2);
    }
    if (this.selectedFourSeater) {
      selectedVehicleTypes.push(4);
    }
    if (this.selectedFiveSeater) {
      selectedVehicleTypes.push(5);
    }
    if (this.selectedSixSeater) {
      selectedVehicleTypes.push(6);
    }
    if (this.selectedSevenSeater) {
      selectedVehicleTypes.push(7);
    }
    if (this.selectedSixteenSeater) {
      selectedVehicleTypes.push(16);
    }
    this.onPriceChange();
    this.onSort();
    if (selectedVehicleTypes.length === 0) {
      this.filterCars();
    } else {
      this.filteredCars = this.filteredCars.filter((car) =>
        selectedVehicleTypes.includes(car.type)
      );
    }
    this.addMarkersToMap(this.map);
  }
  filterCars() {
    if (this.returnDate <= this.pickupDate) {
      this.returnDate = new Date(this.pickupDate.getTime() + 86400000);
    }
    this.carService
      .filter(this.latitude, this.longitude, this.pickupDate, this.returnDate)
      .subscribe({
        next: (res) => {
          this.cars = res;
          this.filteredCars = res;
          this.loading = false;
          this.addMarkersToMap(this.map);
        },
        error: (err) => {
          console.log(err);
        },
      });
  }
}
