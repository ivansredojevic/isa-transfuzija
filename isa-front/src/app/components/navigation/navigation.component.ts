import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { MatMenu } from '@angular/material/menu';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  loggedIn = false;
  username: string = "";
  user: any;

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  onLogout() {
    this.loggedIn = false;
    this.username = "";
    this.authService.logout();
  }

  authenticated() {
    this.username = this.authService.getUsername();    
    return this.authService.authenticated();
  }

}
