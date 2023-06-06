import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  sex: String;
  registerForm: FormGroup;
  fieldRequired: string = "This field is required"

  constructor() { }

  ngOnInit() {
    this.createForm();
  }
  createForm() {
    let emailregex: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    this.registerForm = new FormGroup(
      {
        'username': new FormControl(null, [Validators.required]),
        'email': new FormControl(null, [Validators.required, Validators.pattern(emailregex)]),
        'password': new FormControl(null, [Validators.required, this.checkPassword]),
        'passwordConfirm': new FormControl(null, [Validators.required]),
        'name': new FormControl(null, [Validators.required]),
        'surname': new FormControl(null, [Validators.required]),
        'address': new FormControl(null),
        'city': new FormControl(null),
        'state': new FormControl(null),
        'phone': new FormControl(null),
        'jmbg': new FormControl(null),
        'sex' : new FormControl(null, [Validators.required]),
        'occupation': new FormControl(null),
        'jobinformation': new FormControl(null),
      }, { validators: passwordMatchingValidatior}
    )


  }

  checkPassword(control: { value: any; }) {
    let enteredPassword = control.value
    let passwordCheck = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{6,})/;
    return (!passwordCheck.test(enteredPassword) && enteredPassword) ? { 'requirements': true } : null;
  }



  
  onSubmit(formData: FormGroup, formDirective: FormGroupDirective): void {

    const email = formData.value.email;
    const password = formData.value.password;
    const passwordConfirm = formData.value.passwordConfirm;
    const username = formData.value.username;
    const sex = formData.value.sex;
    // this.auth.registerUSer(email, password, username);
    formDirective.resetForm();
    this.registerForm.reset();
  }

}

export const passwordMatchingValidatior: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const password = control.get('password');
  const passwordConfirm = control.get('passwordConfirm');

  return password?.value === passwordConfirm?.value ? null : { notmatched: true };
};
