package entity;

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
    @OneToOne
    private Activity previousActivity;
    @ManyToOne
    private Topic belongsTo;
    private String comment;

    private boolean isPublic;

    private LocalDate publicationDate;

    public Activity() {
    }

    public Activity(String activityName, LocalDateTime startDateTime, double longitude, double latitude, Activity previousActivity, Topic belongsTo, String comment, boolean isPublic, LocalDate publicationDate) {
        this.activityName = activityName;
        this.startDateTime = startDateTime;
        this.longitude = longitude;
        this.latitude = latitude;
        this.previousActivity = previousActivity;
        this.belongsTo = belongsTo;
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

    public Topic getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(Topic belongsTo) {
        this.belongsTo = belongsTo;
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
        isPublic = aPublic;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
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
                ", belongsTo=" + belongsTo +
                ", comment='" + comment + '\'' +
                ", isPublic=" + isPublic +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
