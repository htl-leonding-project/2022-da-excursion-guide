import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TourViewComponent} from './components/tour-view/tour-view.component';
import {RouterModule, Routes} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UnlockActivityComponent} from './components/unlock-activity/unlock-activity.component';
import { TourCreateComponent } from './components/tour-create/tour-create.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { AuthModule, LogLevel } from 'angular-auth-oidc-client';
import { LoginComponent } from './components/login/login.component';
import { OidcSecurityService } from 'angular-auth-oidc-client';



const appRoutes: Routes = [
  {path: 'view', component: TourViewComponent},
  {path: 'create', component: TourCreateComponent},
  {path: 'unlock-activity', component: UnlockActivityComponent},
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
        redirectUrl: window.location.origin,
        postLogoutRedirectUri: window.location.origin,
        clientId: 'htlleonding',
        scope: 'openid profile email offline_access',
        responseType: 'code',
        silentRenew: true,
        useRefreshToken: true,
        logLevel: LogLevel.Debug
      }
    })
  ],
  exports:[
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
