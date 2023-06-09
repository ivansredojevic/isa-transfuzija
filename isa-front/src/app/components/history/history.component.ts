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

  public displayedColumns = ['id', 'center', 'startTime', 'duration', 'priceEuro', 'taken', 'approved', 'doctors', 'complainCenter' ,'complainDoctor'];

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

  onComplainCenter(appointmentId: number) {
    console.log("Make center complaint");
    this.router.navigate(['create-complaint'], {
      queryParams: { aptId: appointmentId, subject: 'center'},
    });
  }

  onComplainDoctor(appointmentId: number) {
    console.log("Make doctor complaint");
    this.router.navigate(['create-complaint'], {
      queryParams: { aptId: appointmentId, subject: 'doctor'},
    });
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
