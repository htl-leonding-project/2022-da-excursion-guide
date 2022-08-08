package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Event extends PanacheEntityBase {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private EventType eventType;
    private LocalDateTime planedStartDateTime;
    private LocalDateTime planedEndDateTime;


    public Event() {
    }

    public Event(String name, EventType eventType, LocalDateTime planedStartDateTime, LocalDateTime planedEndDateTime) {
        this.name = name;
        this.eventType = eventType;
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

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
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
}
