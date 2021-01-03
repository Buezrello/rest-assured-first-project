package dynamic;

import io.restassured.path.json.JsonPath;
import json.postbody.AddBook;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test
    public void addBook() {
        baseURI = "http://216.10.245.166";

        String isbn = RandomStringUtils.randomAlphabetic(5);
        String aisle = RandomStringUtils.randomNumeric(5);

        String response = given().header("Content-Type", "application/json")
                .body(AddBook.getJsonString(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath jsonResponse = JsonPath.from(response);
        String id = jsonResponse.get("ID");

//        System.out.println(id);

        Assert.assertEquals(id, isbn + aisle);
    }
}
