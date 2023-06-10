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


  rejected: boolean = false;
  healthy: boolean = false;
  dangerousOccupation: boolean = false;
  infectious: boolean = false;
  bloodPressureIssues: boolean = false;
  onTherapy: boolean = false;
  aspirin: boolean = false;
  tatooed: boolean = false;

  constructor(public router: Router, public questionnaireService: QuestionnaireService,
    public snackService: SnackService, public authService: AuthService) { }

  ngOnInit() {
    this.username = this.authService.getUsername();
  }


  onSubmit(){

    console.log("click");
    
    let questionnaire: QuestionnaireModel = new QuestionnaireModel();

    questionnaire.appUsername = this.username;
    questionnaire.rejected = this.rejected;
    questionnaire.healthy = this.healthy;
    questionnaire.dangerousOccupation = this.dangerousOccupation;
    questionnaire.infectious = this.infectious;
    questionnaire.bloodPressureIssues = this.bloodPressureIssues;
    questionnaire.onTherapy = this.onTherapy;
    questionnaire.aspirin = this.aspirin;
    questionnaire.tatooed = this.tatooed;

    console.log(questionnaire);

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