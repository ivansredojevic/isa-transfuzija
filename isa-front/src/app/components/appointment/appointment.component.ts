import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AppointmentModel } from 'src/app/model/appointment.model';
import { AppointmentService } from 'src/app/services/appointment.service';
import { merge } from 'rxjs';
import { tap } from 'rxjs/operators';
import { AuthService } from 'src/app/services/auth.service';
import { AppointmentDTO } from 'src/app/model/dto/appointment.dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit {

  public displayedColumns = ['id', 'center', 'startTime', 'duration', 'priceEuro', 'approved', 'doctors', 'cancelAppointment'];

  dataSource: MatTableDataSource<AppointmentModel>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  totalElements: number;
  pageIndex: number = 0;
  pageSize: number = 5;
  selectedRowIndex = -1;

  errorMessage: string = "";
  username: string;

  constructor(public appointmentService: AppointmentService, public authService: AuthService, public router: Router) { }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource;
    this.username = this.authService.getUsername();

    this.loadPage();
  }

  ngAfterViewInit(): void {
    this.sort.sortChange.subscribe(() => {
      this.paginator.pageIndex = 0;
    });
    this.dataSource.paginator = this.paginator;

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        tap(() => this.loadPage())
      ).subscribe();
  }

  highlight(row: AppointmentModel) {
    this.selectedRowIndex = row.id;
  }

  onCancel(appointment: AppointmentModel) {
    console.log("Cancel appointment");
    let appointmentDto: AppointmentDTO = new AppointmentDTO();
    appointmentDto.id = appointment.id;
    appointmentDto.username = this.username;
    this.appointmentService.cancelAppointment(appointmentDto)
      .subscribe((data) => {
        console.log(data);
        this.router.navigate(['make-appointment']);
      },
        (error) => console.log("Error cancelling appointment, " +error)
      );
  }
  
  loadPage() {
    let sort = "id,asc";
    if (this.sort) {
      let sortActive = "id";
      let sortDirection = "asc";
      if (this.sort.active) {
        sortActive = this.sort.active;
        sortDirection = this.sort.direction;
      }
      sort = sortActive + "," + sortDirection;
    }

    let pageIndex = 0;
    let pageSize = 5;
    if (this.paginator) {
      pageIndex = this.paginator.pageIndex;
      pageSize = this.paginator.pageSize;
    }
    this.getMyAppointmentsPageable(this.username, sort, pageIndex, pageSize);
  }

  getMyAppointmentsPageable(username: string, sort: string, page: number, size: number) {
    this.appointmentService.getMyAppointmentsPageable(username, sort, page, size)
      .subscribe(data => {
        this.totalElements = data.totalElements;
        this.dataSource = new MatTableDataSource(data.content);
      },
        error => {
          console.log(error);
        });
  }

  nextPage(event: PageEvent) {
    console.log(event);
    this.loadPage();
  }

}
