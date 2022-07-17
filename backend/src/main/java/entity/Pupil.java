package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;

@Entity
public class Pupil extends PanacheEntity {

    public String firstname;
    public String lastname;
    public float xCord;
    public float yCord;
}
