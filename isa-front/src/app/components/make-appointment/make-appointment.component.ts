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
import { StorageService } from 'src/app/services/storage.service';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { error } from 'console';

@Component({
  selector: 'app-make-appointment',
  templateUrl: './make-appointment.component.html',
  styleUrls: ['./make-appointment.component.css']
})
export class MakeAppointmentComponent implements OnInit {

  public displayedColumns = ['id', 'center', 'startTime', 'duration', 'priceEuro', 'taken', 'approved', 'doctors', 'makeAppointment'];

  dataSource: MatTableDataSource<AppointmentModel>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  totalElements: number;
  pageIndex: number = 0;
  pageSize: number = 10;
  selectedRowIndex = -1;

  errorMessage: string = "";
  username: string;
  userCanDonate: boolean = false;

  constructor(public appointmentService: AppointmentService, public snackBar: MatSnackBar, public sessionStorage: StorageService, public authService: AuthService, public router: Router) { }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource;
    this.username = this.authService.getUsername();
    this.userCanDonate = JSON.parse(this.sessionStorage.getItem('canDonate'));
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
    console.log("Make appointment");
    if (!this.userCanDonate) {
      this.openSnackBar("You don\'t fulfill all conditions needed to make an appointment.", "OK");
    } else {
      let appointmentDto: AppointmentDTO = new AppointmentDTO();
      appointmentDto.id = appointment.id;
      appointmentDto.username = this.username;
      this.appointmentService.reserveAppointment(appointmentDto)
        .subscribe((data) => {
          this.router.navigate(['my-appointments']);

        },
          (error) => {
            console.log(error)
            this.openSnackBar("Error making appointment. Check if another appointment is overlaping with this one.", "OK");
          }
        );
    }
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
    this.appointmentService.getFreePageable(sort, page, size)
      .subscribe(data => {
        this.totalElements = data.totalElements;
        this.dataSource = new MatTableDataSource(data.content);
      },
        error => {
          console.log(error);
        });
  }

  openSnackBar(redirectReason: string, action: string) {
    const config = new MatSnackBarConfig();
    config.verticalPosition = 'top';
    config.duration = 5000;
    this.snackBar.open(redirectReason, action, config);
  }

  nextPage(event: PageEvent) {
    console.log(event);
    this.loadPage();
  }

}
