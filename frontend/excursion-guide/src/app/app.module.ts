import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TourViewComponent} from './components/tour-view/tour-view.component';
import {RouterModule, Routes} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UnlockActivityComponent} from './components/unlock-activity/unlock-activity.component';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatTableModule} from "@angular/material/table";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatStepperModule} from "@angular/material/stepper";
import {MatButtonModule} from "@angular/material/button";
import {MatSelectModule} from "@angular/material/select";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import { TourCreateComponent } from './components/tour-create/tour-create.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatCheckboxModule} from "@angular/material/checkbox";

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
    MatSlideToggleModule,
    MatTableModule,
    MatButtonToggleModule,
    MatStepperModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatCheckboxModule
  ],
  exports:[
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
