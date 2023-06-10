import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApplicationUserModel } from 'src/app/model/applicationUser.model';
import { ApplicationUserService } from 'src/app/services/application.user.service';
import { AuthService } from 'src/app/services/auth.service';
import { SnackService } from 'src/app/services/snackHelper.service';
import { StorageService } from 'src/app/services/storage.service';


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

  constructor(private authService: AuthService, private userService: ApplicationUserService, private storageService: StorageService) { }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    this.getUser();


  }

  getUser() {
    this.userService.loadUser(this.username)
      .subscribe(data => {
        this.applicationUser = data;
        // this.storageService.setItem('canDonate', this.applicationUser.canDonate);
        if (this.applicationUser.questionnaireId !== 0) {
          this.hasQuestionnaire = true;
        }
      },
        error => {
          console.log(error);
        });
  }

}
