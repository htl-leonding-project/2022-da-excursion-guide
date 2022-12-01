export interface ActivityDto{
  id:number,
  activityName:String,
  startDateTime:Date,
  longitude:number,
  latitude:number,
  previousActivityId:number,
  comment:string,
  isPublic:boolean,
  publicationDate:Date,
  topicId:number,
}
