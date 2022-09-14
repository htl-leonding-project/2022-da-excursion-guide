package boundary;

import entity.Topic;
import org.jboss.resteasy.reactive.RestPath;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/topic")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicResource {

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
}
