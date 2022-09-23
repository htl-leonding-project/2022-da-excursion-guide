package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Event extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int maxPersonAllowed;
    private String type;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Person> participant;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<Topic> topics;

    private LocalDateTime planedStartDateTime;
    private LocalDateTime planedEndDateTime;


    public Event() {
    }

    public Event(String name, int maxPersonAllowed, String type, List<Person> participant, List<Topic> topics, LocalDateTime planedStartDateTime, LocalDateTime planedEndDateTime) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
