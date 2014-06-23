package com.example.apk_kpa.app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.apk_kpa.app.SwipeMenu.SwipeAdmin;


/**
 * Created by Dofke on 2014-06-04.
 */
public class Admin extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, SwipeAdmin.class));

    }





}








