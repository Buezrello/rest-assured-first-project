package jira;

import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class JiraTests {

    static SessionFilter sessionFilter = new SessionFilter();
    URL jiraTxtUrl = JiraTests.class.getResource("/jira.txt");


    @BeforeClass
    public void setUp() {
        baseURI = "http://localhost:8080/";

        given().header("Content-Type", "application/json")
                .body("{ \"username\": \"Buezrello\", \"password\": \"Adm1Cmv4$\" }")
                .log().all()
                .filter(sessionFilter)
                .expect().statusCode(200)
                .when().post("rest/auth/1/session");
    }

    @Test
    public void addComment() {

//        given().header("Content-Type", "application/json")
//                .body("{ \"username\": \"Buezrello\", \"password\": \"Adm1Cmv4$\" }")
//                .log().all()
//                .filter(sessionFilter)
//                .expect().statusCode(200)
//                .when().post("rest/auth/1/session");

        // add comment

        given().pathParam("id", "10003").log().all().header("Content-Type", "application/json")
                .body("{" +
                        "\"visibility\": {" +
                        "    \"type\": \"role\"," +
                        "    \"value\": \"Administrators\"" +
                        "  }," +
                        "  \"body\": \"I have a comment here\"" +
                        "}")
                .filter(sessionFilter)
                .when().post("rest/api/2/issue/{id}/comment")
                .then().log().all().assertThat().statusCode(201);
    }

    @Test
    public void addAttachment() throws URISyntaxException {

        given().pathParam("id", "10003").log().all()
                .header("X-Atlassian-Token", "no-check")
                .header("Content-Type", "multipart/form-data")
                .multiPart("file", new File(jiraTxtUrl.toURI()))
                .filter(sessionFilter)
                .when().post("rest/api/2/issue/{id}/attachments")
                .then().log().all().assertThat().statusCode(200);

    }

    @Test
    public void getIssue() {
        String response = given()
                .filter(sessionFilter)
                .pathParam("id", "10003")
                .queryParam("fields", "comment")
                .when()
                .get("rest/api/2/issue/{id}")
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response().asString();

        JsonPath jsonPath = new JsonPath(response);
        String commentComments = "fields.comment.comments";
        int commentsCount = jsonPath.getInt(String.format("%s.size()", commentComments));
        for (int i=0; i<commentsCount; ++i) {
            System.out.println(jsonPath.getInt(String.format("%s[%d].id", commentComments, i)));
        }
    }
}

