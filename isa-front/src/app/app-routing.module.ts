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
import { MakeAppointmentComponent } from './components/make-appointment/make-appointment.component';

const routes: Routes = [
  {
    path: "",
    component: LoginComponent
  },
  {
    path: "centers",
    component: CenterComponent
  },
  {
    path: "make-appointment",
    component: MakeAppointmentComponent
  },
  {
    path: "my-appointments",
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
