import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, FormGroupDirective, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { error } from 'console';
import { AppointmentModel } from 'src/app/model/appointment.model';
import { DoctorHelper } from 'src/app/model/complaint.helper';
import { InsertComplaintDTO } from 'src/app/model/dto/insert.complaint.dto';
import { AppointmentService } from 'src/app/services/appointment.service';
import { AuthService } from 'src/app/services/auth.service';
import { ComplaintService } from 'src/app/services/complaint.service';

@Component({
  selector: 'app-create-complaint',
  templateUrl: './create-complaint.component.html',
  styleUrls: ['./create-complaint.component.css']
})
export class CreateComplaintComponent implements OnInit {

  appointmentId: number;
  appointment: AppointmentModel = new AppointmentModel();
  complaintForm: FormGroup;
  complaintDto: InsertComplaintDTO = new InsertComplaintDTO('', '', 0, 0, 0);
  doctors = [] as DoctorHelper[];
  docNames = [] as string[];
  subject: string;
  isDoctor: boolean = false;

  firstSelect: boolean = false;
  secondSelect: boolean = false;


  text: string = '';
  doctor: string = '';
  center: string = '';

  constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute, private complaintService: ComplaintService, private appointmentService: AppointmentService) {

    this.route.queryParams.subscribe(params => {
      console.log(params);
      this.appointmentId = params['aptId'];
      this.subject = params['subject']
    });
  }

  ngOnInit(): void {
    this.getOne();
    this.createForm();
    if (this.subject == 'center') {
      this.isDoctor = false;
    } else {
      this.isDoctor = true;
    }
    console.log(this.subject)
    console.log(this.isDoctor)
    console.log(this.doctors);
  }

  // username: string;
  // complaintText: string;
  // appointmentId: number;
  // centerId: number;
  // personnelId: number;

  createForm() {
    this.complaintForm = new FormGroup(
      {

        'complaintText': new FormControl(null, [Validators.required, Validators.maxLength(200)]),//[null, Validators.required, Validators.maxLength(200)]),
        'personnelId': new FormControl(null),

        // 'complaintText': new FormControl(null),//[null, Validators.required, Validators.maxLength(200)]),
        // 'centerId': new FormControl([null, Validators.required]),
        // 'personnelId': new FormControl([null, Validators.required]),
      }
    )
  }

  getOne() {
    let resp = this.appointmentService.getOne(this.appointmentId)
      .subscribe(data => {
        this.appointment = data;
        this.docNames = this.appointment.doctors.split(',');
        for (let index = 0; index < this.appointment.doctorIds.length; index++) {
          this.doctors.push(new DoctorHelper(this.appointment.doctorIds[index], this.docNames[index]));
        }
        console.log(this.doctors);
        console.log(this.appointment);
      },
        error => {
          console.log(error);
        });
  }

  onSubmitComplaint() {

    this.text = this.complaintForm.controls['complaintText'].value;
    this.doctor = this.complaintForm.controls['personnelId'].value;

    this.complaintDto.complaintText = this.text;
    this.complaintDto.username = this.authService.getUsername();

    this.isDoctor ? this.complaintDto.personnelId = +this.doctor : this.complaintDto.centerId = this.appointment.centerId;

    // if(!this.isDoctor) {

    // } else {

    // }
    this.complaintDto.appointmentId = this.appointment.id;
    console.log(this.complaintDto);


    this.complaintService.addComplaint(this.complaintDto)
      .subscribe(data => {
        console.log(data);
        this.router.navigate(["/complaint"]);
      },
        error => {
          console.log(error);
          alert(error);
        }
      );

    this.complaintForm.reset();
  }

}



