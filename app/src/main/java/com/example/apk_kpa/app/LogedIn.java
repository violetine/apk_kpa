package com.example.apk_kpa.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.apk_kpa.app.SwipeMenu.SwipeAdmin;
import com.example.apk_kpa.app.SwipeMenu.SwipeUser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Marius on 5/25/14.
 */
public class LogedIn extends Activity {

    private String slapyvardis;
    private String email = "N/A";

    private static String data;

    private static final String TAG_MESSAGES = "turinys";
    private static final String TAG_EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        data = getIntent().getExtras().getString("nickas");


        Intent intent = new Intent(this, SwipeUser.class);

        intent.putExtra("email",email);
        intent.putExtra("nickas",data);


        startActivity(intent);




    }








}
