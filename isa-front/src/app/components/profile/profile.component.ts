import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApplicationUserModel } from 'src/app/model/applicationUser.model';
import { ApplicationUserService } from 'src/app/services/application.user.service';
import { AuthService } from 'src/app/services/auth.service';
import { StorageService } from 'src/app/services/storage.service';
import { MatGridListModule } from '@angular/material/grid-list';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  username: string;
  applicationUser: ApplicationUserModel = new ApplicationUserModel();
  public displayedColumns = ['personal', 'account'];


  constructor(private authService: AuthService, private router: Router,
    private userService: ApplicationUserService, private storageService: StorageService) { }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    this.getUser();
  }


  getUser() {
    this.userService.loadUser(this.username)
      .subscribe(data => {
        this.applicationUser = data;
        this.storageService.setItem('canDonate', this.applicationUser.canDonate);
      },
        error => {
          console.log(error);
        });
  }

  fillQuestionnaire() {

  }

}
