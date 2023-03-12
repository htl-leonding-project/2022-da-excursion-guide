import {Component, OnInit} from '@angular/core';
import { OidcSecurityService } from 'angular-auth-oidc-client';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  userData: any;

  constructor(public oidcSecurityService: OidcSecurityService) {
  }


  ngOnInit() {
    this.oidcSecurityService
      .checkAuth()
      .subscribe(({ isAuthenticated, userData, accessToken, idToken }) => {
        this.userData = userData;
        console.log(userData);
      });
  }

  login() {
    this.oidcSecurityService
      .authorizeWithPopUp()
      .subscribe(({ isAuthenticated, userData, accessToken, errorMessage }) => {
        this.userData = userData;
        console.log(userData);
      });
    //this.oidcSecurityService.authorize();
  }

  logout() {
    this.oidcSecurityService
      .logoff()
      .subscribe((result) => console.log(result));
  }

  getFormattedUserData() {
    return JSON.stringify(this.userData);
  }
}
