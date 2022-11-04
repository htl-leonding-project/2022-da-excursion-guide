import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EventserviceService {

  constructor(private http: HttpClient) { }

  getAllEvents():Observable<Event[]> {
    return this.http.get<Event[]>(environment.url+"/event/getAll");
  }
}
