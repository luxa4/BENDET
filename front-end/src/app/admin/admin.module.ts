import {NgModule} from '@angular/core';
import {LoginPageComponent} from './components/login-page/login-page.component';
import {ListPageComponent} from './components/list-page/list-page.component';
import {AdminLayoutComponent} from './components/admin-layout/admin-layout.component';
import {EditPageComponent} from './components/edit-page/edit-page.component';
import {CreatePageComponent} from './components/create-page/create-page.component';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations:[
    AdminLayoutComponent,
    LoginPageComponent,
    ListPageComponent,
    EditPageComponent,
    CreatePageComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      {
        path: '', component: AdminLayoutComponent, children: [
          {path: '', redirectTo: '/admin/login', pathMatch: 'full'},
          {path: 'login', component: LoginPageComponent},
          {path: 'list/edit/:id', component: EditPageComponent},
          {path: 'list', component: ListPageComponent},
          {path: 'create', component: CreatePageComponent}
        ]
      }
    ]),
    FormsModule,
    ReactiveFormsModule,

  ],

  exports:[RouterModule]
})
export class AdminModule {}
