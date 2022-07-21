package control;


import entity.Event;
import entity.EventType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventTypeRepository implements PanacheRepository<EventType> {
}
