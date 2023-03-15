import {Component, Inject} from '@angular/core';
import {Activity} from "../../../model/activity";
import {Topic} from "../../../model/topic";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-add-activities',
  templateUrl: './add-activities.component.html',
  styleUrls: ['./add-activities.component.css']
})
export class AddActivitiesComponent {

  constructor(
    public dialogRef: MatDialogRef<AddActivitiesComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Topic,
  ) {}


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

  AddActivity() {
    this.data.activity.push(this.activity);

    this.dialogRef.close(this.data);
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
}
