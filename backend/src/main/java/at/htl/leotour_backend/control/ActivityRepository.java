package at.htl.leotour_backend.control;

import at.htl.leotour_backend.entity.Activity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActivityRepository implements PanacheRepository<Activity> {
}
