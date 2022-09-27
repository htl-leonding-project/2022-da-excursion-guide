package boundary;


import control.EventRepository;
import entity.Event;
import entity.Person;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {
    @Inject
    EntityManager em;

    @Inject
    EventRepository eventRepository;

    @GET
    @Path("getAll")
    public Response list() {
        return Response.ok(Event.listAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getById(long id) {
        return Response.ok(Event.findById(id)).build();
    }

    @POST
    @Transactional
    @Path("addEvent")
    public Response add(Event event) {
        event.persistAndFlush();
        return Response.ok(event).build();
    }

    @DELETE
    @Transactional
    @Path("deleteEvent/{id}")
    public void delete(@RestPath long id) {
        Event.findById(id).delete();
    }

    @PATCH
    @Transactional
    @Path("editEvent/{id}")
    public Response edit(@RestPath long id, Event event) {
        Event tmp = Event.findById(id);
        tmp.setName(event.getName());
        tmp.setMaxPersonAllowed(event.getMaxPersonAllowed());
        tmp.setType(event.getType());
        tmp.setPlanedEndDateTime(event.getPlanedEndDateTime());
        tmp.setPlanedStartDateTime(event.getPlanedStartDateTime());
        tmp.setParticipant(event.getParticipant());
        tmp.setTopics(event.getTopics());
        return Response.ok(event).build();
    }
/*
    @POST
    @Transactional
    @Path("addPerson/{eventname}")
    public Response addPersonToEvent(Person person, @RestPath String eventname) {
        Event event = eventRepository.getEvent(eventname);
        List<Person> personList = event.getParticipant();
        personList.add(person);
        event.setParticipant(personList);
        em.merge(event);
        return Response.ok(event).build();
    }

 */
}
