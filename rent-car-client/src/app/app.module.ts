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
import { GoogleMapsModule } from '@angular/google-maps';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import { GooglePlaceModule } from 'ngx-google-places-autocomplete';
import { DropdownModule } from 'primeng/dropdown';
import { SliderModule } from 'primeng/slider';
@NgModule({
  declarations: [
    AppComponent,
    ViewComponent,
    CoreComponent,
    PageComponent,
    HeaderComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    MenubarModule,
    InputTextModule,
    DialogModule,
    BrowserAnimationsModule,
    CalendarModule,
    FormsModule,
    GoogleMapsModule,
    CommonModule,
    HttpClientModule,
    HttpClientJsonpModule,
    GooglePlaceModule,
    DropdownModule,
    SliderModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
