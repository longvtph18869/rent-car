import { Component, ViewChild } from '@angular/core';
import { GoogleMap, MapInfoWindow, MapMarker } from '@angular/google-maps';
import { Address } from 'ngx-google-places-autocomplete/objects/address';

interface MarkerProperties {
  position: {
    lat: number;
    lng: number;
  };
  icon: string;
  label: any;
  name: string;
  image: string;
}
@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.css'],
})
export class PageComponent {
  @ViewChild(MapInfoWindow) infoWindow!: MapInfoWindow;
  date1: Date = new Date();
  time1: Date = new Date();

  date2: Date = new Date();
  time2: Date = new Date();
  options!: google.maps.MapOptions;
  @ViewChild('myMap') mapElement!: GoogleMap;
  sorts!: any[];
  prices: number[] = [0, 100];
  show: boolean = true;
  styleMap: google.maps.MapTypeStyle[] = [
    {
      featureType: 'administrative',
      elementType: 'geometry',
      stylers: [
        {
          visibility: 'off',
        },
      ],
    },
    {
      featureType: 'poi',
      stylers: [
        {
          visibility: 'off',
        },
      ],
    },
    {
      featureType: 'road',
      elementType: 'labels.icon',
      stylers: [
        {
          visibility: 'off',
        },
      ],
    },
    {
      featureType: 'transit',
      stylers: [
        {
          visibility: 'off',
        },
      ],
    },
  ];
  constructor() {
    this.sorts = [
      { name: 'Khoảng cách gần nhất', code: 1 },
      { name: 'Giá thấp nhất', code: 2 },
      { name: 'Giá cao nhất', code: 3 },
      { name: 'Đánh giá tốt nhất', code: 4 },
    ];
    this.options = {
      center: { lat: 21.018853843837093, lng: 105.81610684403171 },
      zoom: 15,
      styles: this.styleMap,
    };
  }
  markers: MarkerProperties[] = [
    {
      position: { lat: 21.018853843837093, lng: 105.81610684403171 },
      icon: 'https://cdn-icons-png.flaticon.com/64/4611/4611919.png',
      label: {
        text: '300K',
        color: 'yellow',
        fontSize: '18px',
        fontWeight: 'bold',
      },
      name: 'KIA SORENTO 2020',
      image:
        'https://n1-pstg.mioto.vn/cho_thue_xe_o_to_tu_lai_thue_xe_du_lich_hanoi/kia_sorento_2020/p/g/2021/01/24/19/Iiqr69IFKKPB2ZdN9diSLw.jpg',
    },
    {
      position: { lat: 21.023808266877527, lng: 105.80957430667394 },
      icon: 'https://cdn-icons-png.flaticon.com/64/4611/4611919.png',
      label: {
        text: '1200K',
        color: 'yellow',
        fontSize: '18px',
        fontWeight: 'bold',
      },
      name: 'KIA SORENTO 2020',
      image:
        'https://n1-pstg.mioto.vn/cho_thue_xe_o_to_tu_lai_thue_xe_du_lich_hanoi/kia_rondo_2016/p/g/2022/03/24/07/cxq_NzJ7mMp42ce4jTZF0Q.jpg',
    },
    {
      position: { lat: 21.030525057951962, lng: 105.82901872875755 },
      icon: 'https://cdn-icons-png.flaticon.com/64/4611/4611919.png',
      label: {
        text: '560K',
        color: 'yellow',
        fontSize: '18px',
        fontWeight: 'bold',
      },
      name: 'KIA SORENTO 2020',
      image:
        'https://n1-pstg.mioto.vn/cho_thue_xe_o_to_tu_lai_thue_xe_du_lich_hanoi/honda_city_2017/p/g/2023/01/01/17/ntZZ6kQpgg2SoFODbAb5PA.jpg',
    },
  ];
  index: number = 0;

  openInfoWindow(marker: MapMarker, i: number) {
    this.index = i;
    this.infoWindow.open(marker);
  }
  public handleAddressChange(address: Address) {
    const geocoder = new google.maps.Geocoder();
    geocoder.geocode(
      { address: address.formatted_address },
      (results, status) => {
        if (status === 'OK' && results && results.length > 0) {
          const location = results[0].geometry.location;
          this.options.center = { lat: location.lat(), lng: location.lng() };
          this.mapElement?.googleMap!.setOptions(this.options);
        } else {
          console.log(
            'Geocode was not successful for the following reason: ' + status
          );
        }
      }
    );
  }
  option: any = {
    types: [],
    componentRestrictions: { country: 'VN' },
  };

  showClick() {
    this.show = !this.show;
  }
}
