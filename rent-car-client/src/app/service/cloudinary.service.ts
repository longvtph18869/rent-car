import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cloudinary } from '@cloudinary/angular-5.x';

@Injectable({
  providedIn: 'root',
})
export class CloudinaryService {
  constructor(private cloudinary: Cloudinary, private http: HttpClient) {}

  async uploadImages(images: File[]) {
    const urls: string[] = [];
    const formData = new FormData();

    await Promise.all(
      images.map(async (image) => {
        formData.append('file', image);
        formData.append('upload_preset', 'rent_car');
        formData.append('cloud_name', 'dyje74rxj');

        const res = await this.http
          .post<any>(
            'https://api.cloudinary.com/v1_1/dyje74rxj/image/upload',
            formData
          )
          .toPromise();
        urls.push(res.url);
      })
    );

    return urls;
  }

  async uploadImage(image: File) {
    const formData = new FormData();
    formData.append('file', image);
    formData.append('upload_preset', 'rent_car');
    formData.append('cloud_name', 'dyje74rxj');
  
    const res = await this.http
      .post<any>(
        'https://api.cloudinary.com/v1_1/dyje74rxj/image/upload',
        formData
      )
      .toPromise();
    return res.url;
  }
}
