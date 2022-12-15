package at.htl.leotour_backend.boundary;


import at.htl.leotour_backend.Dto.ActivityDto;
import at.htl.leotour_backend.entity.Activity;

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

    @PUT
    @Transactional
    @Path("editActivity/{id}")
    public Activity edit(@PathParam("id") long id, ActivityDto activity){
        Activity tmp = Activity.findById(id);
        tmp.setActivityName(activity.activityName());
        tmp.setComment(activity.comment());
        tmp.setLatitude(activity.latitude());
        tmp.setLongitude(activity.longitude());
        tmp.setPreviousActivity(Activity.findById(activity.previousActivityId()));
        tmp.setPublic(activity.isPublic());
        tmp.setPublicationDate(activity.publicationDate());
        tmp.setStartDateTime(activity.startDateTime());

        Activity acc = new Activity();
        acc.setId(activity.id());
        acc.setComment(activity.comment());
        acc.setPreviousActivity(Activity.findById(activity.previousActivityId()));
        acc.setLatitude(activity.latitude());
        acc.setLongitude(activity.longitude());
        acc.setActivityName(activity.activityName());
        acc.setPublic(activity.isPublic());
        acc.setPublicationDate(activity.publicationDate());
        acc.setStartDateTime(activity.startDateTime());
        return acc;
    }



}
