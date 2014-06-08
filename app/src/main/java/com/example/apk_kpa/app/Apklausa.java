package com.example.apk_kpa.app;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by VIO on 6/8/2014.
 */
public class Apklausa extends ListActivity {
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    ArrayList<HashMap<String, String>> inboxList;

    JSONArray inbox = null;

    private static final String INBOX_URL = "http://mokslai.ger.us.lt/pridetiKlausima.php";

    private static final String TAG_MESSAGES = "turinys";
    private static final String TAG_TITLE = "Pavadinimas";
    private static final String TAG_ATS1 = "Pasirinkimas1";
    private static final String TAG_ATS2 = "Pasirinkimas2";
    private static final String TAG_ATS3 = "Pasirinkimas3";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_list);

        // Hashmap for ListView
        inboxList = new ArrayList<HashMap<String, String>>();

        new LoadInbox().execute();
    }
//    @Override
//    public void onBackPressed(){
//        new LoadInbox().execute();
//    }

    class LoadInbox extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Apklausa.this);
            pDialog.setMessage("Kraunasi...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting Inbox JSON
         * */
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            // getting JSON string from URL
            JSONObject json = jsonParser.makeHttpRequest(INBOX_URL, "GET",
                    params);

            Log.d("JSON: ", json.toString());

            try {
                inbox = json.getJSONArray(TAG_MESSAGES);
                // visi duomenys
                for (int i = 0; i < inbox.length(); i++) {
                    JSONObject c = inbox.getJSONObject(i);
                    String from = c.getString(TAG_TITLE);
                    String ats1 = c.getString(TAG_ATS1);
                    String ats2 = c.getString(TAG_ATS2);
                    String ats3 = c.getString(TAG_ATS3);

                    // HashMap sukurimas
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put(TAG_TITLE, from);
                    map.put(TAG_ATS1, ats1);
                    map.put(TAG_ATS2, ats2);
                    map.put(TAG_ATS3, ats3);

                    // HashList to ArrayList
                    inboxList.add(map);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        protected void onPostExecute(String file_url) {
            // dismiss
            pDialog.dismiss();

            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating JSON data
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            Apklausa.this, inboxList,
                            R.layout.klausymas, new String[] { TAG_TITLE, TAG_ATS1, TAG_ATS2, TAG_ATS3 },
                            new int[] { R.id.from, R.id.ats1, R.id.ats2, R.id.ats3});

                    setListAdapter(adapter);

                }
            });



        }

    }

}
