import { Component, OnInit } from '@angular/core';
import { ApplicationUserModel } from 'src/app/model/applicationUser.model';
import { ApplicationUserService } from 'src/app/services/application.user.service';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  username: string;
  applicationUser: ApplicationUserModel = new ApplicationUserModel();
  hasQuestionnaire: boolean = false;
  loadQuestionnaireError: string = "";

  constructor(private authService: AuthService, private userService: ApplicationUserService) { }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    this.getUser();
  }

  getUser() {
    this.userService.loadUser(this.username)
      .subscribe(data => {
        this.applicationUser = data;
        if (this.applicationUser.questionnaireId !== 0) {
          this.hasQuestionnaire = true;
        }
      },
        error => {
          console.log(error);
        });
  }

}
