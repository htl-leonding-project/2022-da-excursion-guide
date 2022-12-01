package at.htl.leotour_backend.control;

import at.htl.leotour_backend.entity.Activity;
import at.htl.leotour_backend.entity.Topic;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TopicRepository implements PanacheRepository<Topic> {

    @Inject
    EntityManager em;

    public Topic getTopic(String topicname){
        return em
                .createQuery("SELECT t from Topic t where t.name LIKE :NAME", Topic.class)
                .setParameter("NAME", topicname)
                .getSingleResult();
    }

    public List<Activity> getActivities(Topic topic){
        return em
                .createQuery("SELECT a from Activity a where a.topic.id = :TOPICID", Activity.class)
                .setParameter("TOPICID", topic.getId())
                .getResultList();
    }
}
