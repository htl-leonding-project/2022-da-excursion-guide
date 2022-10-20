create table Activity
(
    id                  int8 generated by default as identity,
    activityName        varchar(255),
    comment             varchar(4000),
    isPublic            boolean not null,
    latitude            float8  not null,
    longitude           float8  not null,
    publicationDate     date,
    startDateTime       timestamp,
    previousActivity_id int8,
    topic_id            int8,
    primary key (id)
);

create table Event
(
    id                  int8 generated by default as identity,
    location            varchar(255),
    maxPersonAllowed    int4 not null,
    planedEndDateTime   timestamp,
    planedStartDateTime timestamp,
    type                varchar(255),
    primary key (id)
);

create table Person
(
    id        int8 generated by default as identity,
    comment   varchar(4000),
    firstname varchar(255),
    lastname  varchar(255),
    role      int4,
    telephone varchar(255),
    event_id  int8,
    primary key (id)
);

create table Topic
(
    id               int8 generated by default as identity,
    comment          varchar(4000),
    name             varchar(255),
    event_id         int8,
    previousTopic_id int8,
    primary key (id)
);

alter table if exists Activity
    add constraint FK3lxim153ss7jq1leyw97fn32i
        foreign key (previousActivity_id)
            references Activity;

alter table if exists Activity
    add constraint FKcgslh3per1me24a9iov7ia53r
        foreign key (topic_id)
            references Topic;

alter table if exists Person
    add constraint FKhx6sbu85x6maywgxut2sxqb7m
        foreign key (event_id)
            references Event;

alter table if exists Topic
    add constraint FK7ako2w4aegodj0s8yawgswt5v
        foreign key (event_id)
            references Event;

alter table if exists Topic
    add constraint FK8kprmabtprwoof9nraefeol3c
        foreign key (previousTopic_id)
            references Topic;

insert into event (maxpersonallowed, location, planedenddatetime, planedstartdatetime, type)
values (100, ' Norditalien', '2023-05-15 00:00', '2022-05-19 09:30', 'Kulturwoche');
insert into person (comment, firstname, lastname, role, telephone, event_id)
values ('No comment', 'Oliver', 'Sugic', 1, '0123456789', 1);
insert into person (comment, firstname, lastname, role, telephone, event_id)
values ('No comment', 'Test ', 'Person', 1, '0123456789', 1);
insert into person (comment, firstname, lastname, role, telephone, event_id)
values ('No comment', 'Person', 'Test', 1, '0123456789', 1);
insert into person (comment, firstname, lastname, role, telephone, event_id)
values ('No comment', 'Firstname', 'Lastname', 2, '0123456789', 1);
insert into topic (comment, name, event_id)
values ('Mailand ist bekannt als die Modemetropole schlechthin, doch die italienische Stadt weiß mit noch viel mehr zu beeindrucken. Hier befinden sich historische Sehenswürdigkeiten, die nicht nur wunderschön zu betrachten sind, sondern auch viel zu erzählen haben.',
        'Mailand', 1);

insert into activity(activityname, comment, ispublic, latitude, longitude, publicationdate, startdatetime, topic_id)
values ('Mailänder Dom',
        'Die Liste der wichtigsten Sehenswürdigkeiten in Mailand führt der Mailänder Dom an. Architektonisches Schmuckstück, Wahrzeichen der Stadt und Herzstück von Mailand. Egal, wer nach Mailand kommt, der erste Stopp ist in der Regel der Mailänder Dom mit dem großen Domplatz und den Domterrassen, die einen wunderschönen Ausblick auf die Stadt garantieren.',
        false, 45.464098, 9.191926, current_date, '2023-05-11 13:00', 1);
insert into activity(activityname, comment, ispublic, latitude, longitude, publicationdate, startdatetime,
                     previousactivity_id, topic_id)
values ('Königlicher Palast ',
        'Kunstinteressierte sollten in Mailand ein ganz bestimmtes Ziel ansteuern: den Königlichen Palast. Das Stadtschloss war ehemaliger Regierungssitz in Mailand und damit politisches Zentrum der Stadt. Auch Napoleon Bonaparte war im Palazzo Reale zuhause. Im zweiten Weltkrieg kam es durch einen Bombenanschlag zu schweren Brandschäden an dem imposanten Bauwerk. Heute ist der Königliche Palast das wichtigste Kulturgut der Stadt. Auf einer Fläche von 7.000 Quadratmetern gibt es das ganze Jahr über Ausstellungen mit verschiedenen Meisterwerken verschiedener Künstler. Das Kulturzentrum arbeitet mit Museen auf der ganzen Welt zusammen und kann dadurch Kunstwerke aus verschiedenen Museen auf der eigenen Ausstellungsfläche präsentieren.',
        false, 45.4632, 9.1911, current_date, '2023-05-11 16:00', 1, 1);

