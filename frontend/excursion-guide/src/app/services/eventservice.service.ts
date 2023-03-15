import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Event} from "../model/event";
import {Activity} from "../model/activity";
import {ActivityDto} from "../model/activityDto";
import { HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
//,'Authorization': 'my-auth-token'
  })
}
@Injectable({
  providedIn: 'root'
})
export class EventserviceService {

  constructor(private http: HttpClient) { }

  getAllEvents():Observable<Event[]> {
    return this.http.get<Event[]>(environment.url+"/event/getAll");
  }

  updateActivity(activity: ActivityDto) {
    return this.http.put<ActivityDto>(environment.url+"/activity/editActivity/"+activity.id, activity);
  }

  createEvent(event: Event) {
    return this.http.post<Event>(environment.url+"/event/addEvent", event,httpOptions);
  }
}
