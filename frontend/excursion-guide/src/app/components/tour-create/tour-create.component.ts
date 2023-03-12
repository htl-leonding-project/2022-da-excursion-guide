import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Person} from "../../model/person";
import {Topic} from "../../model/topic";
import {Activity} from "../../model/activity";
import {Event} from "../../model/event";
import {EventserviceService} from "../../services/eventservice.service";


@Component({
  selector: 'app-tour-create',
  templateUrl: './tour-create.component.html',
  styleUrls: ['./tour-create.component.css']
})
export class TourCreateComponent implements OnInit {

  event: Event = {
    id: -1,
    type: "",
    location: "",
    topics: [],
    maxPersonAllowed: 0,
    participant: [],
    planedEndDateTime: new Date(),
    planedStartDateTime: new Date()
  }
  topic: Topic = {
    activity: [],
    comment: "",
    id: -1,
    name: "",
    previousTopic: null,
  }
  savetopic: Topic = {
    activity: [],
    comment: "",
    id: -1,
    name: "",
    previousTopic: null,

  }
  tmpTopic: Topic = {
    activity: [],
    comment: "",
    id: -1,
    name: "",
    previousTopic: null,

  }

  participant: Person = {
    comment: "",
    firstname: "",
    id: -1,
    lastname: "",
    role: "",
    telephone: ""

  }

  activity: Activity = {
    activityName: "",
    comment: "",
    id: -1,
    latitude: 0,
    longitude: 0,
    previousActivity: null,
    public: false,
    publicationDate: new Date(),
    startDateTime: new Date(),
  }

  constructor(private eventService: EventserviceService) {
  }

  ngOnInit() {
  }

  AddParticipant() {
    console.log(this.participant);
    this.event.participant.push(this.participant);
    this.participant = {
      comment: "",
      firstname: "",
      id: -1,
      lastname: "",
      role: "",
      telephone: ""
    }
  }

  deselect(participant: Person) {
    this.event.participant = this.event.participant.filter(p => p.firstname !== participant.firstname && p.lastname !== participant.lastname);
  }

  AddTopic() {
    if(this.event.topics.length == 0 ){
      this.event.topics.push(this.topic);
      this.savetopic = this.topic;
    }
    else {
      this.tmpTopic = this.topic;
      this.topic.previousTopic = this.savetopic;
      this.event.topics.push(this.topic);
      this.savetopic= this.tmpTopic;
    }
    this.topic = {
      activity: [],
      comment: "",
      id: -1,
      name: "",
      previousTopic: null,
    }
    console.log(this.event.topics);
  }

  deselectTopic(topic: Topic) {
    if(this.event.topics.length == 1){
      this.event.topics = this.event.topics.filter(t => t.name !== topic.name);
    }
   /* else if(this.event.topics.length > 1){
      //set the previous topic of the topic to delete to the next topic in the array and delete the topic
      console.log(this.event.topics.filter(t => t.name !== topic.name));
      //console.log(this.event.topics[this.event.topics.filter(t => t.name !== topic.name).length -1 ].previousTopic)
      //console.log(this.event.topics[this.event.topics.filter(t => t.name !== topic.name).length ])
      this.event.topics[this.event.topics.filter(t => t.name !== topic.name).length -1] = this.event.topics[this.event.topics.filter(t => t.name !== topic.name).length];
      this.event.topics = this.event.topics.filter(t => t.name !== topic.name);
      console.log(this.event.topics);
    }*/
  }
}
