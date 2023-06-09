import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { QuestionnaireModel } from 'src/app/model/questionnaire.model';
import { AuthService } from 'src/app/services/auth.service';
import { QuestionnaireService } from 'src/app/services/questionnaire.service';
import { SnackService } from 'src/app/services/snackHelper.service';

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css']
})
export class QuestionnaireComponent implements OnInit {

  username: string;
  questionnaire: QuestionnaireModel = new QuestionnaireModel();

  hasQuestionnaire: boolean = false;


  constructor(private authService: AuthService, private router: Router,
    private questionnaireService: QuestionnaireService, private snackService: SnackService) { }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    this.loadQuestionnaire();

    // console.log(this.questionnaire);
    // console.log(!this.questionnaire);

    // console.log(!!this.questionnaire);

    // console.log(!!!this.questionnaire);
  }


  loadQuestionnaire() {
    this.questionnaireService.getOne(this.username)
      .subscribe(data => {
        this.questionnaire = data;
        console.log(this.questionnaire);
        console.log(!this.questionnaire);
        console.log(!!this.questionnaire);
        console.log(!!!this.questionnaire);
      },
        error => {
          console.log(error);
          this.router.navigate(["/profile"]) , {
            state: { loadQuestionnaireError: "Error loading questionnaire" }
          }
        });
  }

}