import {Activity} from "./activity";

export interface Topic {
  activity:      Activity[];
  comment:       string;
  id?:            number;
  name:          string;
  previousTopic: Topic | null;
}
