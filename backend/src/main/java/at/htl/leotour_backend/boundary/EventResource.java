package at.htl.leotour_backend.boundary;


import at.htl.leotour_backend.entity.Activity;
import at.htl.leotour_backend.entity.Event;
import at.htl.leotour_backend.entity.Topic;
import at.htl.leotour_backend.control.EventRepository;
import at.htl.leotour_backend.entity.Person;

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
    public Response getById(@PathParam("id") long id) {
        return Response.ok(Event.findById(id)).build();
    }

    @POST
    @Transactional
    @Path("addEvent")
    public Response add(Event event) {
        Event newEvent = new Event();
        newEvent.setLocation(event.getLocation());
        newEvent.setMaxPersonAllowed(event.getMaxPersonAllowed());
        newEvent.setType(event.getType());
        newEvent.setPlanedEndDateTime(event.getPlanedEndDateTime());
        newEvent.setPlanedStartDateTime(event.getPlanedStartDateTime());

        for (Person person : event.getParticipant()) {
            person.setEvent(newEvent);
            person.persist();
        }

        for (Topic topic : event.getTopics()) {
            topic.setEvent(newEvent);
            topic.persist();
            for (Activity activity : topic.getActivity()
            ) {
                {
                    activity.setTopic(topic);
                    activity.persist();
                }
            }
        }
        newEvent.persist();
        return Response.ok(newEvent).build();
    }

    @DELETE
    @Transactional
    @Path("deleteEvent/{id}")
    public void delete(@PathParam("id") long id) {
        Event.findById(id).delete();
    }

    @PATCH
    @Transactional
    @Path("editEvent/{id}")
    public Response edit(@PathParam("id") long id, Event event) {
        Event tmp = Event.findById(id);
        tmp.setLocation(event.getLocation());
        tmp.setMaxPersonAllowed(event.getMaxPersonAllowed());
        tmp.setType(event.getType());
        tmp.setPlanedEndDateTime(event.getPlanedEndDateTime());
        tmp.setPlanedStartDateTime(event.getPlanedStartDateTime());
        tmp.setParticipant(event.getParticipant());
        tmp.setTopics(event.getTopics());
        return Response.ok(event).build();
    }

    @POST
    @Transactional
    @Path("addPerson/{eventname}")
    public Response addPersonToEvent(Person person, @PathParam("eventname") String eventname) {
        Event event = eventRepository.getEvent(eventname);
        List<Person> personList = event.getParticipant();
        personList.add(person);
        event.setParticipant(personList);
        Event.deleteById(event.getId());
        Event.persist(event);
        person.setEvent(event);
        return Response.ok(event).build();
    }

    @POST
    @Transactional
    @Path("addTopic/{eventname}")
    public Response addTopicToEvent(Topic topic, @PathParam("eventname") String eventname) {
        Event event = eventRepository.getEvent(eventname);
        List<Topic> topics = event.getTopics();
        topics.add(topic);
        event.setTopics(topics);
        Event.deleteById(event.getId());
        Event.persist(event);
        topic.setEvent(event);
        return Response.ok(event).build();
    }
}
