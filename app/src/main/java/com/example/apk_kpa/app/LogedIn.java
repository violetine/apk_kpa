package com.example.apk_kpa.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Marius on 5/25/14.
 */
public class LogedIn extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loged_in);
    }

    public void start_apk (View view) {

        startActivity(new Intent(this, Apklausa.class));
    }


}