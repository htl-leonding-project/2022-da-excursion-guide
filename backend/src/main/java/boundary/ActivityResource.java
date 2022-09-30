package boundary;


import entity.Activity;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/activity")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActivityResource {
    @GET
    @Path("getAll")
    public Response list(){
        return Response.ok(Activity.listAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id){
        return Response.ok(Activity.findById(id)).build();
    }

    @POST
    @Transactional
    @Path("addActivity")
    public Activity add(Activity activity){
        activity.persistAndFlush();
        return activity;
    }

    @DELETE
    @Transactional
    @Path("deleteActivity/{id}")
    public void delete(@PathParam("id") long id){
        Activity.findById(id).delete();
    }

    @PATCH
    @Transactional
    @Path("editActivity/{id}")
    public Activity edit(@PathParam("id") long id, Activity activity){
        Activity tmp = Activity.findById(id);
        tmp.setActivityName(activity.getActivityName());
        tmp.setComment(activity.getComment());
        tmp.setLatitude(activity.getLatitude());
        tmp.setLongitude(activity.getLongitude());
        tmp.setPreviousActivity(activity.getPreviousActivity());
        tmp.setPublic(activity.isPublic());
        tmp.setPublicationDate(activity.getPublicationDate());
        tmp.setStartDateTime(activity.getStartDateTime());
        return activity;
    }

}
