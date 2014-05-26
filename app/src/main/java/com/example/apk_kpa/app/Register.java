package com.example.apk_kpa.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;


/**
 * Created by VIO on 5/22/14.
 */
public class Register extends Activity {

    JSONParser jsonParser = new JSONParser();
    EditText nick;
    EditText psw;

    // url to create new product
    private static String new_client = "http://mokslai.ger.us.lt/webservice.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

//    norint sukurti nauja veikianti buttona:
//    1,xml faile prie button pridedam android:onclick"click(metodo pav, kuris aprasomas veliau mainActivity)"
//    2,mainActivity aprasom metoda "click" su nuoroda i klase, pvz. Click.class
//    3,Click klaseje apsirasom kvieciamo loyouto pavadinima, pvz R.layout.click
//    4,nepamirstam AndroidManifest nurodyti aprasytos click klases : android:activity".click"


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        nick = (EditText) findViewById(R.id.nick);
        psw = (EditText) findViewById(R.id.psw);

    }

    //metodas logedIn klases iskvietimui
    public void loged_in (View view2) {

        startActivity(new Intent(this, LogedIn.class));
    }

    //sutvarkyti nuo sitos vietos
    protected String doInBackground(String... args) {
        String name = nick.getText().toString();
        String pass = psw.getText().toString();

        JSONArray json = jsonParser.getJSONFromUrl(new_client);


        try {
           // int success = json.getInt(TAG_SUCCESS);

           // if (success == 1) {

                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
               // finish();

//            } else {
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
