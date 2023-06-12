import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms'
import { AuthUserModel } from 'src/app/model/auth.user.model';
import { AuthService } from 'src/app/services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SnackService } from 'src/app/services/snackHelper.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  private user: AuthUserModel = new AuthUserModel();
  addRegisterResponse: string;
  activationStatus: string;
  sessionExpired: string;

  constructor(private authService: AuthService, private router: Router, private activatedRoute: ActivatedRoute, private snackService: SnackService) {
      this.activatedRoute.queryParams
      .subscribe(params => {
        this.sessionExpired = params['sessionExpired'];
      }
      );
  }

  ngOnInit(): void {
    if (!!this.sessionExpired) {
      this.snackService.showSnack("Session expired. Please log in.", "OK");
    }

    this.addRegisterResponse = history.state.addRegisterResponse;
    if (!!this.addRegisterResponse) {
      this.snackService.showSnack(this.addRegisterResponse, "OK");
    }

    this.activationStatus = history.state.activationStatusMessage;

    if (!!this.activationStatus) {
      this.snackService.showSnack(this.activationStatus, "OK");
    }
    this.createForm();
  }

  createForm() {
    this.loginForm = new FormGroup({
      'username': new FormControl(null),
      'password': new FormControl(null)
    })
  }

  onSubmit() {
    localStorage.removeItem('token');

    this.user.username = this.loginForm.controls['username'].value;
    this.user.password = this.loginForm.controls['password'].value;

    this.authService.login(this.user)
      .subscribe(data => {
        localStorage.setItem('token', data.jwt)
        console.log('login token: ' + localStorage.getItem('token'));
        this.router.navigate(["/profile"]);
      },
        error => {
          console.log(error);
          this.snackService.showSnack("Login failed!\nUsername and password not matching or account is not activated.\nPlease check your email.", "DISMISS");
          this.router.navigate(["/login"]);
          localStorage.removeItem('token');
        }
      );
  }

}
