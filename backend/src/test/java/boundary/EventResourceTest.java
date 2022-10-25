package boundary;

import entity.Activity;
import entity.Event;
import entity.Person;
import entity.Topic;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class EventResourceTest {

    long eventId;
    LocalDateTime start = LocalDateTime.now();
    LocalDateTime end = LocalDateTime.now().plusHours(2);

    @BeforeEach
    @Transactional
    public void setup() {
        Topic.deleteAll();
        Activity.deleteAll();
        Person.deleteAll();
        Event.deleteAll();
        Event event = new Event();
        event.setLocation("event");
        event.setPlanedStartDateTime(start);
        event.setPlanedEndDateTime(end);
        event.persistAndFlush();
        eventId = event.getId();
    }

    @Test
    void getCorrectSizeOfList() {
        given()
                .when().get("/api/event/getAll")
                .then()
                .statusCode(200)
                .body("size()", is(1));

    }

    @Test
    void getEventById() {
        given()
                .contentType(ContentType.JSON)
                .when().get("/api/event/" + eventId)
                .then()
                .statusCode(200)
                .body("location ", is("event"));
    }

    @Test
    void addEvent() {
        given()
                .body(Map.of("location", "event"))
                .contentType(ContentType.JSON)
                .when().post("/api/event/addEvent")
                .then()
                .statusCode(200)
                .body("location", is("event"));

        given()
                .when().get("/api/event/getAll")
                .then()
                .statusCode(200)
                .body("[1].location", is("event")); // second of array because one is always persisted

    }

    @Test
    void deleteEvent() {
        given()
                .when().delete("/api/event/deleteEvent/" + eventId)
                .then()
                .statusCode(204);

        given()
                .when().get("/api/event/getAll")
                .then()
                .statusCode(200)
                .body("size()", is(0));
    }

    @Test
    void editEvent() {
        given()
                .body(Map.of("location", "Editevent"))
                .contentType(ContentType.JSON)
                .when().patch("/api/event/editEvent/" + eventId)
                .then()
                .statusCode(200)
                .body("location", is("Editevent"));

        given()
                .contentType(ContentType.JSON)
                .when().get("/api/event/" + eventId)
                .then()
                .statusCode(200)
                .body("location", is("Editevent"));
    }
}