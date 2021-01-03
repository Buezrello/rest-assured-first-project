package json.postbody;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RsaMap {

    private Location location = new Location();
    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private List<String> types = new ArrayList<>();
    private String website;
    private String language;

    public static String getJsonRsaMap() {
        RsaMap rsaMap = new RsaMap();

        rsaMap.location.lat = -38.383494;
        rsaMap.location.lng = 33.427362;
        rsaMap.accuracy = 50;
        rsaMap.name = "Frontline house";
        rsaMap.phone_number = "(+91) 983 893 3937";
        rsaMap.address = "29, side layout, cohen 09";
        rsaMap.types.add("shoe park");
        rsaMap.types.add("shop");
        rsaMap.website = "http://google.com";
        rsaMap.language = "French-IN";

        return new Gson().toJson(rsaMap);
    }

    class Location {
        double lat;
        double lng;
    }
}
