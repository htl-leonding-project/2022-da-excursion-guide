package boundary;

import at.htl.leotour_backend.entity.Activity;
import at.htl.leotour_backend.entity.Topic;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class TopicResourceTest {

    long topicId;

    @BeforeEach
    @Transactional
    public void setup() {
        Activity.deleteAll();
        Topic.deleteAll();
        Topic topic = new Topic();
        topic.setName("topic");
        topic.setComment("comment");
        topic.persistAndFlush();
        topicId = topic.getId();
    }

    @Test
    void getCorrectSizeOfList() {
        given()
                .when().get("/api/topic/getAll")
                .then()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    void getTopicById() {
        given()
                .when().get("/api/topic/" + topicId)
                .then()
                .statusCode(200)
                .body("name ", is("topic"));

    }

    @Test
    void addTopic() {
        given()
                .body(Map.of("name", "topic", "comment", "comment"))
                .contentType(ContentType.JSON)
                .when().post("/api/topic/addTopic")
                .then()
                .statusCode(200)
                .body("name", is("topic"))
                .body("comment", is("comment"));

        given()
                .when().get("/api/topic/getAll")
                .then()
                .statusCode(200)
                .body("[1].name", is("topic"))
                .body("[1].comment", is("comment")); // second of array because one is always persisted
    }

    @Test
    void deleteTopic() {
        given()
                .when().delete("/api/topic/deleteTopic/" + topicId)
                .then()
                .statusCode(204);

        given()
                .when().get("/api/topic/getAll")
                .then()
                .statusCode(200)
                .body("size()", is(0));
    }


    @Test
    void editTopic() {
        given()
                .body(Map.of("name", "Editname", "comment", "Editcomment"))
                .contentType(ContentType.JSON)
                .when().patch("/api/topic/editTopic/" + topicId)
                .then()
                .statusCode(200)
                .body("name", is("Editname"))
                .body("comment", is("Editcomment"));

        given()
                .when().get("/api/topic/" + topicId)
                .then()
                .statusCode(200)
                .body("name", is("Editname"))
                .body("comment", is("Editcomment"));
    }
}