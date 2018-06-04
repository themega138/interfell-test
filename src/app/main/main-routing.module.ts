import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {MainComponent} from "./main.component";
import {MainGuard} from "./main.guard";

const routes: Routes = [

  {
    path: '',
    component: MainComponent,
    canActivate: [MainGuard],
    children: [
      {
        path: 'home',
        loadChildren: './home/home.module#HomeModule'
      },
      {
        path: 'users',
        loadChildren: './users/users.module#UsersModule'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
