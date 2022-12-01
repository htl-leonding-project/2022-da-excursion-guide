import {Component, OnInit} from '@angular/core';
import {EventserviceService} from "../../services/eventservice.service";
import {Event} from "../../model/event";
import {Topic} from "../../model/topic";
import {Activity} from "../../model/activity";

@Component({
  templateUrl: './tour-view.component.html',
  styleUrls: ['./tour-view.component.css'],
  selector: 'app-tour-view'
})
export class TourViewComponent implements OnInit {

  list: Event[] = [];

  selectedValue: Topic = {id: -1, activity: [], comment: "", name: "", previousTopic: null};

  constructor(private eventService: EventserviceService) {
  }

  ngOnInit(): void {
    this.getAllEvents();
  }

  getAllEvents() {
    this.eventService.getAllEvents()
      .subscribe((data: Event[]) => {
        this.list = data;
      });
  }


  getLinkForOpenStreetMap(activity: Activity) {
    return "https://www.openstreetmap.org/directions?engine=graphhopper_foot&route=%3B" + activity.latitude + "%2C" + activity.longitude;
  }

  getLinkForGoogleMaps(activity: Activity) {
    return "https://www.google.com/maps/dir/?api=1&destination=" + activity.latitude + "," + activity.longitude;
  }

}
