import { NgModule } from '@angular/core';
import { MycarsComponent } from './mycars/mycars.component';
import { RouterModule, Routes } from '@angular/router';
import { CalendarsComponent } from './calendars/calendars.component';
import { RegisterCarComponent } from './register-car/register-car.component';
import { AuthGuard } from '../auth.guard';
import { MyCarDetailComponent } from './my-car-detail/my-car-detail.component';

const routes: Routes = [
  {
    path: 'mycars',
    component: MycarsComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: ':id',
        component: MyCarDetailComponent,
        canActivate: [AuthGuard],
      },
    ],
  },
  {
    path: 'calendars',
    component: CalendarsComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'registerCar',
    component: RegisterCarComponent,
    canActivate: [AuthGuard],
  },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OwnerRoutingModule {}
