package lib;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VIO on 5/28/2014.
 */
public class User_func {

    private JSONParser jsonParser;

    //url i webservisa for login
    //private static String loginURL = "";

    //url i webservisa for register
    private static String new_client = "http://mokslai.ger.us.lt/webservice.php";

    //private static String login_tag = "login";
    private static String register_tag = "register";

    // constructor
    public User_func() {
        jsonParser = new JSONParser();
    }

//    /**
//     * function make Login Request
//     * @param email
//     * @param password
//     * */
//    public JSONObject loginUser(String email, String password){
//        // Building Parameters
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("tag", login_tag));
//        params.add(new BasicNameValuePair("email", email));
//        params.add(new BasicNameValuePair("password", password));
//        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
//        // return json
//        // Log.e("JSON", json.toString());
//        return json;
//    }

    /**
     * function make Login Request
     *
     * @param name
     * @param password
     */
    public JSONObject registerUser(String name, String password) {
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("password", password));

        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(new_client, params);
        // return json
        return json;
    }

}
