import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViewComponent } from './view/view.component';
import { CoreComponent } from './view/core/core.component';
import { PageComponent } from './view/core/page/page.component';
import { HeaderComponent } from './view/core/header/header.component';
import { MenubarModule } from 'primeng/menubar';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { LoginComponent } from './view/login/login.component';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CalendarModule } from 'primeng/calendar';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {
  HttpClientModule,
  HttpClientJsonpModule,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { DropdownModule } from 'primeng/dropdown';
import { SliderModule } from 'primeng/slider';
import { MoneyPipePipe } from './money-pipe.pipe';
import { DetailComponent } from './view/core/detail/detail.component';
import { NoComponent } from './view/core/no/no.component';
import { CarouselModule } from 'primeng/carousel';
import { RentComponent } from './view/core/rent/rent.component';
import { RadioButtonModule } from 'primeng/radiobutton';
import { OwnerComponent } from './owner/owner.component';
import { MycarsComponent } from './owner/mycars/mycars.component';
import { CalendarsComponent } from './owner/calendars/calendars.component';
import { OwnerRoutingModule } from './owner/owner-routing.module';
import { RegisterCarComponent } from './owner/register-car/register-car.component';
import { MatButtonModule } from '@angular/material/button';
import { MatStepperModule } from '@angular/material/stepper';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { RegisterComponent } from './view/register/register.component';
import { MatDialogModule } from '@angular/material/dialog';
import {
  CloudinaryModule,
  CloudinaryConfiguration,
} from '@cloudinary/angular-5.x';
import { Cloudinary } from 'cloudinary-core';
import { SeatPipe } from './seat.pipe';
import { MenuBarComponent } from './view/menu-bar/menu-bar.component';
import { ChangePasswordComponent } from './view/change-password/change-password.component';
import { TokeninterceptorService } from './interceptor/tokeninterceptor.service';
import { DialogSuccessComponent } from './dialog/success/success.component';
import { DialogLoadingComponent } from './dialog/loading/loading.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MenuModule } from 'primeng/menu';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { ListboxModule } from 'primeng/listbox';
import { AccountComponent } from './view/account/account.component';
import { MyCarDetailComponent } from './owner/my-car-detail/my-car-detail.component';
import { MatMenuModule } from '@angular/material/menu';
import { ConfirmComponent } from './dialog/confirm/confirm.component';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { FavoriteCarComponent } from './view/favorite-car/favorite-car.component';
import { RentedCarComponent } from './view/rented-car/rented-car.component';
import { AddressComponent } from './view/address/address.component';
import { CardComponent } from './view/card/card.component';
import { DrivingLicenseComponent } from './view/account/driving-license/driving-license.component';
import { EmailComponent } from './view/account/email/email.component';
import { InfoComponent } from './view/account/info/info.component';
import { PhoneComponent } from './view/account/phone/phone.component';
import { AccessDeniedComponent } from './view/access-denied/access-denied.component';
import { GalleriaModule } from 'primeng/galleria';
import { HomeComponent } from './view/home/home.component';
import { LottieModule } from 'ngx-lottie';
import player from 'lottie-web';
export function playerFactory() {
  return player;
}
@NgModule({
  declarations: [
    AppComponent,
    ViewComponent,
    CoreComponent,
    PageComponent,
    HeaderComponent,
    LoginComponent,
    MoneyPipePipe,
    DetailComponent,
    NoComponent,
    RentComponent,
    OwnerComponent,
    MycarsComponent,
    CalendarsComponent,
    RegisterCarComponent,
    RegisterComponent,
    SeatPipe,
    MenuBarComponent,
    ChangePasswordComponent,
    DialogSuccessComponent,
    DialogLoadingComponent,
    AccountComponent,
    MyCarDetailComponent,
    ConfirmComponent,
    FavoriteCarComponent,
    RentedCarComponent,
    AddressComponent,
    CardComponent,
    DrivingLicenseComponent,
    EmailComponent,
    InfoComponent,
    PhoneComponent,
    AccessDeniedComponent,
    HomeComponent,
  ],
  imports: [
    CloudinaryModule.forRoot({ Cloudinary }, {
      cloud_name: 'dyje74rxj',
      api_key: '971146142641585',
      api_secret: 'uD6000Yf2dnHzEDBiukL8MXPUQU',
      upload_preset: 'ml_default',
    } as CloudinaryConfiguration),
    BrowserModule,
    OwnerRoutingModule,
    AppRoutingModule,
    ButtonModule,
    MenubarModule,
    InputTextModule,
    DialogModule,
    BrowserAnimationsModule,
    CalendarModule,
    FormsModule,
    CommonModule,
    HttpClientModule,
    HttpClientJsonpModule,
    DropdownModule,
    SliderModule,
    CarouselModule,
    RadioButtonModule,
    MatButtonModule,
    MatStepperModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatSelectModule,
    MatCardModule,
    ReactiveFormsModule,
    ToastModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MenuModule,
    ProgressSpinnerModule,
    ListboxModule,
    MatMenuModule,
    MessagesModule,
    MessageModule,
    GalleriaModule,
    LottieModule.forRoot({ player: playerFactory }),
  ],
  providers: [
    MoneyPipePipe,
    MessageService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokeninterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
