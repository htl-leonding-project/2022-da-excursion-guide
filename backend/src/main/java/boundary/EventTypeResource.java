package boundary;

import entity.EventType;
import org.jboss.resteasy.reactive.RestPath;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/eventtype")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
    public Response add(EventType eventType){
        eventType.persistAndFlush();
        return Response.ok(eventType).build();
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
    public Response edit(@RestPath long id, EventType eventType){
        EventType tmp = EventType.findById(id);
        tmp.setType(eventType.getType());
        tmp.setMaxPersonAllowed(eventType.getMaxPersonAllowed());
        return Response.ok(eventType).build();
    }

}
