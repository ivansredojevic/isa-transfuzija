import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentModel } from 'src/app/model/appointment.model';
import { DoctorHelper } from 'src/app/model/complaint.helper';
import { InsertComplaintDTO } from 'src/app/model/dto/insert.complaint.dto';
import { AppointmentService } from 'src/app/services/appointment.service';
import { AuthService } from 'src/app/services/auth.service';
import { ComplaintService } from 'src/app/services/complaint.service';
import { SnackService } from 'src/app/services/snackHelper.service';

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

  constructor(private authService: AuthService, private router: Router, private snackService: SnackService, 
    private complaintService: ComplaintService, private appointmentService: AppointmentService, private activatedRoute: ActivatedRoute) {
      this.activatedRoute.queryParams
      .subscribe(params => {
        console.log(params);
        this.appointmentId = params['aptId'];
        this.subject = params['subject'];
      }
      );
     }

  ngOnInit(): void {
    this.appointmentId = history.state.aptId;
    this.subject = history.state.subject;
    this.getOne();
    this.createForm();
    if (this.subject == 'center') {
      this.isDoctor = false;
    } else {
      this.isDoctor = true;
    }
  }

  createForm() {
    this.complaintForm = new FormGroup(
      {
        'complaintText': new FormControl(null, [Validators.required, Validators.maxLength(200)]),
        'personnelId': new FormControl(null) ,
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

    this.complaintDto.appointmentId = this.appointment.id;


    this.complaintService.addComplaint(this.complaintDto)
      .subscribe(data => {
        this.router.navigate(["/complaint"], {
          state: { addComplaintResponse: "Complaint added." }
        })
      },
        error => {
          console.log(error);
          this.snackService.showSnack(error, "OK");
        }
      );
    this.complaintForm.reset();
  }

}
