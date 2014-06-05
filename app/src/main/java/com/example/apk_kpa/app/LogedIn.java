package com.example.apk_kpa.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Marius on 5/25/14.
 */
public class LogedIn extends Activity {
    private String slapyvardis;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loged_in);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            slapyvardis = extras.getString("nickas");
        }
        TextView nickas = (TextView)findViewById(R.id.lblNick);
        nickas.setText("Slapyvardis: "+slapyvardis);
    }




}