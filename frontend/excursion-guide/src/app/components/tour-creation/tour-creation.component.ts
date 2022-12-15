import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-tour-creation',
  templateUrl: './tour-creation.component.html',
  styleUrls: ['./tour-creation.component.css']
})
export class TourCreationComponent implements OnInit {
  public firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  public secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  public isLinear = false;

  constructor(private _formBuilder: FormBuilder) {}
  ngOnInit(): void {
  }

}
