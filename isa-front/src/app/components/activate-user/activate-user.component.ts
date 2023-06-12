import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { ActivationDTO } from 'src/app/model/dto/activation.dto';
import { ApplicationUserService } from 'src/app/services/application.user.service';

@Component({
  selector: 'app-activate-user',
  templateUrl: './activate-user.component.html',
  styleUrls: ['./activate-user.component.css']
})
export class ActivateUserComponent implements OnInit {

  hasQuestionnaire: boolean = false;
  loadQuestionnaireError: string = "";
  token: string = "";
  activationResponse: ActivationDTO = new ActivationDTO();
  activationMessage: string = "";
  timeoutId: any;
  timeLeft: number = 5;
  progress: number = 100;



  constructor(private userService: ApplicationUserService, private activatedRoute: ActivatedRoute,
    private router: Router) {
    this.activatedRoute.queryParams
      .subscribe(params => {
        this.token = params['token'];
      }
      );
  }

  ngOnInit(): void {
    this.activate();

  }


  activate() {
    this.userService.activate(this.token)
      .subscribe(data => {
        this.activationResponse = data;
        if (this.activationResponse.response.startsWith("fail")) {
          if (!!!this.activationResponse.username) {
            this.activationMessage = "Activation failed, user does not exist. Please register to continue.";
            this.router.navigate(['/register'],
              {
                state: { activationStatusMessage: this.activationMessage }
              });
          } else {
            this.activationMessage = "Activation link is not valid. User " + this.activationResponse.username + " is already activated.";
            this.router.navigate(['/login'],
              {
                state: { activationStatusMessage: this.activationMessage }
              });
          }
        } else {
          this.activationMessage = "User " + this.activationResponse.username + " is activated.";
          this.router.navigate(['/login'],
            {
              state: { activationStatusMessage: this.activationMessage }
            });
        }
      },
        error => {
          console.log(error);
          this.router.navigate(['/register'],
            {
              queryParams: { redirectString: "Unknown error." }
            });
        });
  }
}
