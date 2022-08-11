package boundary;


import entity.Event;
import org.jboss.resteasy.reactive.RestPath;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {
    @GET
    @Path("getAll")
    public Response list(){
        return Response.ok(Event.listAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getById(long id){
        return Response.ok(Event.findById(id)).build();
    }

    @POST
    @Transactional
    @Path("addEvent")
    public Response add(Event event){
        event.persistAndFlush();
        return Response.ok(event).build();
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
    public Response edit(@RestPath long id, Event event){
        Event tmp = Event.findById(id);
        tmp.setEventType(event.getEventType());
        tmp.setName(event.getName());
        tmp.setPlanedEndDateTime(event.getPlanedEndDateTime());
        tmp.setPlanedStartDateTime(event.getPlanedStartDateTime());
        return Response.ok(event).build();
    }


}
