package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Topic extends PanacheEntityBase {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Event event;
    @OneToOne
    private Topic previousTopic;
    private String comment;

    public Topic() {
    }

    public Topic(String name, Event event, Topic previousTopic, String comment) {
        this.name = name;
        this.event = event;
        this.previousTopic = previousTopic;
        this.comment = comment;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Topic getPreviousTopic() {
        return previousTopic;
    }

    public void setPreviousTopic(Topic previousTopic) {
        this.previousTopic = previousTopic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
