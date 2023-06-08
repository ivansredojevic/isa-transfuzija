import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { error } from 'console';
import { AppointmentModel } from 'src/app/model/appointment.model';
import { InsertComplaintDTO } from 'src/app/model/dto/insert.complaint.dto';
import { AppointmentService } from 'src/app/services/appointment.service';
import { ComplaintService } from 'src/app/services/complaint.service';

@Component({
  selector: 'app-create-complaint',
  templateUrl: './create-complaint.component.html',
  styleUrls: ['./create-complaint.component.css']
})
export class CreateComplaintComponent implements OnInit {

  appointmentId: number;
  appointment: AppointmentModel;
  complaintForm: FormGroup;
  complaintDto: InsertComplaintDTO;
  doctors: String[];

  constructor(private route: ActivatedRoute, private complaintService: ComplaintService, private appointmentService: AppointmentService) {
    this.route.queryParams.subscribe(params => {
      console.log(params);
      this.appointmentId = params['aptId'];
    });
  }

  ngOnInit(): void {
    this.createForm();
    this.getOne();
    this.doctors = this.appointment.doctors.split(',');
  }

  // username: string;
  // complaintText: string;
  // appointmentId: number;
  // centerId: number;
  // personnelId: number;

  createForm() {

    // this.doctors = this.appointment.doctors.split(',');
    console.log(this.doctors);
    // console.log(this.appointment);

   

    this.complaintForm = new FormGroup({
      'complaintText': new FormControl(null),
      'centerId': new FormControl(null),
      'personnelId': new FormControl(null)
    })
  }

  getOne(){
    this.appointmentService.getOne(this.appointmentId)
    .subscribe( data => {
      this.appointment = data.content;
      console.log(this.appointment);
    },
    error => {
      console.log(error);
    });
  }

  onSubmitComplaint() {

  }





}
