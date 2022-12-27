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
  latitude: number = 0;
  longitude: number = 0;

  selectedValue: Topic = {id: -1, activity: [], comment: "", name: "", previousTopic: null};
  notselected: Topic = {id: -1, activity: [], comment: "", name: "", previousTopic: null};

  constructor(private eventService: EventserviceService) {
  }

  ngOnInit(): void {
    this.getAllEvents();
    this.getUserLocation();
  }

  public getAllEvents() {
    this.eventService.getAllEvents()
      .subscribe((data: Event[]) => {
        this.list = data;
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
    return "https://maps.apple.com/?daddr=" + activity.latitude + "," + activity.longitude+"&saddr="+this.latitude+","+this.longitude;
  }
}
