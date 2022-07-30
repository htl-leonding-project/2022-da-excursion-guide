package boundary;


import entity.Event;
import org.jboss.resteasy.reactive.RestPath;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

@Path("/api/event")
public class EventResource {
    @GET
    @Path("getAll")
    public List<Event> list(){
        return Event.listAll();
    }

    @GET
    @Path("{id}")
    public Event getById(long id){
        return Event.findById(id);
    }

    @POST
    @Transactional
    @Path("addEvent")
    public Event add(Event event){
        event.persistAndFlush();
        return event;
    }

    @DELETE
    @Transactional
    @Path("deleteEvent/{id}")
    public void delete(@RestPath long id){
        Event.findById(id).delete();
    }

    @PATCH
    @Transactional
    @Path("editEvent/{id}")
    public Event edit(@RestPath long id, Event event){
        Event tmp = Event.findById(id);
        tmp.setEventType(event.getEventType());
        tmp.setName(event.getName());
        tmp.setPlanedEndDateTime(event.getPlanedEndDateTime());
        tmp.setPlanedStartDateTime(event.getPlanedStartDateTime());
        return event;
    }


}
