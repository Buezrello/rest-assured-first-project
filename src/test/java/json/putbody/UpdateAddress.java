package json.putbody;

import com.google.gson.Gson;

public class UpdateAddress {

    private String place_id;
    private String address;
    private String key = "qaclick123";

    public static String getUpdateAddressJson(String place_id, String address) {
        UpdateAddress updateAddress = new UpdateAddress();
        updateAddress.place_id = place_id;
        updateAddress.address = address;

        return new Gson().toJson(updateAddress);
    }
}
