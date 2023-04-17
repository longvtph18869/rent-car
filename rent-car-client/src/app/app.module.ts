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
    RegisterComponent
  ],
  imports: [
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
    ToastModule
  ],
  providers: [MoneyPipePipe, MessageService],
  bootstrap: [AppComponent],
})
export class AppModule {}
