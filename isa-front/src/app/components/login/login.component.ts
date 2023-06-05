import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,FormGroupDirective } from '@angular/forms'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.createForm();
  }
  
  createForm(){
    this.loginForm = new FormGroup({
      'username': new FormControl(null),
      'password': new FormControl(null)
    })
  }

  onSubmit( formData: FormGroup, loginDirective: FormGroupDirective){
    const email = formData.value.email;
    const password = formData.value.password;
    // this.authService.signinUser(email, password);
  }

}
