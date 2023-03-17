import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Person} from "../../model/person";
import {Topic} from "../../model/topic";
import {Activity} from "../../model/activity";
import {Event} from "../../model/event";
import {EventserviceService} from "../../services/eventservice.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {AddActivitiesComponent} from "./add-activities/add-activities.component";
import {TourViewComponent} from "../tour-view/tour-view.component";


@Component({
  selector: 'app-tour-create',
  templateUrl: './tour-create.component.html',
  styleUrls: ['./tour-create.component.css']
})
export class TourCreateComponent implements OnInit {

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
  findTopic: Topic = {
    activity: [],
    comment: "",
    name: "",
    previousTopic: null,
  }
  selectedTopic: Topic = {
    activity: [],
    comment: "",
    name: "",
    previousTopic: null,
  }


  participant: Person = {
    comment: "",
    firstname: "",
    lastname: "",
    role: "",
    telephone: ""

  }

  activity: Activity = {
    activityName: "",
    comment: "",
    latitude: 0,
    longitude: 0,
    previousActivity: null,
    public: false,
    publicationDate: new Date(),
    startDateTime: new Date(),
  }
  displayedColumns = ['firstname', 'lastname', 'role', 'delete'];
  displayedColumnsTopic = ['name', 'activities', 'addActivity', 'delete'];
  displayedColumnsActivity = ['name', 'delete'];
  topicName: string = "";
  topicComment: string = "";

  constructor(private eventService: EventserviceService,
              public dialog: MatDialog,
              private view:TourViewComponent) {
  }

  ngOnInit() {
  }

  AddParticipant() {
    console.log(this.participant);
    this.event.participant.push(this.participant);
    this.participant = {
      comment: "",
      firstname: "",
      lastname: "",
      role: "",
      telephone: ""
    }
  }

  deselect(participant: Person) {
    this.event.participant = this.event.participant.filter(p => p.firstname !== participant.firstname && p.lastname !== participant.lastname);
  }

  AddTopic() {
    this.selectedTopic = {
      name: this.topicName,
      comment: this.topicComment,
      activity: [],
      previousTopic: this.selectedTopic.name === "" ? null : this.selectedTopic, // TODO set previous topic
    }

    this.topicName = "";
    this.topicComment = "";

    this.event.topics.push(this.selectedTopic);
  }

  getDatasource() {
    return new MatTableDataSource(this.event.participant);
  }

  getDatasourceTopic() {
    return new MatTableDataSource(this.event.topics);
  }

  getDatasourceActivity() {
    return new MatTableDataSource(this.selectedTopic.activity);
  }

  AddActivity() {
    const dialogRef = this.dialog.open(AddActivitiesComponent, {
      data: this.selectedTopic,
      width: '90vw',
      height: '90vh'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.selectedTopic = result;

      // TODO remove topic from event.topics and add the selectedTopic to the list
    });

    console.log(this.event)
  }

  deselectTopic(topic: Topic) {
    if (this.event.topics.length == 1) {
      this.event.topics = this.event.topics.filter(t => t.name !== topic.name);
    } else if (this.event.topics.length == this.event.topics.indexOf(topic) + 1) {
      this.event.topics = this.event.topics.filter(t => t.name !== topic.name);
    } else if (this.event.topics.length > 1) {
      this.event.topics[this.event.topics.indexOf(topic) + 1].previousTopic = this.event.topics[this.event.topics.indexOf(topic) - 1];
      this.event.topics = this.event.topics.filter(t => t.name !== topic.name);
    }
  }

  deselectActivity(activity: Activity) {

    this.findTopic = this.event.topics.find(t => t.name === this.selectedTopic.name)!;
    let pos = this.event.topics.indexOf(this.findTopic);
    console.log(pos);
    console.log(this.event.topics[pos].activity);
    console.log(this.event.topics[pos].activity);
    if (this.event.topics.length == 1 && this.event.topics[1].activity.length == 1) {
      this.event.topics[0].activity = this.event.topics[0].activity.filter(a => a.activityName !== activity.activityName);

    } else if (this.event.topics[pos].activity.length == this.event.topics[pos].activity.indexOf(activity) + 1) {
      this.event.topics[pos].activity = this.event.topics[pos].activity.filter(t => t.activityName !== activity.activityName);

    } else if (this.event.topics.length > 1) {
      this.event.topics[pos].activity[this.event.topics[pos].activity.indexOf(activity) + 1].previousActivity =
        this.event.topics[pos].activity[this.event.topics[pos].activity.indexOf(activity) - 1];


      this.event.topics[pos].activity = this.event.topics[pos].activity.filter(t => t.activityName !== activity.activityName);
    }
    console.log(this.event.topics);
  }

  SelectTopic(element: Topic) {
    this.selectedTopic = element;
  }

  CreateEvent() {
    this.eventService.createEvent(this.event).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
    this.view.ngOnInit();
  }
}
