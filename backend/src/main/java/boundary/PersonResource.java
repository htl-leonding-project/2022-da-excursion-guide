package boundary;

import entity.Person;
import org.jboss.resteasy.reactive.RestPath;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

@Path("/api/person")
public class PersonResource {

    @GET
    @Path("getAll")
    public List<Person> list(){
        return Person.listAll();
    }

    @GET
    @Path("{id}")
    public Person getById(long id){
        return Person.findById(id);
    }

    @POST
    @Transactional
    @Path("addPerson")
    public Person add(Person person){
        person.persistAndFlush();
        return person;
    }

    @DELETE
    @Transactional
    @Path("deletePerson/{id}")
    public void delete(@RestPath long id){
        Person.findById(id).delete();
    }

    @PATCH
    @Transactional
    @Path("editPerson/{id}")
    public Person edit(@RestPath long id, Person person){
        Person tmp = Person.findById(id);
        tmp.setComment(person.getComment());
        tmp.setFirstname(person.getFirstname());
        tmp.setLastname(person.getLastname());
        tmp.setRole(person.getRole());
        tmp.setEvent(person.getEvent());
        tmp.setTelephone(person.getTelephone());
        tmp.persistAndFlush();
        return person;
    }


}
