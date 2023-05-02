import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageComponent } from './core/page/page.component';
import { DetailComponent } from './core/detail/detail.component';
import { RentComponent } from './core/rent/rent.component';
import { AccountComponent } from './account/account.component';
import { AuthGuard } from '../auth.guard';
import { RoleGuard } from '../role.guard';
import { HomeComponent } from './home/home.component';

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
  { path: 'rent', component: RentComponent, canActivate: [AuthGuard] },
  {
    path: 'account',
    component: AccountComponent,
    canActivate: [RoleGuard],
    data: { role: 'ROLE_ADMIN' },
  },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ViewRoutingModule {}
