import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CenterModel } from 'src/app/model/center.model';
import { CenterService } from 'src/app/services/center.service';
import { merge } from 'rxjs';
import { tap } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';
import { SnackService } from 'src/app/services/snackHelper.service';

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.css']
})
export class CenterComponent implements OnInit {

  public displayedColumns = ['id', 'centerName', 'address', 'rating', 'openTime', 'closedTime'];

  redirectReason: string = "_";

  dataSource: MatTableDataSource<CenterModel>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  totalElements: number;
  pageIndex: number = 0;
  pageSize: number = 10;
  selectedRowIndex = -1;

  constructor(private centerService: CenterService, private route: ActivatedRoute, private snackService: SnackService) {
    this.route.queryParams.subscribe(params => {
      this.redirectReason = params['redirected'];
    });
  }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource;
    this.loadPage();
    if (!!this.redirectReason) {
      this.snackService.showSnack("Requested page does not exist", "OK");
    }
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

  highlight(row: CenterModel) {
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
    let pageSize = 10;
    if (this.paginator) {
      pageIndex = this.paginator.pageIndex;
      pageSize = this.paginator.pageSize;
    }
    this.getCentersPageable(sort, pageIndex, pageSize);
  }

  getCentersPageable(sort: string, page: number, size: number) {
    this.centerService.getAllPageable(sort, page, size)
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
