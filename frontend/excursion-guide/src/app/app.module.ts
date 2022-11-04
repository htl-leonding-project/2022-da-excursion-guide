import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TourCreationComponent } from './component/tour-creation/tour-creation.component';
import { TourViewComponent } from './components/tour-view/tour-view.component';

@NgModule({
  declarations: [
    AppComponent,
    TourCreationComponent,
    TourViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
