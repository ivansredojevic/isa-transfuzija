import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { map, shareReplay } from 'rxjs/operators';
import { OverlayContainer } from '@angular/cdk/overlay';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  
  loggedIn = false;



  constructor(private breakpointObserver: BreakpointObserver,
    private overlayContainer: OverlayContainer) {}

    ngOnInit() {
    }


  onLogOut() {
    localStorage.removeItem('token');
  }

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

}
