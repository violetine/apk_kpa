package com.example.apk_kpa.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.apk_kpa.app.SwipeMenu.SwipeUser;



/**
 * Created by Marius on 5/25/14.
 */
public class LogedIn extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        String data = getIntent().getExtras().getString("nickas");

        Intent intent = new Intent(this, SwipeUser.class);
        intent.putExtra("nickas",data);
        startActivity(intent);


    }





}
