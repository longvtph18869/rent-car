import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageComponent } from './view/core/page/page.component';
import { NoComponent } from './view/core/no/no.component';
import { OwnerComponent } from './owner/owner.component';
import { ViewComponent } from './view/view.component';
const routes: Routes = [
  {
    path: '',
    component: ViewComponent,
    loadChildren: () =>
      import('./view/view-routing.module').then((m) => m.ViewRoutingModule),
  },
  {
    path: 'owner',
    component: OwnerComponent,
    loadChildren: () =>
      import('./owner/owner-routing.module').then((m) => m.OwnerRoutingModule),
  },
  { path: '**', component: NoComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
