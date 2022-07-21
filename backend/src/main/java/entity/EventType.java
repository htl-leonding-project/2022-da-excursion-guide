package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int maxPersonAllowed;
    private String type;

    public EventType() {
    }

    public EventType(int maxPersonAllowed, String type) {
        this.maxPersonAllowed = maxPersonAllowed;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
