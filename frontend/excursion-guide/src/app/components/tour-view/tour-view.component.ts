import { Component, OnInit } from '@angular/core';
import {EventserviceService} from "../../services/eventservice.service";
import {Event} from "../../model/event";

@Component({
  templateUrl: './tour-view.component.html',
  styleUrls: ['./tour-view.component.css'],
  selector: 'app-tour-view'
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
