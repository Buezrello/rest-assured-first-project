package basic;

import io.restassured.path.json.JsonPath;
import json.dummy.ExampleToParse;

import java.util.List;
import java.util.Map;

import static utilities.ListsAndAtherCollections.zipListsToMap;

public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath jsonPath = new JsonPath(ExampleToParse.getJsonExampleToParse());

        // Amount of courses in the JSON
        int amountOfCourses = jsonPath.getInt("courses.size()");

        System.out.println("AMOUNT OF COURSES : " + amountOfCourses);

        // purchase amount
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");

        System.out.println("PURCHASE AMOUNT : " + purchaseAmount );

        // first title
        String firstTitle = jsonPath.getString("courses[0].title");

        System.out.println("FIRST TITLE: " + firstTitle);

        // all courses titles and their prices
        List<String> titles = jsonPath.getList("courses.title");
        List<Integer> prices = jsonPath.getList("courses.price");
        Map<String, Integer> titlesAndPrices = zipListsToMap(titles, prices);

        titlesAndPrices.forEach((k, v) -> {
            System.out.println("TITLE " + k + ", PRICE " + v);
        });

//        // number of copies sold by RPA
//        List<Integer> copies = jsonPath.param("name", "Cypress").get("courses.findAll{title -> title == name}.copies");
//        copies.forEach((s) -> {
//            System.out.println("RPA COPIES : " + s);
//        });

    }
}
