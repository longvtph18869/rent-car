import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageComponent } from './core/page/page.component';
import { DetailComponent } from './core/detail/detail.component';
import { RentComponent } from './core/rent/rent.component';
import { AccountComponent } from './account/account.component';
import { FavoriteCarComponent } from './favorite-car/favorite-car.component';
import { RentedCarComponent } from './rented-car/rented-car.component';
import { AddressComponent } from './address/address.component';
import { CardComponent } from './card/card.component';
import { AccessDeniedComponent } from './access-denied/access-denied.component';
import { AuthGuard } from '../auth.guard';
import { HomeComponent } from './home/home.component';
import { LoginGuard } from '../login.guard';

const routes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'find',
    component: PageComponent,
    children: [
      {
        path: 'car/:id',
        component: DetailComponent,
      },
    ],
  },
  {
    path: 'rent',
    component: RentComponent,
    canActivate: [AuthGuard],
  },
  { path: 'account', component: AccountComponent, canActivate: [AuthGuard] },
  {
    path: 'access-denied',
    component: AccessDeniedComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'myfavs',
    component: FavoriteCarComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'rentedcars',
    component: RentedCarComponent,
    canActivate: [AuthGuard],
  },
  { path: 'myaddress', component: AddressComponent, canActivate: [AuthGuard] },
  { path: 'mycard', component: CardComponent, canActivate: [AuthGuard] },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ViewRoutingModule {}
