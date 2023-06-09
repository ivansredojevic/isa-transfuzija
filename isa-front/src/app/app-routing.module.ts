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
import { CreateComplaintComponent } from './components/create-complaint/create-complaint.component';
import { AuthGuard } from './services/authGuard';

const routes: Routes = [
  {
    path: "",
    component: CenterComponent
  },
  {
    path: "centers",
    component: CenterComponent
  },
  {
    path: "make-appointment",
    canActivate: [AuthGuard],
    component: MakeAppointmentComponent
  },
  {
    path: "my-appointments",
    canActivate: [AuthGuard],
    component: AppointmentComponent
  },
  {
    path: "create-complaint",
    canActivate: [AuthGuard],
    component: CreateComplaintComponent
  },
  {
    path: "users",
    canActivate: [AuthGuard],
    component: UserComponent
  },
  {
    path: "history",
    canActivate: [AuthGuard],
    component: HistoryComponent
  },
  {
    path: "complaint",
    canActivate: [AuthGuard],
    component: ComplaintComponent
  },
  {
    path: "personnel",
    canActivate: [AuthGuard],
    component: PersonnelComponent
  },
  {
    path: "questionnaire",
    canActivate: [AuthGuard],
    component: QuestionnaireComponent
  },
  {
    path: "profile",
    canActivate: [AuthGuard],
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
  {
    path: '**',
    redirectTo: "/centers?redirected=nonexistingpage"
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
