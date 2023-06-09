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
import { ComplaintModel } from 'src/app/model/complaint.model';
import { ComplaintService } from 'src/app/services/complaint.service';
import { SnackService } from 'src/app/services/snackHelper.service';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {

  public displayedColumns = ['id', 'complaintText', 'replyText', 'admin', 'appointment', 'personnelUser', 'center'];

  dataSource: MatTableDataSource<ComplaintModel>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  totalElements: number;
  pageIndex: number = 0;
  pageSize: number = 5;
  selectedRowIndex = -1;

  errorMessage: string = "";
  username: string;
  addComplaintResponse: string = "";

  constructor(public complaintService: ComplaintService, public authService: AuthService, public snackService: SnackService) { }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource;
    this.username = this.authService.getUsername();
    this.addComplaintResponse = history.state.addComplaintResponse;
    if (!!this.addComplaintResponse) {
      this.snackService.showSnack(this.addComplaintResponse, "OK");
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

  highlight(row: ComplaintModel) {
    this.selectedRowIndex = row.id;
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
    this.getMyComplaintsPageable(this.username, sort, pageIndex, pageSize);
  }

  getMyComplaintsPageable(username: string, sort: string, page: number, size: number) {
    this.complaintService.getMyComplaintsPageable(username, sort, page, size)
      .subscribe(data => {
        this.totalElements = data.totalElements;
        this.dataSource = new MatTableDataSource(data.content);
      },
        error => {
          console.log(error);
        });
  }

  nextPage(event: PageEvent) {
    this.loadPage();
  }

}
