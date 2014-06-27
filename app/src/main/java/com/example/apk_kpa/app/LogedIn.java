package com.example.apk_kpa.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    public static String email,points,questions;

    private static String data = "";

    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    public static ArrayList<HashMap<String, String>> inboxList;
    JSONArray inbox = null;

    private static final String INBOX_URL = "http://mokslai.ger.us.lt/getUserInfo.php";
    private static final String INBOX_URL_TABLE = "http://mokslai.ger.us.lt/pridetiKlausimaV2.php";
    private static final String TAG_MESSAGES = "turinys";
    private static final String TAG_EMAIL = "email";

    private static final String TAG_POINTS = "taskai";
    private static final String TAG_QUESTIONS = "atsake";
    public static int LIMIT = 0;

    private Intent a;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        data = getIntent().getExtras().getString("nickas");



        inboxList = new ArrayList<HashMap<String, String>>();



        new LoadInbox().execute();

        Intent intent = new Intent(this, SwipeUser.class);
        a = intent;

        intent.putExtra("nickas",data);

        startActivity(intent);


    }
//////////////////////////////////////////////////////////////////////////////
    public class LoadInbox extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

                    jsData();


                return null;
            }

            @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LogedIn.this);
            pDialog.setMessage("Kraunasi...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting Inbox JSON
         */


        protected void jsData (){

            ArrayList<NameValuePair> res = new ArrayList<NameValuePair>();

            // Kreipimosi ID i WebServisa
            res.add(new BasicNameValuePair("name",data));

            JSONObject json = jsonParser.makeHttpRequest(INBOX_URL, "POST",res);


            try {
                inbox = json.getJSONArray(TAG_MESSAGES);
                // visi duomenys
                for (int i = 0; i < inbox.length(); i++) {
                    JSONObject c = inbox.getJSONObject(i);
                    String from = c.getString(TAG_EMAIL);
                    String pointers = c.getString(TAG_POINTS);
                    String questioner = c.getString(TAG_QUESTIONS);

                    email = from;
                    points = pointers;
                    questions = questioner;
                    // HashMap sukurimas
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put(TAG_EMAIL, from);
                    map.put(TAG_POINTS,pointers);
                    map.put(TAG_QUESTIONS,questioner);

                    // HashList to ArrayList
                    inboxList.add(map);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pDialog.dismiss();

            //intent.putExtra("nickas",data);

        }

    }










}
