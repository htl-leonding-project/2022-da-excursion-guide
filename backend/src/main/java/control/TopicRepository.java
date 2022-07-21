package control;

import entity.Topic;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TopicRepository implements PanacheRepository<Topic> {

}
