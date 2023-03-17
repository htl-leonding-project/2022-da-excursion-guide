import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TourViewComponent} from './components/tour-view/tour-view.component';
import {RouterModule, Routes} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UnlockActivityComponent} from './components/unlock-activity/unlock-activity.component';
import {TourCreateComponent} from './components/tour-create/tour-create.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AuthModule, LogLevel} from 'angular-auth-oidc-client';
import {LoginComponent} from './components/login/login.component';
import {OidcSecurityService} from 'angular-auth-oidc-client';
import {MatIconModule} from "@angular/material/icon";
import {MatStepperModule} from "@angular/material/stepper";
import {MatInputModule} from "@angular/material/input";
import {MatTableModule} from "@angular/material/table";
import {MatButtonModule} from "@angular/material/button";
import {MatSelectModule} from "@angular/material/select";
import {MatListModule} from "@angular/material/list";
import {MatRadioModule} from "@angular/material/radio";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatCardModule} from "@angular/material/card";
import {MatRippleModule} from "@angular/material/core";
import {AddActivitiesComponent} from './components/tour-create/add-activities/add-activities.component';
import {MAT_DIALOG_DEFAULT_OPTIONS, MatDialogModule} from "@angular/material/dialog";
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatGridListModule} from "@angular/material/grid-list";


const appRoutes: Routes = [
  {path: 'view', component: TourViewComponent},
  {path: 'create', component: TourCreateComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'unlock-activity', component: UnlockActivityComponent},
 // {path: 'login', component: LoginComponent},
  {path: '', component: TourViewComponent},
  //{ path: '**', component: PageNotFoundComponent }, // Wildcard route for a 404 page
];

@NgModule({
  declarations: [
    AppComponent,
    TourViewComponent,
    UnlockActivityComponent,
    TourCreateComponent,
    LoginComponent,
    AddActivitiesComponent,
    DashboardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    AuthModule.forRoot({
      config: {
        authority: 'https://auth.htl-leonding.ac.at/realms/htlleonding',
        redirectUrl: window.location.origin+ + '/o.sugic/leotour-frontend',
        postLogoutRedirectUri: window.location.origin ,
        clientId: 'htlleonding',
        scope: 'openid profile email offline_access',
        responseType: 'code',
        silentRenew: true,
        useRefreshToken: true,
        logLevel: LogLevel.Debug
      }
    }),
    MatIconModule,
    MatDialogModule,
    MatStepperModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,
    MatSelectModule,
    MatListModule,
    MatRadioModule,
    MatCheckboxModule,
    MatCardModule,
    MatRippleModule,
    MatToolbarModule,
    MatSidenavModule,
    MatGridListModule,
  ],
  exports: [],
  providers: [
    //{provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}
    TourViewComponent,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
