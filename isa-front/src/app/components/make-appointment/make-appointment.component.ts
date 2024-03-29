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
import { SnackService } from 'src/app/services/snackHelper.service';

@Component({
  selector: 'app-make-appointment',
  templateUrl: './make-appointment.component.html',
  styleUrls: ['./make-appointment.component.css']
})
export class MakeAppointmentComponent implements OnInit {

  public displayedColumns = ['id', 'center', 'startTime', 'duration', 'endTime', 'priceEuro', 'taken', 'approved', 'doctors', 'makeAppointment'];

  dataSource: MatTableDataSource<AppointmentModel>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  totalElements: number;
  pageIndex: number = 0;
  pageSize: number = 10;
  selectedRowIndex = -1;

  errorMessage: string = "";
  username: string;
  // userCanDonate: boolean = false;
  response: string = "";
  cancelAppointmentResult: string = "";

  constructor(private appointmentService: AppointmentService, private snackService: SnackService,
    private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource;
    this.username = this.authService.getUsername();
    // this.userCanDonate = JSON.parse(this.sessionStorage.getItem('canDonate'));
    this.cancelAppointmentResult = history.state.cancelAppointmentResult;
    if (!!this.cancelAppointmentResult) {
      this.snackService.showSnack(this.cancelAppointmentResult, "OK");
    }
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

  onMake(appointment: AppointmentModel) {

    let appointmentDto: AppointmentDTO = new AppointmentDTO();
    appointmentDto.id = appointment.id;
    appointmentDto.username = this.username;
    this.appointmentService.reserveAppointment(appointmentDto)
      .subscribe((data) => {
        if (data.id == 0) {
          this.snackService.showSnack(data.response, "OK");
        } else {
          this.response = data.response;
          this.router.navigate(['my-appointments'], {
              state: { makeAppointmentResult: this.response }
          })
        }
      },
        (error) => {
          console.log(error);
          this.snackService.showSnack("Error making appointment.", "OK");
        }
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
      sort = sortActive + "," + sortDirection
    }

    let pageIndex = 0;
    let pageSize = 10;
    if (this.paginator) {
      pageIndex = this.paginator.pageIndex;
      pageSize = this.paginator.pageSize;
    }
    this.getFreePageable(sort, pageIndex, pageSize);
  }

  getFreePageable(sort: string, page: number, size: number) {
    this.appointmentService.getFreePageable(this.username, sort, page, size)
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
