package com.example.apk_kpa.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONArray;
//import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.app.ProgressDialog;

import com.example.apk_kpa.app.database.DBHelper;


/**
 * Created by Dofke on 2014/06/01.
 */
public class Login extends Activity {


    String url = null;
    String code;
    String name;
    String passwd;
    String dialog_title = null;
    ProgressDialog progressDialog;
    boolean a;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.login);

        final DBHelper mydb = new DBHelper(this);
        // elementai
        final EditText e_code = (EditText) findViewById(R.id.code);
        final EditText e_name = (EditText) findViewById(R.id.name);
        final EditText e_password = (EditText) findViewById(R.id.passw);

        Button logBtn = (Button) findViewById(R.id.loginBtn);

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //start the progress dialog
                progressDialog = ProgressDialog.show(Login.this, dialog_title, "Kraunasi...");
                // priskiriam string reiksmes
                code = e_code.getText().toString();
                name = e_name.getText().toString();
                passwd = e_password.getText().toString();
                mydb.newUser(name,passwd,code);


                if(isNetworkAvailable() == true){
                    LoginTask log = new LoginTask();
                    log.execute(url);
                }else{
                    mydb.activeUser(name,code,a);
                    if(a == true){
                        Intent intent = new Intent(Login.this,LogedIn.class);
                        intent.putExtra("nickas ",name);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Blogai įvesti duomenys arba toks vartotojas/ID neegzistuoja !", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // klase prisijungimui prie webservo
    public class LoginTask extends AsyncTask<String, Void, Boolean> {

        public LoginTask(){
        }

        InputStream is = null;
        String line = null;
        String result = null;

        @Override
        protected Boolean doInBackground(String... url) {

            // prisijungimo aprasymas
            final HttpClient httpclient = new DefaultHttpClient();
            final HttpPost httppost = new HttpPost("http://mokslai.ger.us.lt/login_ws.php");


            // (string/integer) reiksmiu suvarymas i array
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

//            nameValuePairs.add(new BasicNameValuePair("code", Integer.toString(code)));
            nameValuePairs.add(new BasicNameValuePair("code", code));
            nameValuePairs.add(new BasicNameValuePair("name", name));
            nameValuePairs.add(new BasicNameValuePair("passw", passwd));

            try {

                // bandom jungtis
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();


                // isvedimas LogCat konsolej (debugui)
                Log.e("prisijungimas", "connection success #1");

                try {

                    Thread.sleep(1000);
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    is.close();
                    result = sb.toString();
                    Log.e("Result: ",result);

                    Log.e("prisijungimas:", "connection success #2");


                    // tusti laukai
                    if (passwd.length() == 0 || name.length() == 0 || code.length() == 0) {

                        // pranesimas useriui
                        runOnUiThread(new Runnable() {
                            public void run() {
                                // dismiss the progress dialog
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Neužpildyti visi laukai !", Toast.LENGTH_LONG).show();

                            }
                        });
                    } else {

                        // pranesimas useriui
                        if(result.equals("1")) {
                            runOnUiThread(new Runnable() {
                                public void run() {

                                    Intent intent = new Intent(Login.this,LogedIn.class);
                                    intent.putExtra("nickas",name);
                                    startActivity(intent);
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), name+", sėkmingai prisijungei !", Toast.LENGTH_LONG).show();

                                }
                            });
                        }else if(result.equals("2")) {

                            runOnUiThread(new Runnable() {
                                public void run() {

                                    Intent intent = new Intent(Login.this,Admin.class);
                                    intent.putExtra("nickas",name);
                                    startActivity(intent);

                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Sveikinu prisijungus,"+name+"!", Toast.LENGTH_LONG).show();
                                }
                            });


                        } else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                // dismiss the progress dialog
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Blogai įvesti duomenys arba toks vartotojas/ID neegzistuoja !", Toast.LENGTH_LONG).show();
                            }
                        });

                        }
                    }

                } catch (Exception e) {
                    Log.e("prisijungimas fail 2", e.toString());
                }

            } catch (Exception e) {

                Log.e("fail", e.toString());

                // pranesimas useriui
                runOnUiThread(new Runnable() {
                    public void run() {
                        // dismiss the progress dialog
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Prisijungti nepavyko!", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return false;
        }
    }

}
