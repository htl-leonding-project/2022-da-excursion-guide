package at.htl.leotour_backend.boundary;

import at.htl.leotour_backend.entity.Topic;
import at.htl.leotour_backend.control.TopicRepository;
import at.htl.leotour_backend.entity.Activity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/topic")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicResource {
    @Inject
    TopicRepository topicRepository;

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok(Topic.listAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id) {
        return Response.ok(Topic.findById(id)).build();
    }

    @POST
    @Transactional
    @Path("addTopic")
    public Response add(Topic topic) {
        topic.persistAndFlush();
        return Response.ok(topic).build();
    }

    @DELETE
    @Transactional
    @Path("deleteTopic/{id}")
    public void delete(@PathParam("id") long id) {
        Topic.findById(id).delete();
    }

    @PATCH
    @Transactional
    @Path("editTopic/{id}")
    public Response edit(@PathParam("id") long id, Topic topic) {
        Topic tmp = Topic.findById(id);
        tmp.setComment(topic.getComment());
        tmp.setName(topic.getName());
        tmp.setPreviousTopic(topic.getPreviousTopic());
        tmp.persistAndFlush();
        return Response.ok(topic).build();
    }

    @POST
    @Transactional
    @Path("addActivity/{topicname}")
    public Response addPersonToEvent(Activity activity, @PathParam("topicname") String topicname) {
        Topic topic = topicRepository.getTopic(topicname);
        List<Activity> activities = topicRepository.getActivities(topic);
        activities.add(activity);
        Topic.deleteById(topic.getId());
        Topic.persist(topic);
        activity.setTopic(topic);
        return Response.ok(topic).build();
    }
}
