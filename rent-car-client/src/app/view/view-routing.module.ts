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
import { LoginGuard } from '../login.guard';

const routes: Routes = [
  { path: '', component: PageComponent },
  { path: 'find', component: PageComponent },
  { path: 'car/:id', component: DetailComponent },
  { path: 'rent', component: RentComponent, canActivate: [AuthGuard, LoginGuard] },
  { path: 'account', component: AccountComponent, canActivate: [LoginGuard]},
  { path: 'access-denied', component: AccessDeniedComponent, canActivate: [LoginGuard]},
  { path: 'myfavs', component: FavoriteCarComponent, canActivate: [LoginGuard]},
  { path: 'rentedcars', component: RentedCarComponent, canActivate: [LoginGuard]},
  { path: 'myaddress', component: AddressComponent, canActivate: [LoginGuard]},
  { path: 'mycard', component: CardComponent, canActivate: [LoginGuard]}
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ViewRoutingModule {}
