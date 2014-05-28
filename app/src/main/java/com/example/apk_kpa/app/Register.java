package com.example.apk_kpa.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import lib.User_func;


/**
 * Created by VIO on 5/22/14.
 */
public class Register extends Activity {

    EditText nick;
    EditText psw;
    Button RegBtn;

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_CREATED_AT = "created_at";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        nick = (EditText) findViewById(R.id.nick);
        psw = (EditText) findViewById(R.id.psw);
        RegBtn = (Button) findViewById(R.id.RegBtn);

        // Register Button Click event
        RegBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = nick.getText().toString();
                String password = psw.getText().toString();
                User_func userFunction = new User_func();
                JSONObject json = userFunction.registerUser(name, password);

                // check for login response

                if (KEY_SUCCESS != null) {
                    // registerErrorMsg.setText("");
                    String res = KEY_SUCCESS;
                    if (Integer.parseInt(res) == 1) {

                        try {
                            JSONObject json_user = json.getJSONObject("user");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


        });


//
//                                db.addUser(json_user.getString(KEY_NAME), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));
//                                finish();
//                            }else{
//                                // Error in registration
//                                registerErrorMsg.setText("Error occured in registration");
//


        //metodas logedIn klases iskvietimui

//                public void loged_in(View view2) {
//                    startActivity(new Intent(this, LogedIn.class));
//                }
//            }
    }
}
