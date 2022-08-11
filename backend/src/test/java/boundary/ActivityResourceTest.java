package boundary;

import entity.Activity;
import entity.Topic;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ActivityResourceTest {

    long activityId;
    LocalDateTime start = LocalDateTime.now();
    LocalDate date = LocalDate.now();

    @BeforeEach
    @Transactional
    public void setup() {
        Activity.deleteAll();
        Activity activity = new Activity();
        activity.setPublicationDate(date);
        activity.setActivityName("Activity");
        activity.setPublic(true);
        activity.setLongitude(0.001);
        activity.setLatitude(0.001);
        activity.setComment("comment");
        activity.setStartDateTime(start);
        activity.setBelongsTo(new Topic());
        activity.persistAndFlush();
        activityId = activity.getId();
    }

    @Test
    void listAllActivities() {
        given()
                .when().get("/api/activity/getAll")
                .then()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    void getActivityById() {
        given()
                .when().get("/api/activity/" + activityId)
                .then()
                .statusCode(200)
                .body("activityName ", is("Activity"));
    }

    @Test
    void addActivity() {
        given()
                .body(Map.of("activityName", "Activity", "isPublic", true,"comment","comment"))
                .contentType(ContentType.JSON)
                .when().post("/api/activity/addActivity")
                .then()
                .statusCode(200)
                .body("activityName", is("Activity"))
                .body("isPublic", is(true))
                .body("comment", is("comment"));

        given()
                .when().get("/api/activity/getAll")
                .then()
                .statusCode(200)
                .body("[1].activityName",  is("Activity"))
                .body("[1].comment", is("comment"))
                .body("[1].isPublic", is(true)); // second of array because one is always persisted

    }

    @Test
    void delete() {
    }

    @Test
    void edit() {
    }
}