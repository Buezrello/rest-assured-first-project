package oauth;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class OAUthTests {

    @Test
    public void firstOauthTest() {

        Map<String, String> params = new HashMap<>();
        params.put("code", "");
        params.put("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
        params.put("client_secret", "erZOWM9g3UtwNRj340YYaK_W");
        params.put("redirect_url", "https://rahulshettyacademy.com/getCourse.php");
        params.put("grant_type", "authorization_code");

        String accessTokenResponse = given()
                .queryParams(params)
                .when()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath jsonPath = new JsonPath(accessTokenResponse);
        String accessToken = jsonPath.getString("access_token");


        String response = given().queryParam("accessToken", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/getCourse.php").asString();

    }
}
