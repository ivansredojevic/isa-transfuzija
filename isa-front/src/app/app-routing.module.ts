import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CenterComponent } from './components/center/center.component';
import { AppointmentComponent } from './components/appointment/appointment.component';
import { UserComponent } from './components/user/user.component';
import { HistoryComponent } from './components/history/history.component';
import { ComplaintComponent } from './components/complaint/complaint.component';
import { PersonnelComponent } from './components/personnel/personnel.component';
import { QuestionnaireComponent } from './components/questionnaire/questionnaire.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  {
    path: "centers",
    component: CenterComponent
  },
  {
    path: "appointments",
    component: AppointmentComponent
  },
  {
    path: "users",
    component: UserComponent
  },
  {
    path: "history",
    component: HistoryComponent
  },
  {
    path: "complaint",
    component: ComplaintComponent
  },
  {
    path: "personnel",
    component: PersonnelComponent
  },
  {
    path: "questionnaire",
    component: QuestionnaireComponent
  },
  {
    path: "profile",
    component: ProfileComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


// <a mat-list-item href="/centers">Centers</a>
//           <a mat-list-item href="/appointments">Appointments</a>
//           <!-- <a mat-list-item href="/users">Users</a> -->
//           <a mat-list-item href="/history">History</a>
//           <a mat-list-item href="/complaint">Complaints</a>
//           <!-- <a mat-list-item href="/personnel">Personnel</a> -->
//           <!-- premestiti questionnaire u profil -->
//           <!-- <a mat-list-item href="/questionnaire">Work order runs</a> -->
//           <a mat-list-item href="/profile">Profile</a>
//           <span fxFlex></span>
//           <a mat-list-item href="/register">Register</a>
//           <a mat-list-item href="/login" >Login</a> <!-- dodati (click)="onLogOut()"-->