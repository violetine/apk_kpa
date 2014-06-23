package com.example.apk_kpa.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        startActivity(new Intent(this, Login.class));

        // Hide the Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
    }

    //metodas register klases iskvietimui
    public void register (View view1) {

//        startActivity(new Intent(this, Register.class));
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }


    public void loged_in (View view2) {

        startActivity(new Intent(this, LogedIn.class));
    }

    public void logIn (View view3){
        startActivity(new Intent(this, Login.class));
    }


//    public void test(View view){
//        startActivity(new Intent(this, Admin_loged.class));
//    }
}
