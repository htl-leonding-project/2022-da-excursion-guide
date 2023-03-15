import {Person} from "./person";
import {Topic} from "./topic";

export interface Event {
  id?:                  number;
  maxPersonAllowed:    number;
  location:            string;
  participant:         Person[];
  planedEndDateTime:   Date;
  planedStartDateTime: Date;
  topics:              Topic[];
  type:                string;
}
