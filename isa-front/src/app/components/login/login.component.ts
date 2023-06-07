import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,FormGroupDirective } from '@angular/forms'
import { AuthUserModel } from 'src/app/model/auth.user.model';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  errorMessage: string = "";
  private user: AuthUserModel = new AuthUserModel();

  constructor(public authService: AuthService, private router: Router) { }

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
          this.errorMessage = '';
          console.log(localStorage.getItem('token'));
          this.router.navigate(["/centers"]);
        },
        error => {
          console.log(error);
          this.errorMessage = 'Bad credentials';
          alert(this.errorMessage);
          localStorage.removeItem('token');
        }
    );
  }

}
