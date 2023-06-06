import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { error } from 'console';
import { Center } from 'src/app/model/center';
import { CenterService } from 'src/app/services/center.service';

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.css']
})
export class CenterComponent implements OnInit {

  // public dataSource = new MatTableDataSource<Center>();
  public displayedColumns = ['id', 'centerName', 'address', 'rating', 'openTime', 'closedTime'];
  public columnsToDisplay = this.displayedColumns.slice();
  public centers: Center[] = [];
  public totalElements: number = 0;
  public errorMessage: string;
  
  sizes: number[] = [5, 10];
  size: number = 5;
  page: number = 1;

  constructor(public centerService: CenterService) { }

  ngOnInit(): void {
    this.getCentersPageable();
  }

  

  getCentersPageable() {
    this.centerService.getAllPageable(this.page, this.size)
    .subscribe(data => {
      console.log(34);
      console.log(data);
      this.centers = data.content;
      this.totalElements = data.length;
      console.log(this.centers);
    },
    error => {
      console.log(error);
    });
  }

  nextPage(event: PageEvent) {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.getCentersPageable();
}

}
