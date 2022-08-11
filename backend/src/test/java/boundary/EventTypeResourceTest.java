package boundary;

import entity.EventType;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class EventTypeResourceTest {

    long eventTypeId;

    @BeforeEach
    @Transactional
    public void setup() {
        EventType.deleteAll();
        EventType eventType = new EventType();
        eventType.setType("excursion");
        eventType.setMaxPersonAllowed(20);
        eventType.persistAndFlush();
        eventTypeId = eventType.getId();
    }
    @Test
    void listEventType() {
        given()
                .when().get("/api/eventtype/getAll")
                .then()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    void getEventTypeById() {
        given()
                .when().get("/api/eventtype/" + eventTypeId)
                .then()
                .statusCode(200)
                .body("type ", is("excursion"))
                .body("maxPersonAllowed",is(20));
    }

    @Test
    void addEventType() {
        given()
                .body(Map.of("type", "excursion", "maxPersonAllowed", 20))
                .contentType(ContentType.JSON)
                .when().post("/api/eventtype/addEventType")
                .then()
                .statusCode(200)
                .body("type", is("excursion"))
                .body("maxPersonAllowed", is(20));

        given()
                .when().get("/api/eventtype/getAll")
                .then()
                .statusCode(200)
                .body("[1].type", is("excursion"))
                .body("[1].maxPersonAllowed", is(20)); // second of array because one is always persisted
    }

    @Test
    void deleteEventType() {
        given()
                .when().delete("/api/eventtype/deleteEventType/" + eventTypeId)
                .then()
                .statusCode(204);

        given()
                .when().get("/api/eventtype/getAll")
                .then()
                .statusCode(200)
                .body("size()", is(0));
    }

    @Test
    void editEventType() {
        given()
                .body(Map.of("type", "Editexcursion", "maxPersonAllowed", 30))
                .contentType(ContentType.JSON)
                .when().patch("/api/eventtype/editEventType/" + eventTypeId)
                .then()
                .statusCode(200)
                .body("type", is("Editexcursion"))
                .body("maxPersonAllowed", is(30));

        given()
                .when().get("/api/eventtype/" + eventTypeId)
                .then()
                .statusCode(200)
                .body("type", is("Editexcursion"))
                .body("maxPersonAllowed", is(30));
    }
}