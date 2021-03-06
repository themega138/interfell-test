import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UsersListComponent} from "./users-list/users-list.component";
import {UsersEditorComponent} from "./users-editor/users-editor.component";
import {UsersDetailComponent} from "./users-detail/users-detail.component";

const routes: Routes = [
  {
    path: '',
    component: UsersListComponent
  },
  {
    path: ':id',
    component: UsersEditorComponent
  },
  {
    path: 'detail/:id',
    component: UsersDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
