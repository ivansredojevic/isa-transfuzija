import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,FormGroupDirective } from '@angular/forms'
import { AuthUserModel } from 'src/app/model/auth.user.model';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { ApplicationUserService } from 'src/app/services/application.user.service';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  errorMessage: string = "";
  private user: AuthUserModel = new AuthUserModel();

  constructor(public authService: AuthService, private router: Router, 
      public userService: ApplicationUserService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.createForm();
  }
  
  createForm(){
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
          console.log(localStorage.getItem('token'));
          this.router.navigate(["/profile"]);
        },
        error => {
          console.log(error);
          this.openSnackBar("Login failed!\nUsername and password not matching or account is not activated.\nPlease check your email.", "DISMISS");
          localStorage.removeItem('token');
        }
    );
  }

  openSnackBar(redirectReason: string, action: string) {
    const config = new MatSnackBarConfig();
    config.verticalPosition = 'top';
    config.duration = 5000;
    this.snackBar.open(redirectReason, action, config);
  }

}
