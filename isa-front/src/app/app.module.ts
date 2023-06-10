import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NavigationComponent } from './components/navigation/navigation.component';

import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { HttpClientModule } from '@angular/common/http';

import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { UserComponent } from './components/user/user.component';
import { AppointmentComponent } from './components/appointment/appointment.component';
import { CenterComponent } from './components/center/center.component';
import { ComplaintComponent } from './components/complaint/complaint.component';
import { QuestionnaireComponent } from './components/questionnaire/questionnaire.component';
import { PersonnelComponent } from './components/personnel/personnel.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HistoryComponent } from './components/history/history.component';
import { MatRadioModule } from '@angular/material/radio';
import { FormsModule } from '@angular/forms';
import { MakeAppointmentComponent } from './components/make-appointment/make-appointment.component';
import { CreateComplaintComponent } from './components/create-complaint/create-complaint.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatMenuModule } from '@angular/material/menu';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { DatePipe } from '@angular/common';
import { FillQuestionnaireComponent } from './components/fill-questionnaire/fill-questionnaire.component';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NavigationComponent,
    UserComponent,
    AppointmentComponent,
    CenterComponent,
    ComplaintComponent,
    QuestionnaireComponent,
    PersonnelComponent,
    ProfileComponent,
    HistoryComponent,
    MakeAppointmentComponent,
    CreateComplaintComponent,
    FillQuestionnaireComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatSortModule,
    HttpClientModule,
    MatPaginatorModule,
    MatTableModule,
    LayoutModule,
    MatFormFieldModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatCardModule,
    MatInputModule,
    MatDialogModule,
    MatSnackBarModule,
    MatCheckboxModule,
    MatRadioModule,
    FormsModule,
    MatGridListModule,
    MatSnackBarModule,
    MatMenuModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSlideToggleModule 
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
