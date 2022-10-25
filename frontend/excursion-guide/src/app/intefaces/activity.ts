export interface Activity {
  activityName:     string;
  comment:          string;
  id:               number;
  latitude:         number;
  longitude:        number;
  previousActivity: Activity | null;
  public:           boolean;
  publicationDate:  Date;
  startDateTime:    Date;
}
