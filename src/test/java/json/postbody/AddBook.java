package json.postbody;

import com.google.gson.Gson;

public class AddBook {

    private String name;
    private String isbn;
    private String aisle;
    private String author;

    public AddBook(String name, String isbn, String aisle, String author) {
        this.name = name;
        this.isbn = isbn;
        this.aisle = aisle;
        this.author = author;
    }

    public AddBook(String isbn, String aisle) {
        this("Learn Appium Automation with Java", isbn, aisle, "John foe");
    }

    public static String getJsonString(String isbn, String aisle) {
        AddBook addBook = new AddBook(isbn, aisle);
        return new Gson().toJson(addBook);
    }

    public static String getJsonString(String name, String isbn, String aisle, String author) {
        AddBook addBook = new AddBook(name, isbn, aisle, author);
        return new Gson().toJson(addBook);
    }
}
