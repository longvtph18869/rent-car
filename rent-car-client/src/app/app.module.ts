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
import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import { DropdownModule } from 'primeng/dropdown';
import { SliderModule } from 'primeng/slider';
import { MoneyPipePipe } from './money-pipe.pipe';
import { DetailComponent } from './view/core/detail/detail.component';
import { NoComponent } from './view/core/no/no.component';
import { CarouselModule } from 'primeng/carousel';
import { RentComponent } from './view/core/rent/rent.component';
import { RadioButtonModule } from 'primeng/radiobutton';
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
    CommonModule,
    HttpClientModule,
    HttpClientJsonpModule,
    DropdownModule,
    SliderModule,
    CarouselModule,
    RadioButtonModule,
  ],
  providers: [MoneyPipePipe],
  bootstrap: [AppComponent],
})
export class AppModule {}
