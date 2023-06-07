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
import { ComplaintService } from 'src/app/services/complaint.service';
import { InsertComplaintDTO } from 'src/app/model/dto/insert.complaint.dto';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  public displayedColumns = ['id', 'center', 'startTime', 'duration', 'priceEuro', 'taken', 'approved', 'doctors', 'complain'];

  dataSource: MatTableDataSource<AppointmentModel>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  totalElements: number;
  pageIndex: number = 0;
  pageSize: number = 5;
  selectedRowIndex = -1;

  errorMessage: string = "";
  username: string;

  constructor(public appointmentService: AppointmentService, public authService: AuthService, public complaintService: ComplaintService, public router: Router) { }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource;
    this.username = this.authService.getUsername();

    this.loadPage();
    console.log("DATASOURCE");
    console.log(this.dataSource);
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
    console.log(row);
  }

  onComplain(appointment: AppointmentModel) {
    console.log("Make complaint");
    console.log(appointment);
    let complaintDto = new InsertComplaintDTO();
    // complaintDto.username = this.username;
    // complaintDto.appointmentId = appointment.id;
    // complaintDto.a


    // this.complaintService.reserveAppointment(appointment)
    //   .subscribe((data) => {
    //     console.log(data);
    //     this.router.navigate(["my-appointments"]);
    //   },
    //     (error) => console.log("75: " + error)
    //   );
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
      console.log(sort);
    }

    let pageIndex = 0;
    let pageSize = 5;
    if (this.paginator) {
      pageIndex = this.paginator.pageIndex;
      pageSize = this.paginator.pageSize;
    }
    console.log(sort);
    this.getMyHistoryPageable(this.username, sort, pageIndex, pageSize);
  }

  getMyHistoryPageable(username: string, sort: string, page: number, size: number) {
    this.appointmentService.getMyHistoryPageable(username, sort, page, size)
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