insert into topic (comment, name, event_id, previoustopic_id)
values ('Turin gehört zu den unterschätzten Städten Italiens und ist gerade deshalb noch ein Geheimtipp. Die Hauptstadt des Piemont hat so viele schöne Sehenswürdigkeiten und eine wunderbare Umgebung, dass es sich lohnt, ein paar Tage in Turin zu verbringen und sich von der angenehmen Atmosphäre und den Köstlichkeiten verwöhnen zu lassen. ',
        'Turin', 1, 1);

insert into activity(activityname, comment, ispublic, latitude, longitude, publicationdate, startdatetime, topic_id)
values ( 'Venaria Reale '
       , 'Auch die Venaria Reale war ein Königspalast des Hauses Savoyen und gehört heute zum UNESCO-Weltkulturerbe. Es ist eines der größten Königsschlösser und hat riesige Gartenanlagen. Wir empfehlen, einen ganzen Tag für diese außerordentliche Sehenswürdigkeit mit dem gesamten Dorf ringsherum einzuplanen.'
       , false, 45.464098, 9.191926, current_date, '2023-05-12 11:00', 2);

insert into activity(activityname, comment, ispublic, latitude, longitude, publicationdate, startdatetime, topic_id)
values ( 'Palazzo Reale'
       , 'Der Königspalast Palazzo Reale war die Residenz der Königsfamilie Savoyen im Piemont. Er befindet in der attraktiven Piazza Castello und ist durch ein schmiedeeisernes Gitter vom Platz getrennt. Der Palazzo Reale wird heute für Ausstellungen genutzt. Dahinter befinden sich die königlichen Gärten.'
       , false, 40.8362, 14.2496, current_date, '2023-05-12 14:00', 2);

insert into topic (comment, name, event_id, previoustopic_id)
values ('Turin gehört zu den unterschätzten Städten Italiens und ist gerade deshalb noch ein Geheimtipp. Die Hauptstadt des Piemont hat so viele schöne Sehenswürdigkeiten und eine wunderbare Umgebung, dass es sich lohnt, ein paar Tage in Turin zu verbringen und sich von der angenehmen Atmosphäre und den Köstlichkeiten verwöhnen zu lassen. ',
        'Turin', 1, 2);

insert into activity(activityname, comment, ispublic, latitude, longitude, publicationdate, startdatetime, topic_id)
values ( 'Venaria Reale '
       , 'Auch die Venaria Reale war ein Königspalast des Hauses Savoyen und gehört heute zum UNESCO-Weltkulturerbe. Es ist eines der größten Königsschlösser und hat riesige Gartenanlagen. Wir empfehlen, einen ganzen Tag für diese außerordentliche Sehenswürdigkeit mit dem gesamten Dorf ringsherum einzuplanen.'
       , false, 45.464098, 9.191926, current_date, '2023-05-12 11:00', 3);

insert into activity(activityname, comment, ispublic, latitude, longitude, publicationdate, startdatetime, topic_id)
values ( 'Palazzo Reale'
       , 'Der Königspalast Palazzo Reale war die Residenz der Königsfamilie Savoyen im Piemont. Er befindet in der attraktiven Piazza Castello und ist durch ein schmiedeeisernes Gitter vom Platz getrennt. Der Palazzo Reale wird heute für Ausstellungen genutzt. Dahinter befinden sich die königlichen Gärten.'
       , false, 40.8362, 14.2496, current_date, '2023-05-12 14:00', 3);

insert into topic (comment, name, event_id, previoustopic_id)
values ('Die einzigartige Lagunenstadt an der italienischen Adria zählt zu den weltweit bedeutendsten Zielen für Städtereisen. Neben prächtigen Kirchen und einzigartigen Museen und Palästen sind es vor allem die Kanäle mit ihren Gondeln, die der Stadt diesen unvergesslichen Charme geben. Venedigs Architektur ist einzigartig und der richtige Ort um sich einfach treiben zu lassen. Die Stadt ist ein riesiges Freilichtmuseum mit unzähligen Kunst und Kulturschätzen und bietet darüber hinaus auch noch eine Menge kulinarischer Köstlichkeiten.',
        'Venedig', 1, 3);

