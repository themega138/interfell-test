import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { UsersListComponent } from './users-list/users-list.component';
import { UsersEditorComponent } from './users-editor/users-editor.component';
import { UsersDetailComponent } from './users-detail/users-detail.component';

@NgModule({
  imports: [
    CommonModule,
    UsersRoutingModule
  ],
  declarations: [UsersListComponent, UsersEditorComponent, UsersDetailComponent]
})
export class UsersModule { }
