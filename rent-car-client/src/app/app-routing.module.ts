import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailComponent } from './view/core/detail/detail.component';
import { PageComponent } from './view/core/page/page.component';
import { NoComponent } from './view/core/no/no.component';
import { RentComponent } from './view/core/rent/rent.component';
const routes: Routes = [
  { path: 'car/:id', component: DetailComponent },
  { path: 'find', component: NoComponent },
  { path: 'rent', component: RentComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
