import {Component, OnInit} from '@angular/core';
import {Event} from "../../model/event";
import {EventserviceService} from "../../services/eventservice.service";
import {Activity} from "../../model/activity";
import {ActivityDto} from "../../model/activityDto";
import {Topic} from "../../model/topic";
import {TourViewComponent} from "../tour-view/tour-view.component";

@Component({
  selector: 'app-unlock-activity',
  templateUrl: './unlock-activity.component.html',
  styleUrls: ['./unlock-activity.component.css']
})
export class UnlockActivityComponent implements OnInit {

  list: Event[] = [];

  event: Event = {
    type: "",
    location: "",
    topics: [],
    maxPersonAllowed: 0,
    participant: [],
    planedEndDateTime: new Date(),
    planedStartDateTime: new Date(),
    currentEvent: false
  }
  cols: number = 3;

  constructor(private eventService: EventserviceService) {
  }

  ngOnInit(): void {
    //this.getAllEvents();
    this.getCurrentEvent()
  }

  getAllEvents() {
    this.eventService.getAllEvents()
      .subscribe((data: Event[]) => {
        this.list = data;
      });
  }

  changeVisibility(activity: Activity, topic: Topic) {
    let activityDto: ActivityDto = {
      id: activity.id,
      activityName: activity.activityName,
      comment: activity.comment,
      latitude: activity.latitude,
      longitude: activity.longitude,
      startDateTime: activity.startDateTime,
      publicationDate: activity.publicationDate,
      isPublic: !activity.public,
      previousActivityId: activity.previousActivity?.id || 0,
      topicId: topic.id
    };


    activity.public = !activity.public;
    this.eventService.updateActivity(activityDto)
      .subscribe((data: ActivityDto) => {
        this.getAllEvents();
      });
  }

  getCurrentEvent() {
    this.eventService.getCurrentEvent()
      .subscribe((data: Event) => {
        this.event = data;
      });
  }

  handleResizeEvent(resizeevent: UIEvent) {
    //console.log(<Window>resizeevent.target)
    const target = <Window>resizeevent.target

 /*
    if (target.innerWidth >= 350 ) {
      this.cols = 3;
    } else if (target.innerWidth <= 350) {
      this.cols = 2;
    }

  */
  }
}
