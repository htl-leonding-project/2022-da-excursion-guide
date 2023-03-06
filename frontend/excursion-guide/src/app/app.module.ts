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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
  ],
  exports:[
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
