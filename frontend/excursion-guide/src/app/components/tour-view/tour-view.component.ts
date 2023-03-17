import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {EventserviceService} from "../../services/eventservice.service";
import {Event} from "../../model/event";
import {Topic} from "../../model/topic";
import {Activity} from "../../model/activity";
import {ActivatedRoute, Params} from "@angular/router";

@Component({
  templateUrl: './tour-view.component.html',
  styleUrls: ['./tour-view.component.css'],
  selector: 'app-tour-view'
})
export class TourViewComponent implements OnInit {
  events: Event[] = [];
  event: Event = {
    type: "",
    location: "",
    topics: [],
    maxPersonAllowed: 0,
    participant: [],
    planedEndDateTime: new Date(),
    planedStartDateTime: new Date(),
    currentEvent:false
  }
  id: number = 0;

  latitude: number = 0;
  longitude: number = 0;

  selectedValue: Topic = {id: -1, activity: [], comment: "", name: "", previousTopic: null};
  notselected: Topic = {id: -1, activity: [], comment: "", name: "", previousTopic: null};

  constructor(private activatedRoute: ActivatedRoute,
              private eventService: EventserviceService) {
  }

  ngOnInit(): void {
    this.getAllEvents();
   // this.getUserLocation();
    this.getCurrentEvent();

  }

  public getAllEvents() {
    this.eventService.getAllEvents()
      .subscribe((data: Event[]) => {
        this.events = data;
      });
  }


  getLinkForOpenStreetMap(activity: Activity) {
    return "https://www.openstreetmap.org/directions?engine=graphhopper_foot&route=" + this.latitude + "%2C" + this.longitude + "%3B" + activity.latitude + "%2C" + activity.longitude;
  }

  getLinkForGoogleMaps(activity: Activity) {
    return "https://www.google.com/maps/dir/?api=1&origin=" + this.latitude + "," + this.longitude + "&destination=" + activity.latitude + "," + activity.longitude;
  }

  getUserLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(position => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
      });
    } else {
      console.log("User not allow")
    }
  }

  openDefaultMap(activity: Activity) {
    return "https://maps.apple.com/?daddr=" + activity.latitude + "," + activity.longitude + "&saddr=" + this.latitude + "," + this.longitude;
  }


  getCurrentEvent() {
    this.eventService.getCurrentEvent()
      .subscribe((data: Event) => {
        this.event = data;
      });
  }
}
