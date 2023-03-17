import {Component, OnInit} from '@angular/core';
import {OidcSecurityService} from 'angular-auth-oidc-client';
import {keycloakoutput} from "../../model/keycloak-output";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userData: keycloakoutput = {
    email_verified: true,
    family_name: "",
    name: "",
    sub: "",
    given_name: "",
    preferred_username: ""
  };

  isStudent: boolean = false;
  isTeacher: boolean = false;

  constructor(public oidcSecurityService: OidcSecurityService) {
  }


  ngOnInit() {
    this.oidcSecurityService
      .checkAuth()
      .subscribe(({isAuthenticated, userData, accessToken, idToken}) => {
        console.log(userData)
        this.userData = userData;
      });
    if (this.userData.preferred_username.indexOf('.') > -1 || this.userData.preferred_username == "if180157") {
      this.isTeacher = true
    } else {
      this.isStudent = true;
    }

  }
  login() {
    this.oidcSecurityService
      .authorizeWithPopUp()
      .subscribe(({isAuthenticated, userData, accessToken, errorMessage}) => {
        this.userData = userData;
      });
    this.oidcSecurityService.authorize();
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
