package entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class EntityTest {

    LocalDateTime timeNow = LocalDateTime.now();
    LocalDate dateNow = LocalDate.now();
    @Test
    public void entityActivity(){
        Activity activity = new Activity();
        activity.setPublic(false);
        activity.setStartDateTime(timeNow);
        activity.setPublicationDate(dateNow);
        activity.setActivityName("test");
        activity.setComment("test comment");
        activity.setLatitude(0.001);
        activity.setLongitude(0.001);

        assertThat(activity).isNotNull();
        assertThat(activity.isPublic()).isEqualTo(false);
        assertThat(activity.getStartDateTime()).isEqualTo(timeNow);
        assertThat(activity.getPublicationDate()).isEqualTo(dateNow);
        assertThat(activity.getActivityName()).isEqualTo("test");
        assertThat(activity.getComment()).isEqualTo("test comment");
        assertThat(activity.getLatitude()).isEqualTo(0.001);
        assertThat(activity.getLongitude()).isEqualTo(0.001);
    }


    @Test
    public void entityTopic(){
        Topic topic = new Topic();
        topic.setName("test");
        topic.setComment("test comment");

        assertThat(topic).isNotNull();
        assertThat(topic.getName()).isEqualTo("test");
        assertThat(topic.getComment()).isEqualTo("test comment");
    }

    @Test
    public void entityPerson(){
        Person person = new Person();
        person.setFirstname("firstname");
        person.setLastname("lastname");
        person.setRole(Role.MEMBER);
        person.setTelephone("0123456789");
        person.setComment("Person");

        assertThat(person).isNotNull();
        assertThat(person.getFirstname()).isEqualTo("firstname");
        assertThat(person.getLastname()).isEqualTo("lastname");
        assertThat(person.getRole()).isEqualTo(Role.MEMBER);
        assertThat(person.getTelephone()).isEqualTo("0123456789");
        assertThat(person.getComment()).isEqualTo("Person");
    }

    @Test
    public void entityEvent(){
        Event event = new Event();
        event.setName("test");
        event.setPlanedStartDateTime(timeNow);
        event.setPlanedEndDateTime(timeNow.plusSeconds(1));

        assertThat(event).isNotNull();
        assertThat(event.getName()).isEqualTo("test");
        assertThat(event.getPlanedStartDateTime()).isEqualTo(timeNow);
        assertThat(event.getPlanedEndDateTime()).isEqualTo(timeNow.plusSeconds(1));
    }

}