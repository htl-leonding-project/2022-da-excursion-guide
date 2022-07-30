package boundary;

import entity.EventType;
import org.jboss.resteasy.reactive.RestPath;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

@Path("/api/eventtype")
public class EventTypeResource {

    @GET
    @Path("getAll")
    public List<EventType> list(){
        return EventType.listAll();
    }

    @GET
    @Path("{id}")
    public EventType getById(long id){
        return EventType.findById(id);
    }

    @POST
    @Transactional
    @Path("addEvent")
    public EventType add(EventType eventType){
        eventType.persistAndFlush();
        return eventType;
    }

    @DELETE
    @Transactional
    @Path("deleteEvent/{id}")
    public void delete(@RestPath long id){
        EventType.findById(id).delete();
    }

    @PATCH
    @Transactional
    @Path("editEvent/{id}")
    public EventType edit(@RestPath long id, EventType eventType){
        EventType tmp = EventType.findById(id);
        tmp.setType(eventType.getType());
        tmp.setMaxPersonAllowed(eventType.getMaxPersonAllowed());
        return eventType;
    }

}
