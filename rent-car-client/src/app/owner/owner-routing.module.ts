import { NgModule } from '@angular/core';
import { MycarsComponent } from './mycars/mycars.component';
import { RouterModule, Routes } from '@angular/router';
import { CalendarsComponent } from './calendars/calendars.component';
import { RegisterCarComponent } from './register-car/register-car.component';
import { AuthGuard } from '../auth.guard';
import { LoginGuard } from '../login.guard';
import { RoleGuard } from '../role.guard';
import { MyCarDetailComponent } from './my-car-detail/my-car-detail.component';

const routes: Routes = [
  {
    path: 'mycars',
    component: MycarsComponent,
    // canActivate: [AuthGuard, RoleGuard],
    // data: { roles: ['ROLE_ADMIN', 'ROLE_CAR_OWNER'] },
    canActivate: [AuthGuard],
    children: [
      {
        path: ':id',
        component: MyCarDetailComponent,
        canActivate: [AuthGuard],
        // canActivate: [AuthGuard, RoleGuard],
        // data: { roles: ['ROLE_ADMIN', 'ROLE_CAR_OWNER'] },
      },
    ],
  },
  {
    path: 'calendars',
    component: CalendarsComponent,
    canActivate: [AuthGuard],
    // canActivate: [AuthGuard, RoleGuard],
    // data: { roles: ['ROLE_ADMIN', 'ROLE_CAR_OWNER'] },
  },
  {
    path: 'registerCar',
    component: RegisterCarComponent,
    canActivate: [AuthGuard],
    // canActivate: [AuthGuard, RoleGuard],
    // data: { roles: ['ROLE_ADMIN', 'ROLE_CAR_OWNER'] },
  },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OwnerRoutingModule {}
