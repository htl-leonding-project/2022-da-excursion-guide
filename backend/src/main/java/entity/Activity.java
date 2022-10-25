package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Activity extends PanacheEntityBase {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String activityName;
    private LocalDateTime startDateTime;
    private double longitude;
    private double latitude;
    @OneToOne(cascade = CascadeType.ALL)
    private Activity previousActivity;

    private String comment;

    private boolean isPublic;

    private LocalDate publicationDate;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference
    private Topic topic;

    public Activity() {
    }

    public Activity(String activityName, LocalDateTime startDateTime, double longitude, double latitude, Activity previousActivity, String comment, boolean isPublic, LocalDate publicationDate) {
        this.activityName = activityName;
        this.startDateTime = startDateTime;
        this.longitude = longitude;
        this.latitude = latitude;
        this.previousActivity = previousActivity;
        this.comment = comment;
        this.isPublic = isPublic;
        this.publicationDate = publicationDate;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Activity getPreviousActivity() {
        return previousActivity;
    }

    public void setPreviousActivity(Activity previousActivity) {
        this.previousActivity = previousActivity;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        this.isPublic = aPublic;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityName='" + activityName + '\'' +
                ", startDateTime=" + startDateTime +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", previousActivity=" + previousActivity +
                ", comment='" + comment + '\'' +
                ", isPublic=" + isPublic +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
