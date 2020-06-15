import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {WelcomePageComponent} from "./components/welcome-page/welcome-page.component";
import {Guard} from "./guard/guard.service";
import {UserTableComponent} from "./components/user-table/user-table.component";
import {TaskTableComponent} from "./components/task-table/task-table.component";


const routes: Routes = [
  {
    path: '',
    component: WelcomePageComponent,
  },
  {
    path: 'users/table',
    component: UserTableComponent,
  },
  {
    path: 'tasks/table',
    component: TaskTableComponent,
  },
  {
    path: 'tasks/:id',
    component: UserTableComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
