import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { error } from 'console';
import { CenterModel } from 'src/app/model/center.model';
import { CenterService } from 'src/app/services/center.service';

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.css']
})
export class CenterComponent implements OnInit {

  // public dataSource = new MatTableDataSource<Center>();
  public displayedColumns = ['id', 'centerName', 'address', 'rating', 'openTime', 'closedTime'];
  public centers: CenterModel[] = [];

  dataSource: MatTableDataSource<CenterModel>;
  totalElements: number;
  pageIndex: number = 1;
  pageSize: number = 5;

  constructor(public centerService: CenterService) { }

  ngOnInit(): void {
    this.getCentersPageable(this.pageIndex, this.pageSize);
  }

  

  getCentersPageable(page: number, size: number) {
    this.centerService.getAllPageable(page, size)
    .subscribe(data => {
      console.log(data);
      this.centers = data.content;
      this.totalElements = data.totalElements;
      this.dataSource = new MatTableDataSource(data.content);
      this.pageIndex = data.pageIndex;
      this.pageSize = data.pageSize;
    },
    error => {
      console.log(error);
    });
  }

  nextPage(event: PageEvent) {
    this.getCentersPageable(event.pageIndex, event.pageSize);
}

}
