package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topic extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Activity> activity;

    @OneToOne(cascade = CascadeType.ALL)
    private Topic previousTopic;
    private String comment;

    public Topic() {
    }

    public Topic(String name, List<Activity> activity, Topic previousTopic, String comment) {
        this.name = name;
        this.activity = activity;
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

    public List<Activity> getActivity() {
        return activity;
    }

    public void setActivity(List<Activity> activity) {
        this.activity = activity;
    }
}
