import {Component, OnInit} from '@angular/core';
import {Event} from "../../model/event";
import {EventserviceService} from "../../services/eventservice.service";
import {Activity} from "../../model/activity";
import {ActivityDto} from "../../model/activityDto";
import {Topic} from "../../model/topic";

@Component({
  selector: 'app-unlock-activity',
  templateUrl: './unlock-activity.component.html',
  styleUrls: ['./unlock-activity.component.css']
})
export class UnlockActivityComponent implements OnInit {

  list: Event[] = [];

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
}
