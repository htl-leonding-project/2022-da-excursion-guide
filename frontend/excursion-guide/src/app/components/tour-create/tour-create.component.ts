import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Person} from "../../model/person";
import {Topic} from "../../model/topic";
import {Activity} from "../../model/activity";
import {Event} from "../../model/event";


@Component({
  selector: 'app-tour-create',
  templateUrl: './tour-create.component.html',
  styleUrls: ['./tour-create.component.css']
})
export class TourCreateComponent implements OnInit {


  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit() {
  }
}
