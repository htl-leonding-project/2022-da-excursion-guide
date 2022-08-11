package boundary;

import entity.EventType;
import org.jboss.resteasy.reactive.RestPath;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/eventtype")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventTypeResource {

    @GET
    @Path("getAll")
    public Response list(){
        return Response.ok(EventType.listAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getById(long id){
        return Response.ok(EventType.findById(id)).build();
    }

    @POST
    @Transactional
    @Path("addEventType")
    public Response add(EventType eventType){
        eventType.persistAndFlush();
        return Response.ok(eventType).build();
    }

    @DELETE
    @Transactional
    @Path("deleteEventType/{id}")
    public void delete(@RestPath long id){
        EventType.findById(id).delete();
    }

    @PATCH
    @Transactional
    @Path("editEventType/{id}")
    public Response edit(@RestPath long id, EventType eventType){
        EventType tmp = EventType.findById(id);
        tmp.setType(eventType.getType());
        tmp.setMaxPersonAllowed(eventType.getMaxPersonAllowed());
        return Response.ok(eventType).build();
    }

}
