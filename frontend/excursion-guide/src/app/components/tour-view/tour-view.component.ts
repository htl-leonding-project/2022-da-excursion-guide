import { Component, OnInit } from '@angular/core';
import {EventserviceService} from "../../services/eventservice.service";
import {Event} from "../../model/event";

@Component({
  selector: 'app-tour-view',
  templateUrl: './tour-view.component.html',
  styleUrls: ['./tour-view.component.css']
})
export class TourViewComponent implements OnInit {

  list: Event[]=[];

  constructor(private eventService: EventserviceService) {
  }
  ngOnInit(): void {
    this.getAllEvents();
  }

  getAllEvents() {
    this.eventService.getAllEvents()
      .subscribe((data: Event[]) =>
      {
        this.list = data;
      });
  }

}
