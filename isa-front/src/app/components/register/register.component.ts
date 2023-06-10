import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { ApplicationUserDTO } from 'src/app/model/dto/applicationUser.dto';
import { DatePipe } from '@angular/common'
import { SnackService } from 'src/app/services/snackHelper.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ApplicationUserService } from 'src/app/services/application.user.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  sex: String;
  redirectString: string = "";
  
  activationStatus: string;

  constructor(public datepipe: DatePipe, public router: Router, public userService: ApplicationUserService, public snackService: SnackService, public activatedRoute: ActivatedRoute) { 
    this.activatedRoute.queryParams.subscribe(params => {
      console.log(params);
      this.redirectString = params['activation'];
      console.log(this.redirectString);
    });
  }

  ngOnInit() {
    this.activationStatus = history.state.activationStatusMessage;

    if (!!this.activationStatus) {
      this.snackService.showSnack(this.activationStatus, "OK");
    }
    this.createForm();
  }
  createForm() {
    let emailregex: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    this.registerForm = new FormGroup(
      {
        'username': new FormControl(null, [Validators.required]),
        'email': new FormControl(null, [Validators.required, Validators.pattern(emailregex)]),
        'password': new FormControl(null, [Validators.required, Validators.minLength(4)]),
        'passwordConfirm': new FormControl(null, [Validators.required]),
        'name': new FormControl(null, [Validators.required]),
        'surname': new FormControl(null, [Validators.required]),
        'address': new FormControl(null),
        'city': new FormControl(null),
        'state': new FormControl(null),
        'phone': new FormControl(null),
        'jmbg': new FormControl(null),
        'sex': new FormControl(null, [Validators.required]),
        'date': new FormControl(new Date(), [Validators.required]),
        'occupation': new FormControl(null),
        'jobinformation': new FormControl(null),
      }, { validators: passwordMatchingValidatior }
    );
  }

  onSubmit(formData: FormGroup, formDirective: FormGroupDirective): void {

    const email = formData.value.email;
    const password = formData.value.password;
    const username = formData.value.username;
    const sex = formData.value.sex;
    const date = formData.value.date;
    const name = formData.value.name;
    const surname = formData.value.surname;
    const address = formData.value.address;
    const phone = formData.value.phone;
    const jmbg = formData.value.jmbg;
    const occupation = formData.value.occupation;
    const jobinformation = formData.value.jobinformation;

    let applicationUser: ApplicationUserDTO = new ApplicationUserDTO();
    applicationUser.username = username;
    applicationUser.password = password;
    applicationUser.email = email;
    applicationUser.name = name;
    applicationUser.surname = surname;
    applicationUser.address = address;
    applicationUser.phone = phone;
    applicationUser.jmbg = jmbg;
    applicationUser.sex = sex;
    applicationUser.lastDonationDate = this.convertDateString(date);
    applicationUser.occupation = occupation;
    applicationUser.jobinformation = jobinformation;
    // hardcoded for regular users
    applicationUser.role = "USER";

    this.userService.register(applicationUser)
      .subscribe(
        (data) => {
          let response: string = data.response;
          if (response.startsWith("User with ")) {
            this.snackService.showSnack(response, "OK");
          } else {
            this.router.navigate(["/login"], {
              state: { addRegisterResponse: data.response }
            })
          }
        },
        error => {
          console.log(error);
          this.snackService.showSnack(error, "OK")
        }
      );
  }

  convertDateString(date: Date) {
    let newDate: string;
    newDate = this.datepipe.transform(date, 'yyy-MM-dd')!;
    return newDate;
  }

}

export const passwordMatchingValidatior: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const password = control.get('password');
  const passwordConfirm = control.get('passwordConfirm');

  return password?.value === passwordConfirm?.value ? null : { notmatched: true };
};
