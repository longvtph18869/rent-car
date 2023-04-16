import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageComponent } from './core/page/page.component';
import { DetailComponent } from './core/detail/detail.component';
import { RentComponent } from './core/rent/rent.component';

const routes: Routes = [
  { path: '', component: PageComponent },
  { path: 'find', component: PageComponent },
  { path: 'car/:id', component: DetailComponent },
  { path: 'rent', component: RentComponent },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ViewRoutingModule {}