import { NgModule } from '@angular/core';
import { MycarsComponent } from './mycars/mycars.component';
import { RouterModule, Routes } from '@angular/router';
import { CalendarsComponent } from './calendars/calendars.component';
import { RegisterCarComponent } from './register-car/register-car.component';

const routes: Routes = [
  { path: 'mycars', component: MycarsComponent },
  { path: 'calendars', component: CalendarsComponent },
  { path: 'registerCar', component: RegisterCarComponent },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OwnerRoutingModule {}
