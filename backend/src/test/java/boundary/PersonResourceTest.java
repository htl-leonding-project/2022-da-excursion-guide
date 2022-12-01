package boundary;

import at.htl.leotour_backend.entity.Person;
import at.htl.leotour_backend.entity.Role;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class PersonResourceTest {
    long personId;

    @BeforeEach
    @Transactional
    public void setup() {
        Person.deleteAll();
        Person person = new Person();
        person.setFirstname("firstname");
        person.setLastname("lastname");
        person.setRole(Role.MEMBER);
        person.setTelephone("0123456789");
        person.setComment("Person");
        person.persistAndFlush();
        personId = person.getId();
    }

    @Test
    public void getCorrectSizeOfList() {
        given()
                .when().get("/api/person/getAll")
                .then()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    public void getPersonById() {
        given()
                .when().get("/api/person/" + personId)
                .then()
                .statusCode(200)
                .body("firstname ", is("firstname"));
    }



    @Test
    public void addPerson() {
        given()
                .body(Map.of("firstname", "testFname", "lastname", "testLname"))
                .contentType(ContentType.JSON)
                .when().post("/api/person/addPerson")
                .then()
                .statusCode(200)
                .body("firstname", is("testFname"))
                .body("lastname", is("testLname"));

        given()
                .when().get("/api/person/getAll")
                .then()
                .statusCode(200)
                .body("[1].firstname", is("testFname"))
                .body("[1].lastname", is("testLname")); // second of array because one is always persisted
    }

    @Test
    public void editPerson() {
        given()
                .body(Map.of("firstname", "Editfirstname", "lastname", "Editlastname"))
                .contentType(ContentType.JSON)
                .when().patch("/api/person/editPerson/" + personId)
                .then()
                .statusCode(200)
                .body("firstname", is("Editfirstname"))
                .body("lastname", is("Editlastname"));

        given()
                .when().get("/api/person/" + personId)
                .then()
                .statusCode(200)
                .body("firstname", is("Editfirstname"))
                .body("lastname", is("Editlastname"));
    }


    @Test
    public void deletePerson() {
        given()
                .when().delete("/api/person/deletePerson/" + personId)
                .then()
                .statusCode(204);

        given()
                .when().get("/api/person/getAll")
                .then()
                .statusCode(200)
                .body("size()", is(0));
    }
}