package com.example.apk_kpa.app;
/**
 * Created by Dofke on 2014-06-08.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.apk_kpa.app.lib.JSONParser;

public class JSONTest extends Activity {
    TextView titleName;
    Button Btngetdata;

    //URL to get JSON Array
    private static String url = "http://mokslai.ger.us.lt/pridetiKlausima.php";
    //JSON Node Names
    private static final String TAG_CONTENT = "turinys";
    private static final String TAG_TITLE = "Pavadinimas";
    //    private static final String TAG_NAME = "name";
//    private static final String TAG_EMAIL = "email";
    JSONArray content_tag = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_data);
        Btngetdata = (Button)findViewById(R.id.getdata);
        Btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONParse().execute();
            }
        });
    }
    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            titleName = (TextView)findViewById(R.id.uid);

            pDialog = new ProgressDialog(JSONTest.this);
            pDialog.setMessage("Gaunami duomenys ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
                // Getting JSON Array
                content_tag = json.getJSONArray(TAG_CONTENT);
                JSONObject c = content_tag.getJSONObject(0);
                // Storing  JSON item in a Variable
                String title = c.getString(TAG_TITLE);

                //Set JSON Data in TextView
                titleName.setText(title);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