insert into activity(activityname, comment, ispublic, latitude, longitude, publicationdate, startdatetime, topic_id)
values ( 'Canal Grande '
       , 'Fast 4 km schlängelt sich der Canal Grande durch die Stadt. Die Breite des Kanals schwankt zwischen 30 und 70 Metern, die Tiefe beträgt bis zu 5 Meter. Die Hauptwasserstraße der Lagune trennt die Stadtsechstel (Sestieri) San Marco, Cannaregio und Castello (auf der linken Seite) von den Stadtteilen Dorsoduro, San Polo & Santa Croce, die auf der rechten Kanalseite liegen.'
       , false, 45.4311, 12.3281, current_date, '2023-05-13 11:00', 3);

insert into activity(activityname, comment, ispublic, latitude, longitude, publicationdate, startdatetime, topic_id)
values ( 'Scuola Grande di San Rocco'
       , 'Die Scuola Grande di San Marco gilt als die bekannteste Bruderschaft des früheren Venedigs. Das ehemalige Haus der Bruderschaft mit seiner wunderschönen Fassade ist eine besondere Sehenswürdigkeit und befindet sich am Campo San Rocco im Sestiere San Polo. Das ursprüngliche Gebäude fiel 1445 leider einem Feuer zum Opfer, im Jahr 1488 wurde mit dem Bau des neuen Gebäudes begonnen.Die Scuola Grande di San Marco ist für seine berühmten Deckengemälde mit Szenen aus dem Alten Testament und den sehr großen Bildern an den Wänden bekannt. Hier können auch die 56 Gemälde von Jacopo Tintoretto besichtigt werden.'
       , false, 45.4366, 12.325, current_date, '2023-05-13 14:00', 3);


insert into topic (comment, name, event_id, previoustopic_id)
values (' Zwischen dem Meer und dem Apenninengebirge gelegen, zieht sich die Stadt auf einem schmalen Küstendstreifen über 30 km an der italienischen Riviera entlang. Es ist eng in der Stadt, weiträumige Plätze sind selten im Zentrum, verwinkelte Gässchen gibt es dagegen überall. Die Wohnhäuser sind in der Regel 7-8 Stockwerke hoch und selbst die Prachtstraße Genuas, die Via Garibaldi ist gerade mal 7,5 m breit und die da zu bewundernden herrlichen Renaissancepaläste leiden etwas unter dieser Enge.',
        'Genua', 1, 3);

insert into activity(activityname, comment, ispublic, latitude, longitude, publicationdate, startdatetime, topic_id)
values ( 'Das Aquarium von Genua '
       , 'Das Aquarium Genua (Italienisch: Acquario di Genova) ist das zweitgrößte Aquarium Europas, hinter dem L’Oceanogràfic in Valencia, Spanien. Es befindet sich auf dem Gelände des Porto Antico der italienischen Hafenstadt Genua. Das Aquarium wurde 1992 anlässlich der Feierlichkeiten zur 500-jährigen Entdeckung Amerikas eröffnet und in Folge mehrfach erweitert. Planung und architektonische Ausführung lagen bei den Architekten Renzo Piano und Peter Chermayeff. Zum Zeitpunkt seiner Eröffnung war es das zweitgrößte Aquarium der Welt. Es zeigt 70 Lebensräume und zirka 12.000 Exemplare von 600 Arten der Weltmeere.'
       , false, 44.4102889, 8.9265472, current_date, '2023-05-14 11:00', 3);

insert into activity(activityname, comment, ispublic, latitude, longitude, publicationdate, startdatetime, topic_id)
values ( 'Porto Antico'
       , 'Der Porto Antico ist ein Touristenhafen in Genua. Als ehemaliger Industriehafen war er lange Zeit von der angrenzenden Altstadt abgetrennt und wurde erst 1992, zur Expo anlässlich des Kolumbusjahres, von dem genuesischen Architekten Renzo Piano umstrukturiert und kulturell aufgewertet. Heute ist der Porto Antico einer der größten Anziehungspunkte für Touristen, aber auch für die Anwohner von Genua. '
       , false, 44.40881, 8.926, current_date, '2023-05-14 14:00', 3);


