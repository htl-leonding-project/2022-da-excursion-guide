package control;

import entity.Topic;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
}
