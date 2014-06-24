package com.example.apk_kpa.app;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.apk_kpa.app.database.DBHelper;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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

    DBHelper mydb;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    ArrayList<HashMap<String, String>> inboxList;
    JSONArray inbox = null;


    private static final String INBOX_URL = "http://mokslai.ger.us.lt/pridetiKlausimaV2.php";
    private static final String TAG_MESSAGES = "turinys";
    private static final String TAG_TITLE = "Pavadinimas";
    private static final String TAG_ATS1 = "Pasirinkimas1";
    private static final String TAG_ATS2 = "Pasirinkimas2";
    private static final String TAG_ATS3 = "Pasirinkimas3";
    private RadioGroup group1;
    private RadioButton pasirinkimas;
    public TextView kla;
    String pas;
    String klau;
    int count = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.json_list);
        mydb = new DBHelper(this);
        inboxList = new ArrayList<HashMap<String, String>>();
        new LoadInbox().execute();

        Button but = (Button) findViewById(R.id.submit);
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count++;
                Log.e("Skaiciuoju clickus:", String.valueOf(count));
                getKlausymas();
                getAtsakymas();
                mydb.insertKl(klau,pas);
            }
        });
    }

    public String getKlausymas(){

        kla = (TextView) findViewById(R.id.kl);
        klau = kla.getText().toString();
        return klau;
    }

    public String getAtsakymas(){
        group1 = (RadioGroup) findViewById(R.id.group1);
        int selectedId = group1.getCheckedRadioButtonId();
        pasirinkimas = (RadioButton) findViewById(selectedId);
        pas = pasirinkimas.getText().toString();
        mydb = new DBHelper(this);
        inboxList = new ArrayList<HashMap<String, String>>();
        new LoadInbox().execute();
        return pas;

    }

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
                 */
                protected String doInBackground(String... args) {

                    jsData();
                    return null;
                }

                protected String jsData (){

                    ArrayList<NameValuePair> res = new ArrayList<NameValuePair>();

                    // Kreipimosi ID i WebServisa
                    res.add(new BasicNameValuePair("res",String.valueOf(count)));

                    JSONObject json = jsonParser.makeHttpRequest(INBOX_URL, "POST",res);


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
                            mydb.klausymai(from,ats1,ats2,ats3);
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
                                    R.layout.klausymas, new String[]{TAG_TITLE, TAG_ATS1, TAG_ATS2, TAG_ATS3},
                                    new int[]{R.id.kl, R.id.ats1, R.id.ats2, R.id.ats3});

                            setListAdapter(adapter);

                                }
                    });
            }

     }
}

