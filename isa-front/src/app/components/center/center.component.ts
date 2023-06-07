import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CenterModel } from 'src/app/model/center.model';
import { CenterService } from 'src/app/services/center.service';
import { merge } from 'rxjs';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.css']
})
export class CenterComponent implements OnInit {

  public displayedColumns = ['id', 'centerName', 'address', 'rating', 'openTime', 'closedTime'];

  dataSource: MatTableDataSource<CenterModel>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  totalElements: number;
  pageIndex: number = 0;
  pageSize: number = 5;
  selectedRowIndex = -1;

  constructor(public centerService: CenterService) { }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource;
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
      console.log(sort);
    }

    let pageIndex = 0;
    let pageSize = 5;
    if (this.paginator) {
      pageIndex = this.paginator.pageIndex;
      pageSize = this.paginator.pageSize;
    }
    console.log(sort);
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
