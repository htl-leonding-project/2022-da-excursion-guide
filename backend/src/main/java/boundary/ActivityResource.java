package boundary;


import entity.Activity;
import org.jboss.resteasy.reactive.RestPath;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

@Path("/api/activity ")
public class ActivityResource {
    @GET
    @Path("getAll")
    public List<Activity> list(){
        return Activity.listAll();
    }

    @GET
    @Path("{id}")
    public Activity getById(long id){
        return Activity.findById(id);
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
    public void delete(@RestPath long id){
        Activity.findById(id).delete();
    }

    @PATCH
    @Transactional
    @Path("editActivity/{id}")
    public Activity edit(@RestPath long id, Activity activity){
        Activity tmp = Activity.findById(id);
        tmp.setActivityName(activity.getActivityName());
        tmp.setComment(activity.getComment());
        tmp.setLatitude(activity.getLatitude());
        tmp.setLongitude(activity.getLongitude());
        tmp.setBelongsTo(activity.getBelongsTo());
        tmp.setPreviousActivity(activity.getPreviousActivity());
        tmp.setPublic(activity.isPublic());
        tmp.setPublicationDate(activity.getPublicationDate());
        tmp.setStartDateTime(activity.getStartDateTime());
        return activity;
    }

}
