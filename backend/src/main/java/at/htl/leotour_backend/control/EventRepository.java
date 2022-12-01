package at.htl.leotour_backend.control;


import at.htl.leotour_backend.entity.Event;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class EventRepository implements PanacheRepository<Event> {
    @Inject
    EntityManager em;
    public Event getEvent(String eventname){
        return em
                .createQuery("SELECT e from Event e where e.location LIKE :NAME",Event.class)
                .setParameter("NAME",eventname)
                .getSingleResult();
    }

}
