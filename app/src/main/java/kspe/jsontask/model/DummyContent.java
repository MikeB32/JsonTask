package kspe.jsontask.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();




    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        // Basic info
        ITEM_MAP.put(item.profile_pic, item);
        ITEM_MAP.put(item.full_name, item);
        ITEM_MAP.put(item.gender,item);
        ITEM_MAP.put(item.phone_no, item);
        ITEM_MAP.put(item.email, item);

        //location
        ITEM_MAP.put(item.street, item);
        ITEM_MAP.put(item.city, item);
        ITEM_MAP.put(item.state, item);
        ITEM_MAP.put(item.postcode, item);
        ITEM_MAP.put(item.latitude, item);
        ITEM_MAP.put(item.longitude, item);
        ITEM_MAP.put(item.offset, item);
        ITEM_MAP.put(item.description, item);

        //login
        ITEM_MAP.put(item.uuid, item);
        ITEM_MAP.put(item.username, item);
        ITEM_MAP.put(item.password, item);
        ITEM_MAP.put(item.salt, item);
        ITEM_MAP.put(item.md5, item);
        ITEM_MAP.put(item.sha1, item);
        ITEM_MAP.put(item.sha256, item);

        //dob
        ITEM_MAP.put(item.dobDate, item);
        ITEM_MAP.put(item.dobAge, item);

        //register
        ITEM_MAP.put(item.registerDate, item);
        ITEM_MAP.put(item.registerAge, item);

        //id
        ITEM_MAP.put(item.idName, item);
        ITEM_MAP.put(item.idValue, item);

        //nationality
        ITEM_MAP.put(item.nat, item);




    }



    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem  {
        //        Basic info
        public final String profile_pic;
        public final String full_name;
        public final String gender;
        public final String phone_no;
        public final String email;
        public final String cell;


        //location
        public final String street;
        public final String city;
        public final String state;
        public final String postcode;
        //coordinates
        public final String latitude;
        public final String longitude;
        //timezone
        public final String offset;
        public final String description;

        //login
        public final String uuid;
        public final String username;
        public final String password;
        public final String salt;
        public final String md5;
        public final String sha1;
        public final String sha256;

        //dob
        public final String dobDate;
        public final String dobAge;

        //register
        public final String registerDate;
        public final String registerAge;

        //id
        public final String idName;
        public final String idValue;

        //nationality
        public final String nat;






        public DummyItem(String profile_pic, String full_name,String gender, String email ,String phone_no,String cell,
                         String street,  String city,String state ,String postcode, String latitude, String longitude,String offset,String description,
                         String uuid,String username,String password,String salt,String md5,String sha1,String sha256,
                         String dobDate,String dobAge,
                         String registerDate,String registerAge,
                         String idName,String idValue,
                         String nat) {
            //   basic info
            this.profile_pic = profile_pic;
            this.full_name = full_name;
            this.gender = gender;
            this.phone_no = phone_no;
            this.email = email;
            this.cell = cell;

            //location
            this.street = street;
            this.city = city;
            this.state = state;
            this.postcode = postcode;
            this.latitude = latitude;
            this.longitude = longitude;
            this.offset = offset;
            this.description = description;

            //login
            this.uuid = uuid;
            this.username = username;
            this.password = password;
            this.salt = salt;
            this.md5 = md5;
            this.sha1 = sha1;
            this.sha256 = sha256;

            //dob
            this.dobDate = dobDate;
            this.dobAge = dobAge;

            //register
            this.registerDate = registerDate;
            this.registerAge = registerAge;


            //Id
            this.idName = idName;
            this.idValue = idValue;

            //nationality
            this.nat = nat;


        }

    }
}
