package json.dummy;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ExampleToParse {

    private Dashboard dashboard;
    private List<Course> courses;

    public static String getJsonExampleToParse() {
        ExampleToParse exampleToParse = new ExampleToParse();

        exampleToParse.dashboard = new Dashboard(910, "rahulshettyacademy.com");

        exampleToParse.courses = new ArrayList<>();
        exampleToParse.courses.add(new Course("Selenium Python", 50, 60));
        exampleToParse.courses.add(new Course("Cypress", 40, 4));
        exampleToParse.courses.add(new Course("RPA", 45, 10));

        return new Gson().toJson(exampleToParse);
    }
}

class Dashboard {

    private int purchaseAmount;
    private String website;

    public Dashboard(int purchaseAmount, String website) {
        this.purchaseAmount = purchaseAmount;
        this.website = website;
    }

}

class Course {
    private String title;
    private int price;
    private int copies;

    Course(String title, int price, int copies) {
        this.title = title;
        this.price = price;
        this.copies = copies;
    }
}