package basic;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import json.postbody.RsaMap;
import json.putbody.UpdateAddress;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class BasicCase {

    @Test
    public void postTesting() {
        String jsonBody = RsaMap.getJsonRsaMap();

        baseURI = "https://rahulshettyacademy.com";

        given().log().all()
                .queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(jsonBody)
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)");
    }

    @Test
    public void putTesting() {
        String jsonBody = RsaMap.getJsonRsaMap();

        baseURI = "https://rahulshettyacademy.com";

        String response = given().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json").body(jsonBody)
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath = new JsonPath(response);
        String placeId = jsonPath.getString("place_id");

        String newAddress = "Hashoftim 9 Petah Tikva";
        String jsonUpdateAddressBody = UpdateAddress.getUpdateAddressJson(placeId, newAddress);

        given().log().all()
                .body(jsonUpdateAddressBody)
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        given().log().all()
                .queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200)
                .body("address", equalTo(newAddress));
    }
}
