package com.example.apk_kpa.app;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Created by VIO on 5/22/14.
 */
public class Register extends Activity {

    String url = null;
    String name;
    String nick;
    String email;
    String miestas;
    String psw;
    String rePsw;
    String dialog_title = null;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // elementai
        final EditText e_nick = (EditText) findViewById(R.id.nick);
        final EditText e_name = (EditText) findViewById(R.id.name);
        final EditText e_email = (EditText) findViewById(R.id.email);
        final EditText e_miestas = (EditText) findViewById(R.id.miestas);
        final EditText e_psw = (EditText) findViewById(R.id.psw);
        final EditText e_rePsw = (EditText) findViewById(R.id.rePsw);
        Button regBtn = (Button) findViewById(R.id.regBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {

                //start the progress dialog
                progressDialog = ProgressDialog.show(Register.this, dialog_title, "Kraunasi...");
                // priskiriam string reiksmes
                nick = e_nick.getText().toString();
                name = e_name.getText().toString();
                email = e_email.getText().toString();
                miestas = e_miestas.getText().toString();
                psw = e_psw.getText().toString();
                rePsw = e_rePsw.getText().toString();

                RegisterTask log = new RegisterTask();
                log.execute(url);
            };
        });
    }

    // klase prisijungimui prie webservo
    public class RegisterTask extends AsyncTask<String, Void, Boolean> {

        public RegisterTask() { };

        InputStream is = null;
        String line = null;
        String result = null;

        @Override
        protected Boolean doInBackground(String... url) {

            boolean goo = false;

            // prisijungimo aprasymas
            HttpClient httpclient = new DefaultHttpClient();
            final HttpPost httppost = new HttpPost("http://mokslai.ger.us.lt/registracija.php");

            try {

                    // (string) reiksmiu suvarymas i array
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                    nameValuePairs.add(new BasicNameValuePair("nick", nick));
                    nameValuePairs.add(new BasicNameValuePair("name", name));
                    nameValuePairs.add(new BasicNameValuePair("email", email));
                    nameValuePairs.add(new BasicNameValuePair("miestas", miestas));
                    nameValuePairs.add(new BasicNameValuePair("psw", psw));


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

                        if ((nick.equals("")) || (name.equals("")) || (email.equals("")) || (miestas.equals("")) || (psw.equals("")) || (rePsw.equals("") || (!(psw.equals(rePsw))))) {

                            runOnUiThread(new Runnable() {
                                public void run() {
                                    // dismiss the progress dialog
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Klaida! Blogai įvesti duomenys!", Toast.LENGTH_LONG).show();
                                }
                            });
                        }else {
                            while ((line = reader.readLine()) != null) {
                                sb.append(line);
                            }
                            is.close();
                            result = sb.toString();
                            Log.e("prisijungimas:", "connection success #2");

                            // pranesimas useriui
                            if(result.equals("false")) {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                            Log.e("Result of 1: ",result);
                                        Intent intent = new Intent(Register.this,LogedIn.class);
                                        startActivity(intent);
                                        // dismiss the progress dialog
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Jūs sėkmingai užsiregistravote !", Toast.LENGTH_LONG).show();

                                    }
                                });
                            }else {
                                Log.e("Result of 0: ",result);
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        // dismiss the progress dialog
                                        // dismiss the progress dialog
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Toks vartotojas jau egzistuoja!", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }


                        }
                    }catch (Exception e) {
                        Log.e("prisijungimas fail 2", e.toString());
                    }
                }catch(Exception e){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            // dismiss the progress dialog
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "No connection!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            return goo;
        }
    }
}








