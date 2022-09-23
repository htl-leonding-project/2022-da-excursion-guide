package boundary;

import entity.Person;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/person")
public class PersonResource {

    @Inject
    EntityManager em;

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok(Person.listAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(long id) {
        return Response.ok(Person.findById(id)).build();
    }

    @POST
    @Transactional
    @Path("addPerson")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Person person) {
        person.persistAndFlush();
        return Response.ok(person).build();
    }

    @DELETE
    @Transactional
    @Path("deletePerson/{id}")
    public void delete(@RestPath long id) {
        Person.findById(id).delete();
    }

    @PATCH
    @Transactional
    @Path("editPerson/{id}")
    public Response edit(@RestPath long id, Person person) {
        Person tmp = Person.findById(id);
        tmp.setComment(person.getComment());
        tmp.setFirstname(person.getFirstname());
        tmp.setLastname(person.getLastname());
        tmp.setRole(person.getRole());
        tmp.setTelephone(person.getTelephone());
        tmp.persistAndFlush();
        return Response.ok(person).build();
    }

    @GET
    @Path("/getByName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@RestPath String name) {
        return Response.
                ok(em.createQuery("SELECT p from Person p WHERE p.lastname Like :NAME")
                        .setParameter("NAME", name)
                        .getResultList()
                ).build();
    }


}
