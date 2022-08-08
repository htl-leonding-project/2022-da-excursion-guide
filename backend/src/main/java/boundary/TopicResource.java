package boundary;

import entity.Topic;
import org.jboss.resteasy.reactive.RestPath;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/topic")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicResource {

    @GET
    @Path("getAll")
    public List<Topic> list() {
        return Topic.listAll();
    }

    @GET
    @Path("{id}")
    public Topic getById(long id) {
        return Topic.findById(id);
    }

    @POST
    @Transactional
    @Path("addTopic")
    public Topic add(Topic topic) {
        topic.persistAndFlush();
        return topic;
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
    public Topic edit(@RestPath long id, Topic topic) {
        Topic tmp = Topic.findById(id);
        tmp.setComment(topic.getComment());
        tmp.setEvent(topic.getEvent());
        tmp.setName(topic.getName());
        tmp.setPreviousTopic(topic.getPreviousTopic());
        tmp.persistAndFlush();
        return topic;
    }
}
