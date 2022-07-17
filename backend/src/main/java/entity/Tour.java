package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Tour extends PanacheEntity {
    public int neededTime;

    public LocalDate startDate;
    public LocalDate endDate;

    @OneToMany
    public List<Pupil> pupils;

    @OneToMany
    public List<Guide> guides;

    @OneToMany
    public List<Destination> destinations;
}
