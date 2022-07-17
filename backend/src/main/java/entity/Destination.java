package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Destination extends PanacheEntity {
    public String destinationName;
    public float xCord;
    public float yCord;
    public boolean nearByShops;
    public int estimatedTourTimeAtDestination;
    public boolean makingPause;
    public LocalDateTime arrivalDate;
    public LocalDateTime departureDate;
}
