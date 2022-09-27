-- Insert Test Event
insert into event (maxpersonallowed, name, planedenddatetime, planedstartdatetime, type)
values (100,'Test Event','2022-11-11 13:23:44','2022-11-01 13:23:44','Test Type');

-- Insert Test Person
insert into person (comment, firstname, lastname, role, telephone,event_id)
values ('No comment','Oliver','Sugic',1,'0123456789',1);
insert into person (comment, firstname, lastname, role, telephone,event_id)
values ('No comment','Test ','Person',1,'0123456789',1);
insert into person (comment, firstname, lastname, role, telephone,event_id)
values ('No comment','Person','Test',1,'0123456789',1);
insert into person (comment, firstname, lastname, role, telephone,event_id)
values ('No comment','Firstname','Lastname',2,'0123456789',1);

-- Insert Test Topic
insert into topic (comment, name, event_id)
values ('NO comment', 'Test Name', 1);
insert into topic (comment, name, event_id, previoustopic_id)
values ('NO comment', 'Test Name', 1, 1);


-- Insert Test Activity
insert into activity(activityname, comment, ispublic, latitude, longitude, publicationdate, startdatetime,topic_id)
values ('testactivity1','no comment',false,48.2685952973631, 14.25187750534175,current_date,'2008-11-11 13:23:44',1);
insert into activity(activityname, comment, ispublic, latitude, longitude, publicationdate, startdatetime,previousactivity_id,topic_id)
values ('testactivity1','no comment',false,48.2685952973631, 14.25187750534175,current_date,'2008-11-11 13:23:44',1,1);



