package boundary;

import entity.Activity;
import entity.Event;
import entity.Person;
import entity.Topic;
import org.jboss.resteasy.reactive.RestPath;

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
    EntityManager em;

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok(Topic.listAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getById(long id) {
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
    public void delete(@RestPath long id) {
        Topic.findById(id).delete();
    }

    @PATCH
    @Transactional
    @Path("editTopic/{id}")
    public Response edit(@RestPath long id, Topic topic) {
        Topic tmp = Topic.findById(id);
        tmp.setComment(topic.getComment());
        tmp.setName(topic.getName());
        tmp.setPreviousTopic(topic.getPreviousTopic());
        tmp.setActivity(topic.getActivity());
        tmp.persistAndFlush();
        return Response.ok(topic).build();
    }

    @POST
    @Transactional
    @Path("addAktivity/{topicname}")
    public Response addPersonToEvent(Activity activity, @RestPath String topicname) {
        Topic topic = em
                .createQuery("SELECT t from Topic t where t.name LIKE :NAME", Topic.class)
                .setParameter("NAME", topicname)
                .getSingleResult();
        List<Activity> activities = topic.getActivity();
        activities.add(activity);
        topic.setActivity(activities);
        topic.persistAndFlush();
        return Response.ok(topic).build();
    }

}
