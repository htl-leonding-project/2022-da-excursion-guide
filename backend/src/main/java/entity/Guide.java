package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Guide extends PanacheEntity {
    public String firstname;
    public String lastname;
    public float xCord;
    public float yCord;
}
