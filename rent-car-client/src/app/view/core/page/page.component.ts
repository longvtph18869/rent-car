import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CarService } from 'src/app/service/car.service';
import { MoneyPipePipe } from 'src/app/money-pipe.pipe';
import { Router } from '@angular/router';
import MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
declare const mapboxgl: any;
@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.css'],
})
export class PageComponent implements OnInit {
  cars: any[] = [];
  pickupDate: Date = new Date();
  minpickupDate: Date = new Date();
  returnDate: Date = new Date();
  minreturnDate: Date = new Date();
  sorts!: any[];
  prices: number[] = [0, 100];
  show: boolean = true;
  map: any;
  constructor(
    private http: HttpClient,
    private carService: CarService,
    private router: Router,
    private moneyPipe: MoneyPipePipe
  ) {
    this.sorts = [
      { name: 'Khoảng cách gần nhất', code: 1 },
      { name: 'Giá thấp nhất', code: 2 },
      { name: 'Giá cao nhất', code: 3 },
      { name: 'Đánh giá tốt nhất', code: 4 },
    ];
  }
  ngOnInit(): void {
    this.returnDate.setDate(this.returnDate.getDate() + 1);
    this.minreturnDate.setDate(this.minreturnDate.getDate() + 1);
    // this.carService.getAllCars().subscribe((data) => {
    //   this.cars = data;
    // });
    this.map = new mapboxgl.Map({
      accessToken:
        'pk.eyJ1IjoibG9uZ3BoMTg4NjkiLCJhIjoiY2xnZHFwdzR4MWxlYjNocGNza2IybXlzbyJ9.ZZj7I092TSffFAFTbu7y5w',
      container: 'map',
      style: 'mapbox://styles/mapbox/streets-v12',
      center: [105.81610684403171, 21.018853843837093],
      zoom: 15,
    });
    const geocoder = new MapboxGeocoder({
      accessToken:
        'pk.eyJ1IjoibG9uZ3BoMTg4NjkiLCJhIjoiY2xnZHFwdzR4MWxlYjNocGNza2IybXlzbyJ9.ZZj7I092TSffFAFTbu7y5w',
      mapboxgl: mapboxgl,
      marker: false,
      placeholder: 'Thành phố Hà Nội, Việt Nam',
    });
    document.getElementById('geocoder')!.appendChild(geocoder.onAdd(this.map));
    this.carService.getAllCars().subscribe({
      next: (res) => {
        this.cars = res;
        console.log(this.cars);
        this.cars.forEach((car) => {
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
            `<a  routerLink="/car/${
              car.id
            }" style="color: #000;  cursor: pointer;">
              <div style="display: grid;
              grid-template-columns: 1fr 1fr;">
                <div class="mark-image">
                  <img
                    width="100px"
                    height="100%"
                    src="assets/image/${car.carImage[0].image}"
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
            .setLngLat([car.location.longitude, car.location.latitude])
            .setPopup(popup)
            .addTo(this.map);
          // popup.getElement().addEventListener('click', () => {
          //   this.router.navigate(['/car', car.id]);
          // });
        });
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  showClick() {
    this.show = !this.show;
  }
  visible: boolean = false;

  showDialog(car: any) {
    console.log(car);
    this.visible = true;
  }
}
