package entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Event extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String location;
    private int maxPersonAllowed;
    private String type;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Person> participant;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Topic> topics;

    private LocalDateTime planedStartDateTime;
    private LocalDateTime planedEndDateTime;

    public Event() {
    }

    public Event(String location, int maxPersonAllowed, String type, List<Person> participant, List<Topic> topics, LocalDateTime planedStartDateTime, LocalDateTime planedEndDateTime) {
        this.location = location;
        this.maxPersonAllowed = maxPersonAllowed;
        this.type = type;
        this.participant = participant;
        this.topics = topics;
        this.planedStartDateTime = planedStartDateTime;
        this.planedEndDateTime = planedEndDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String name) {
        this.location = name;
    }

    public LocalDateTime getPlanedStartDateTime() {
        return planedStartDateTime;
    }

    public void setPlanedStartDateTime(LocalDateTime planedStartDateTime) {
        this.planedStartDateTime = planedStartDateTime;
    }

    public LocalDateTime getPlanedEndDateTime() {
        return planedEndDateTime;
    }

    public void setPlanedEndDateTime(LocalDateTime planedEndDateTime) {
        this.planedEndDateTime = planedEndDateTime;
    }

    public List<Person> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Person> participant) {
        this.participant = participant;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public int getMaxPersonAllowed() {
        return maxPersonAllowed;
    }

    public void setMaxPersonAllowed(int maxPersonAllowed) {
        this.maxPersonAllowed = maxPersonAllowed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
