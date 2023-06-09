import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { QuestionnaireModel } from 'src/app/model/questionnaire.model';
import { ApplicationUserService } from 'src/app/services/application.user.service';
import { AuthService } from 'src/app/services/auth.service';
import { QuestionnaireService } from 'src/app/services/questionnaire.service';
import { SnackService } from 'src/app/services/snackHelper.service';

@Component({
  selector: 'app-fill-questionnaire',
  templateUrl: './fill-questionnaire.component.html',
  styleUrls: ['./fill-questionnaire.component.css']
})
export class FillQuestionnaireComponent implements OnInit {

  questionnaireForm: FormGroup;
  username: string;

  constructor(public router: Router, public questionnaireService: QuestionnaireService,
    public snackService: SnackService, public authService: AuthService) { }

  ngOnInit() {
    this.username = this.authService.getUsername();
    this.createForm();
  }
  createForm() {
    this.questionnaireForm = new FormGroup(
      {
        'donationCount': new FormControl(null, [Validators.required]),
        'rejected': new FormControl(null, [Validators.required]),
        'healthy': new FormControl(null, [Validators.required]),
        'dangerousOccupation': new FormControl(null, [Validators.required]),
        'infectious': new FormControl(null, [Validators.required]),
        'bloodPressureIssues': new FormControl(null),
        'onTherapy': new FormControl(null),
        'aspirin': new FormControl(null),
        'tatooed': new FormControl(null),
      });
  }

  onSubmit(formData: FormGroup, formDirective: FormGroupDirective): void {

    const donationCount = formData.value.email;
    const rejected = formData.value.password;
    const healthy = formData.value.username;
    const dangerousOccupation = formData.value.sex;
    const infectious = formData.value.date;
    const bloodPressureIssues = formData.value.name;
    const onTherapy = formData.value.surname;
    const aspirin = formData.value.address;
    const tatooed = formData.value.phone;

    let questionnaire: QuestionnaireModel = new QuestionnaireModel();

    questionnaire.appUsername = this.username;
    questionnaire.donationCount = donationCount;
    questionnaire.rejected = rejected;
    questionnaire.healthy = healthy;
    questionnaire.dangerousOccupation = dangerousOccupation;
    questionnaire.infectious = infectious;
    questionnaire.bloodPressureIssues = bloodPressureIssues;
    questionnaire.onTherapy = onTherapy;
    questionnaire.aspirin = aspirin;
    questionnaire.tatooed = tatooed;

    this.questionnaireService.fillQuestionnaire(questionnaire)
      .subscribe(
        (data) => {
          this.router.navigate(["/profile"], {
            state: { addQuestionnaireResponse: data.response }
          })
        },
        error => {
          console.log(error);
          this.snackService.showSnack(error, "OK")
        }
      );
  }

}