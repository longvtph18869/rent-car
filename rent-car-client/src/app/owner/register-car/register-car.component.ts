import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
declare const mapboxgl: any;
@Component({
  selector: 'app-register-car',
  templateUrl: './register-car.component.html',
  styleUrls: ['./register-car.component.css'],
})
export class RegisterCarComponent implements AfterViewInit {
  map: any;
  @ViewChild('mapContainer', { static: false }) mapContainer: any;
  yearList: number[] = [];
  form = this._formBuilder.group({
    licensePlate: [
      '',
      [
        Validators.required,
        Validators.pattern(/^[0-9]{2}[A-Z]-[0-9]{3,4}\.[0-9]{2}$/i),
      ],
    ],
    manufacturer: ['', Validators.required],
    yearOfManufacture: ['', Validators.required],
    seats: ['', Validators.required],
    colors: ['', Validators.required],
    description: ['', Validators.required],
  });
  formSecond = this._formBuilder.group({
    rentalPrice: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
  });

  constructor(private _formBuilder: FormBuilder) {
    const currentYear = new Date().getFullYear();
    for (let year = 1980; year <= currentYear; year++) {
      this.yearList.push(year);
    }
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
    geocoder.on('result', (event) => {
      const result = event.result;
      const lng = result.center[0];
      const lat = result.center[1];

      new mapboxgl.Marker().setLngLat([lng, lat]).addTo(this.map);
    });
    document.getElementById('geocoder')!.appendChild(geocoder.onAdd(this.map));
  }

  images: { url: string; name: string }[] = [];
  onFileSelected(event: any) {
    const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      if (!file.type.match('image.*')) {
        continue;
      }
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const image = {
          url: e.target.result,
          name: file.name,
        };
        this.images.push(image);
        const formData = new FormData();
        formData.append('file', file, file.name);
      };
      reader.readAsDataURL(file);
    }
    console.log(this.images);
  }

  removeImage(image: any) {
    const index = this.images.indexOf(image);
    if (index >= 0) {
      this.images.splice(index, 1);
    }
    console.log(this.images);
  }
}
