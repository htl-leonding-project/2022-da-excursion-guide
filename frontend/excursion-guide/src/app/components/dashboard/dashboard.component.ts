import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {MediaMatcher} from "@angular/cdk/layout";
import {Event} from "../../model/event";
import {EventserviceService} from "../../services/eventservice.service";
import {TourViewComponent} from "../tour-view/tour-view.component";


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, OnDestroy {

  events: Event[] = []
  selectedEvent: Event = {
    type: "",
    location: "",
    topics: [],
    maxPersonAllowed: 0,
    participant: [],
    planedEndDateTime: new Date(),
    planedStartDateTime: new Date(),
    currentEvent: false
  }


  fillerNav?: string[] = [];

  mobileQuery: MediaQueryList;


  fillerContent?: string[] = [];

  private _mobileQueryListener: () => void;
  expandButton: boolean = false;

  constructor(changeDetectorRef: ChangeDetectorRef,
              media: MediaMatcher,
              private eventService: EventserviceService) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  ngOnInit(): void {
    this.getAllEvents();
  }


  public getAllEvents() {
    this.eventService.getAllEvents()
      .subscribe((data: Event[]) => {
        this.events = data;
        this.fillerNav = Array.from(this.events, (_) => _.type + " " + _.location);
        this.fillerContent = Array.from(this.events, (_) => _.type + " " + _.location);
      });
  }

  setSelectedEvent(event: string) {
    this.selectedEvent = this.events.find(e => e.type + " " + e.location === event)!;
  }

  setEventForView() {
    this.eventService.SetCurrentEvent(this.selectedEvent.id!).subscribe();
  }
}
